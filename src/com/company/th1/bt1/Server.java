package com.company.th1.bt1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Calculator {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2207);
            while (true) {
                Socket client = serverSocket.accept();
                DataInputStream dis = new DataInputStream(client.getInputStream());
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());

                while (true) {
                    int operator = 999;
                    while ((operator = dis.readInt()) == 999) ;
                    if (operator <= 0 || operator > 4) break;

                    int a = dis.readInt();
                    int b = dis.readInt();
                    int c = 0;
                    switch (operator) {
                        case 1:
                            c = a + b;
                            break;
                        case 2:
                            c = a - b;
                            break;
                        case 3:
                            c = a * b;
                            break;
                        case 4:
                            c = a / b;
                    }
                    dos.writeInt(c);
                }

                dis.close();
                dos.close();
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int plus(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }

    @Override
    public int divide(int a, int b) {
        return a / b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }
}

interface Calculator {
    int plus(int a, int b);

    int minus(int a, int b);

    int divide(int a, int b);

    int multiply(int a, int b);
}
