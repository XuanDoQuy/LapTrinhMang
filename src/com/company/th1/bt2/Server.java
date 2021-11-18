package com.company.th1.bt2;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server  {

    public static ArrayList<Person> listData = new ArrayList<>();
    public static int count = 0;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(11000);
            while (true) {
                Socket socket = serverSocket.accept();
                // create ObjectOutputStream before ObjectInputStream because deadlock - streamheader
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Person person = new Person("xuan","12345");
                person.setId(1);

                oos.writeObject(person);

                ois.close();
                oos.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


