package com.company.thread.ex2;


import java.util.ArrayList;
import java.util.Random;

public class Ex2 {
    public static void main(String[] args) throws InterruptedException {
        ShareData data = new ShareData();
        new Producer(data, "P1",100).start();
        new Consumer(data, "C1",100).start();
        new Producer(data, "P2",100).start();
        new Consumer(data, "C2",100).start();
    }

}

class Producer extends Thread{
    ShareData shareData;
    int duration;
    public Producer(ShareData shareData, String threadName, int duration){
        super(threadName);
        this.shareData = shareData;
        this.duration = duration;
    }

    @Override
    public void run() {
        for (int i=0;i<5;i++){
            shareData.produce(getName(), duration, new Random().nextInt(100));
        }
    }
}

class Consumer extends Thread{
    ShareData shareData;
    int duration;
    public Consumer(ShareData shareData, String threadName, int duration){
        super(threadName);
        this.shareData = shareData;
        this.duration = duration;
    }

    @Override
    public void run() {
        for (int i = 0;i<5;i++){
            shareData.consume(getName(), duration);
        }
    }
}

class ShareData{
    ArrayList<Integer> data = new ArrayList<>();
    int MAX_SIZE = 3;
    synchronized void produce(String threadName, int duration, int value){
        while (data.size() >= MAX_SIZE ){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data.add(value);
        System.out.println(threadName + ": produce: " + value +"  at  " + System.currentTimeMillis());
        notifyAll();
    }

    synchronized void consume(String threadName, int duration){
        while (data.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int x= data.remove(data.size() - 1);
        System.out.println(threadName + ": consume: " + x + "  at  " + System.currentTimeMillis());
        notifyAll();
    }
}
