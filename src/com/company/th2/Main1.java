package com.company.th2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Main1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("203.162.10.109",2207);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String token = ";911";
            dos.writeUTF(token);

            int a = dis.readInt();
            int b = dis.readInt();

            dos.writeInt(UCLN(a,b));
            dos.writeLong(BCNN(a,b));
            dos.writeInt(a+b);
            dos.writeInt(a*b);

            dos.close();
            dis.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int UCLN(int a, int b){
        int r;
        while (b != 0){
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    private static long BCNN(int a, int b){
        return (a * b) / UCLN(a,b);
    }
}
