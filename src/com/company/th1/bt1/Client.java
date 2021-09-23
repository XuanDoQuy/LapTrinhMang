package com.company.th1.bt1;

import sun.security.acl.AclEntryImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2207);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            while (true){
                showMenu();
                int operator;
                try {
                    operator = sc.nextInt();
                }catch (InputMismatchException e){
                    break;
                }
                if (operator <= 0 || operator > 4){
                    dos.writeInt(operator);
                    break;
                }
                System.out.println("Enter first number : ");
                int a = sc.nextInt();
                System.out.println("Enter second number: ");
                int b = sc.nextInt();
                dos.writeInt(operator);
                dos.writeInt(a);
                dos.writeInt(b);
                System.out.println("Result is : " + dis.readInt());
            }

            dis.close();
            dos.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void showMenu() {
        System.out.println("Select option : ");
        System.out.println("1. Plus");
        System.out.println("2. Minus");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.println("Other. Exit");
    }
}

