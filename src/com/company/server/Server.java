package com.company.server;

import com.company.th1.bt2.MouseData;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(15000);
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

                while (true) {
                    int x = dis.readInt();
                    int y = dis.readInt();
                    int wheel = dis.readInt();
                    boolean leftClick = dis.readBoolean();
                    boolean rightClick = dis.readBoolean();
                    System.out.println("x,y  = " +x +"  "+y);
                    if (x != 0 || y != 0){
                        Point point = MouseInfo.getPointerInfo().getLocation();
                        float nowx = point.x;
                        float nowy = point.y;
                        int n = 7;
                        int t = 7;
                        double dx = (x) / ((double) n);
                        double dy = (y) / ((double) n);
                        double dt = t / ((double) n);
                        for (int step = 1; step <= n; step++) {
                            robot.delay((int) dt);
                            robot.mouseMove((int) (nowx + dx * step), (int) (nowy + dy * step));
                        }

//                        robot.mouseMove((int) nowx + x, (int) nowy + y);
                    }else if (wheel != 0){
                        robot.mouseWheel(wheel);
                    }else if (leftClick){
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    }else if (rightClick){
                        robot.mousePress(InputEvent.BUTTON3_MASK);
                        robot.mouseRelease(InputEvent.BUTTON3_MASK);
                    }else {
                        System.out.println("Data not valid :" + x + " " + y + " " + wheel + " " + leftClick + " " + rightClick);
                    }

                    if (rightClick) break;
                }

                dis.close();
                dos.close();
                socket.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
