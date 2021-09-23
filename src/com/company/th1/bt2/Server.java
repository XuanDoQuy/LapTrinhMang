package com.company.th1.bt2;

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
            ServerSocket serverSocket = new ServerSocket(15000);
            while (true) {
                Socket socket = serverSocket.accept();
                // create ObjectOutputStream before ObjectInputStream because deadlock - streamheader
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                DaoImpl dao = new DaoImpl();

                while (true) {
                    int operator = 999;
                    while ((operator = (int) ois.readObject()) == 999) ;
                    if (operator <= 0 || operator > 4) break;

                    if (operator == 1){
                        Person p = (Person) ois.readObject();
                        dao.add(p);
                    }else if (operator == 2){
                        String name = (String) ois.readObject();
                        oos.writeObject(dao.getPhone(name));
                    }else if (operator == 3 ){
                        oos.writeObject(dao.getALl());
                    }else if (operator == 4){
                        String name = (String) ois.readObject();
                        oos.writeObject(dao.search(name));
                    }

                }

                ois.close();
                oos.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}


