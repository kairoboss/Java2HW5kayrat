package com.company;

import java.util.concurrent.CountDownLatch;

public class Uploader extends Thread {
    private CountDownLatch latch;
    private int size;

    public Uploader(CountDownLatch latch) {
        this.latch = latch;
    }

     public synchronized void run() {
        try {
            for (size = 0; size <501 ; size+=20){
            sleep(1000);
            System.out.println("На сервер загрузилось " + size + "мб");
            latch.countDown();}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
