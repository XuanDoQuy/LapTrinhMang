package com.multicast.ex2;

import java.io.IOException;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            InetAddress ipGroup = InetAddress.getByName("228.5.6.7");
            DatagramSocket sender = new DatagramSocket();
            System.out.println("server running");
            int i = 0;
            while (true){
                String message = "Message " + i++;
                DatagramPacket packet = new DatagramPacket(message.getBytes(),message.length(),ipGroup,11005);
                sender.send(packet);
                System.out.println(message);
                Thread.sleep(2000);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
