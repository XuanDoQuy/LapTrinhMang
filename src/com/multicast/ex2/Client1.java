package com.multicast.ex2;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client1 {
    public static void main(String[] args) {
        try {
            MulticastSocket client = new MulticastSocket(11005);
            client.joinGroup(InetAddress.getByName("228.5.6.7"));
            int i = 0;
            while (i++<5){
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                client.receive(packet);
                String message = new String(packet.getData());
                System.out.println(message);
            }
            client.leaveGroup(InetAddress.getByName("228.5.6.7"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
