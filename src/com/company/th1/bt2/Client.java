package com.company.th1.bt2;

import java.io.*;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Client {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 15000);
            System.out.println("connected");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("aa");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("aaa");


            while (true) {
                showMenu();
                int operator;
                try {
                    operator = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    break;
                }
                if (operator <= 0 || operator > 4) {
                    oos.writeObject(operator);
                    break;
                }

                oos.writeObject(operator);

                if (operator == 1) {
                    System.out.println("Enter name : ");
                    String name = sc.nextLine();
                    System.out.println("Enter phone number: ");
                    String phoneNumber = sc.nextLine();
                    Person p = new Person(name, phoneNumber);
                    oos.writeObject(p);
                    System.out.println("Add suceesed");
                }else if (operator == 3){
                    List<Person> listAll = (List<Person>) ois.readObject();
                    System.out.println("=======================");
                    for (Person p : listAll){
                        System.out.print("name : "+p.getName());
                        System.out.println("          phone : "+p.getPhoneNumber());
                    }
                    System.out.println("=======================");
                } else if (operator == 2){
                    System.out.println("Enter name : ");
                    String name = sc.nextLine();
                    oos.writeObject(name);
                    String phone;
                    while ((phone = (String) ois.readObject()) == null);
                    System.out.println("Phone is : "+phone);
                }else{
                    System.out.println("Enter name : ");
                    String name = sc.nextLine();
                    oos.writeObject(name);
                    Person p;
                    p = (Person) ois.readObject();
                    System.out.println("Persion is : " + p);
                }

            }

            ois.close();
            oos.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("Select option : ");
        System.out.println("1. Add");
        System.out.println("2. Get phone");
        System.out.println("3. Get all");
        System.out.println("4. Search");
        System.out.println("Other. Exit");
    }
}
