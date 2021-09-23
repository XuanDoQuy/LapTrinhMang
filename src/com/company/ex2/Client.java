package com.company.ex2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost",113);
            //
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            //
            Scanner scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            out.writeInt(a);
            out.writeInt(b);

            int tong = -1;
            while ((tong = in.readInt()) == -1);

            System.out.println("tong  = " + tong);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
