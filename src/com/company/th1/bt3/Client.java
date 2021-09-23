package com.company.th1.bt3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("203.162.10.109",2207);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String token = "B18DCCN700;800";
            dos.writeUTF(token);

            int a = dis.readInt();
            int b = dis.readInt();

            dos.writeInt(a+b);
            dos.writeInt(a*b);

            dos.close();
            dis.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
