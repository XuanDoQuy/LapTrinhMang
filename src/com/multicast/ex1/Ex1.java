package com.multicast.ex1;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Ex1 {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> listNIC = NetworkInterface.getNetworkInterfaces();
            while (listNIC.hasMoreElements()){
                NetworkInterface ni = listNIC.nextElement();
                if (ni.isLoopback()) {
                    System.out.println(ni.getDisplayName());
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
