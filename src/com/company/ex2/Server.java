package com.company.ex2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(113);
            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                int a = in.readInt();
                int b = in.readInt();
                System.out.format("(a,b) : (%d,%d)\n",a,b);

                int c = a + b;
                out.writeInt(c);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
