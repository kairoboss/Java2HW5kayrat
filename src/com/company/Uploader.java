package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Uploader extends Thread {
    private CountDownLatch latch;
    private int size;

    public Uploader(CountDownLatch latch,int size) {
        this.latch = latch;
        this.size = size;
    }

    public synchronized void run() {
        try {
            sleep(1000);
            System.out.println("На сервер загрузилось " + size + "мб");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
