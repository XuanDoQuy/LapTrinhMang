package remotedroid;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server = null;
    private static Socket client = null;
    private static BufferedReader in = null;
    private static String line;
    private static boolean isConnected=true;
    private static Robot robot;
    private static final int SERVER_PORT = 8998;
    private static  int wheelAmount;
    public static void main(String[] args) {

        try {
            robot = new Robot();
            server = new ServerSocket(SERVER_PORT);
            client = server.accept();
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch(IOException e) {
            System.out.println("Error in opening Socket");
            System.exit(-1);
        }catch(AWTException e) {
            System.out.println("Error in opening Socket");
            System.exit(-1);
        }

        //read input from client while it is connected
        while(isConnected) {
            try {
                line = in.readLine(); // read input from client
                System.out.println(line);

                //if user clicks on next
            } catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }

            // read input from client while it is connected
            while(isConnected) {
                try {
                    line= in.readLine(); //read input from client
                    System.out.println(line);

                    //if user clicks on left
                    if(line.equalsIgnoreCase("left")){
                        //Simulate press and release of key 'r'
                        robot.keyPress(KeyEvent.VK_L);
                        robot.keyRelease((KeyEvent.VK_R));
                    }
                    //if user clicks on right
                    else if (line.equalsIgnoreCase("right")) {
                        //simulate press and release of key 'l'
                        robot.keyPress(KeyEvent.VK_L);
                        robot.keyRelease((KeyEvent.VK_L));
                    }
                    //if user taps on mousepad to simulate a left click
                    else if(line.contains("left_click")){
                        //Simulate press and release of mouse button 1(makes sure correct button is
                        //pressed based on user's dexterity)
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    }
                    //if user taps on mousepad to simulate a right click
                    else if(line.contains("right_click")){
                        //Simulate press and release of mouse button 1(makes sure correct button is
                        //pressed based on user's dexterity)
                        robot.mousePress(InputEvent.BUTTON3_MASK);
                        robot.mouseRelease(InputEvent.BUTTON3_MASK);
                    }
                    else if(line.contains("mouse_wheel")) {
                        robot.mouseWheel(wheelAmount);
                    }
                    //input will come in x,y format if user move mouse on mousepad
                    else if(line.contains(",")) {
                        float movex = Float.parseFloat(line.split(",")[0]);//extract movement in x direction
                        float movey = Float.parseFloat(line.split(",")[1]);//extract movement in y direction
                        Point point = MouseInfo.getPointerInfo().getLocation();//Get current mouse position
                        float nowx = point.x;
                        float nowy = point.y;
                        robot.mouseMove((int) (nowx + movey), (int) (nowy + movex));//Move mouse pointer to new location
                    }
                    //Exit if user ends the connection
                    else if(line.equalsIgnoreCase("exit")) {
                        isConnected=false;
                        //Close server and client socket
                        server.close();
                        client.close();
                    }

                } catch (IOException e) {
                    System.out.println("Read failed");
                    System.exit(-1);
                }
            }
        }
    }
}
