package com.company.udp.ex1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        byte buffer[] = new byte[1024];
        try {
            datagramSocket = new DatagramSocket(2207);
            System.out.println("Server is running");
            while (true){
                // receive data
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(packet);
                String data = new String(packet.getData());
                System.out.println(data);
                // proccess
                String dataRes = new StringBuilder(data).reverse().toString();
                // response data
                DatagramPacket resPacket = new DatagramPacket(dataRes.getBytes(),dataRes.getBytes().length,packet.getAddress(), packet.getPort());
                datagramSocket.send(resPacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
