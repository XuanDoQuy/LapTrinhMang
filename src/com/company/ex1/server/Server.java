package com.company.ex1.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(113);
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("aaa");
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String userInput;
                while ((userInput = in.readLine()) != null){
                    System.out.println(userInput);
                    userInput = userInput.toUpperCase();
                    System.out.println(userInput);
                }
                System.out.println("exit");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
