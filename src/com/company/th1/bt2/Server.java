package com.company.th1.bt2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static ArrayList<Person> listData = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(15000);
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                while (true) {
                    int operator = 999;
                    while ((operator = (int) ois.readObject()) == 999) ;
                    if (operator <= 0 || operator > 4) break;

                    if (operator == 1){
                        Person p = (Person) ois.readObject();
                        p.setId(count++);
                        System.out.println(p);
                        listData.add(p);
                    }else if (operator == 2){
                        String name = (String) ois.readObject();
                        System.out.println(name);
                        String result = "not found";
                        for (Person p : listData){
                            if (p.getName().equals(name)){
                                result = p.getPhoneNumber();
                                break;
                            }
                        }
                        oos.writeObject(result);
                    }else if (operator == 3 ){
                        oos.writeObject(listData);
                    }else if (operator == 4){
                        String name = (String) ois.readObject();
                        for (Person p : listData){
                            if (p.getName().equals(name)){
                                oos.writeObject(p);
                                break;
                            }
                        }
                        oos.writeObject(null);
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
