package com.company.udp.ex1;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        try {
            //send data
            datagramSocket = new DatagramSocket();
            byte[] buffer = new byte[1024];
            String data = "Hello world";
            DatagramPacket packet = new DatagramPacket(data.getBytes(),data.getBytes().length, InetAddress.getLocalHost(),2207);
            datagramSocket.send(packet);
            //receive data
            DatagramPacket resPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(resPacket);
            String resStr = new String(resPacket.getData());
            System.out.println("result is " + resStr);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
