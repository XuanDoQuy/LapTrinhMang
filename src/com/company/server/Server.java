package com.company.server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.awt.event.KeyEvent.*;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(15000);
            System.out.println("server running...");
            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                Robot robot = null;
                try {
                    robot = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }

                boolean initCapture = false;

                String type = "mouse";

                while (true) {
                    type = dis.readUTF();
                    System.out.println("type = " +type);
                    if (type.equals("mouse")) {
                        int x = dis.readInt();
                        int y = dis.readInt();
                        int wheel = dis.readInt();
                        boolean leftClick = dis.readBoolean();
                        boolean rightClick = dis.readBoolean();
                        System.out.println("x,y  = " + x + "  " + y);
                        if (x != 0 || y != 0) {
                            Point point = MouseInfo.getPointerInfo().getLocation(); //get postion of mouse
                            float nowx = point.x;
                            float nowy = point.y;
                            int n = 7;// chia lam 7 buoc di chuyen nho
                            int t = 7;
                            double dx = (x) / ((double) n);
                            double dy = (y) / ((double) n);
                            double dt = t / ((double) n);
                            for (int step = 1; step <= n; step++) {
                                robot.delay((int) dt);
                                robot.mouseMove((int) (nowx + dx * step), (int) (nowy + dy * step));
                            }

//                        robot.mouseMove((int) nowx + x, (int) nowy + y);
                        } else if (wheel != 0) {
                            robot.mouseWheel(wheel);
                        } else if (leftClick) {
                            robot.mousePress(InputEvent.BUTTON1_MASK);
                            robot.mouseRelease(InputEvent.BUTTON1_MASK);
                        } else if (rightClick) {
                            robot.mousePress(InputEvent.BUTTON3_MASK);
                            robot.mouseRelease(InputEvent.BUTTON3_MASK);
                        } else {
                            System.out.println("Data not valid :" + x + " " + y + " " + wheel + " " + leftClick + " " + rightClick);
                        }
                        initCapture = false;
                    }else if (type.equals("keyboard")){
                        String input = dis.readUTF();
                        boolean isText = dis.readBoolean();
                        String keyCode = dis.readUTF();
                        if (isText){
                            enterText(input);
                        }else{
                            pressKey(keyCode);
                        }
                        initCapture = false;
                    } else if(type.equals("powerpoint")) {
                        System.out.println("on mode powerpoint");
                        int action = dis.readInt();
                        int x = dis.readInt();
                        int y = dis.readInt();
                        if (!initCapture){
                            try {
                                new CaptureScreenThread(type, dos).start();
                                initCapture = true;
                            } catch (AWTException e) {
                                e.printStackTrace();
                            }
                        }else {
                            System.out.println("(x,y) =  " + x + "," + y);
                            if (action == 1){
                                robot.mouseMove(x,y);
                                robot.mousePress(InputEvent.BUTTON1_MASK);
                            } else if (action == 2){
                                robot.mouseMove(x,y);
                            }else if (action == 3){
                                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                            }else if (action == 4){
                                robot.keyPress(VK_RIGHT);
                                robot.keyRelease(VK_RIGHT);
                            }else if (action == 5){
                                robot.keyPress(VK_LEFT);
                                robot.keyRelease(VK_LEFT);
                            }else if (action == 6 || action == 7){
                                robot.keyPress(VK_CONTROL);
                                robot.keyPress(VK_P);
                                robot.delay(10);
                                robot.keyRelease(VK_P);
                                robot.keyRelease(VK_CONTROL);
                            }
                        }
                    } else if (type.equals("exit")){
                        break;
                    }
                }

                System.out.println("finished");

                dis.close();
                dos.close();
                socket.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void pressKey(String keyCode) {
        KeyboardControl control = new KeyboardControl();
        switch (keyCode){
            case "Ctrl+C": control.pressCopy();break;
            case "Ctrl+V": control.pressPaste();break;
            case "PageUp": control.keyPressAndRelease(VK_PAGE_UP);break;
            case "PageDown": control.keyPressAndRelease(VK_PAGE_DOWN);break;
        }
    }

    private static void enterText(String input) {
        KeyboardControl control = new KeyboardControl();
        for (int i = 0; i< input.length(); i++){
            control.typeCharacter(input.charAt(i));
        }

    }

}

class CaptureScreenThread extends Thread{
    private String type;
    private Robot robot;
    private int screenWidth;
    private int screemHeight;
    private DataOutputStream dos;

    public CaptureScreenThread(String type, DataOutputStream dos) throws AWTException {
        this.type = type;
        robot = new Robot();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = d.width;
        screemHeight = d.height;
        this.dos = dos;
    }

    @Override
    public void run() {
        while (type.equals("powerpoint")||type == null){
            BufferedImage bufferedImage = robot.createScreenCapture(new Rectangle(screenWidth,screemHeight));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                ImageIO.write(bufferedImage,"PNG",baos);
                byte[] imgBytes = baos.toByteArray();
                dos.writeInt(imgBytes.length);
                dos.write(imgBytes,0, imgBytes.length);
                robot.delay(100);
                System.out.println(imgBytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

