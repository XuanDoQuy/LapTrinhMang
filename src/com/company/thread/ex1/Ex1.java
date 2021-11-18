//package com.company.thread.ex1;
//
//import java.util.Random;
//
//public class Ex1 {
//    public static void main(String[] args) {
//        SharedData sharedData = new SharedData();
//        new Producer(sharedData).start();
//        new Consumer(sharedData).start();
//        new Producer(sharedData).start();
//        new Consumer(sharedData).start();
//    }
//
//}
//
//class Producer extends Thread{
//    SharedData sharedData;
//    public Producer(SharedData sharedData){
//        this.sharedData = sharedData;
//    }
//
//    @Override
//    public void run() {
//        for (int i=0;i<5;i++){
//            sharedData.produce(new Random().nextInt(100));
//        }
//    }
//}
//
//class Consumer extends Thread{
//    SharedData sharedData;
//    public Consumer(SharedData sharedData){
//        this.sharedData = sharedData;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0;i<5;i++){
//            sharedData.consume();
//        }
//    }
//}
//
//class SharedData {
//    int data;
//    boolean isProduced = false;
//
//    synchronized void produce(int value){
//        while (isProduced){
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        data = value;
//        System.out.println("produce: " + data);
//        isProduced = true;
//        notifyAll();
//    }
//
//    synchronized void consume(){
//        while (!isProduced){
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("consume: " + data);
//        this.data = 0;
//        isProduced = false;
//        notifyAll();
//    }
//}
