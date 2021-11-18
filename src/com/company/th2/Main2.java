package com.company.th2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket();

            String token ="msv;801";
            DatagramPacket tokenPacket = new DatagramPacket(token.getBytes(),token.length(), InetAddress.getByName("203.162.10.109"),2207);
            socket.send(tokenPacket);

            byte[] buffer = new byte[4096];
            DatagramPacket resPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(resPacket);
            String resStr = new String(resPacket.getData());

            ArrayList<String> input = new ArrayList<>();
            ArrayList<Integer> data = new ArrayList<>();
            ArrayList<Integer> res = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(resStr);
            while (tokenizer.hasMoreTokens()){
                input.add(tokenizer.nextToken(";"));
            }
            StringTokenizer tokenizer1 = new StringTokenizer(input.get(2));
            while (tokenizer.hasMoreTokens()){
                data.add(Integer.parseInt(tokenizer.nextToken(",")));
            }
            for (int i = 1 ; i <= Integer.parseInt(input.get(1)); i++){
                if (!data.contains(i)){
                    res.add(i);
                }
            }

            String response = input.get(0) + ";";
            for (int i : res){
                response +=  i + ",";
            }
            System.out.println(response);

            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.length(),resPacket.getAddress(),resPacket.getPort());
            socket.send(responsePacket);
        }catch (Exception e){

        }
    }
}
