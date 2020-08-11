package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Uploader extends Thread {
    private CountDownLatch latch;
    private Semaphore semaphore;
    private int size;

    public Uploader(CountDownLatch latch,int size,Semaphore semaphore) {
        this.latch = latch;
        this.size = size;
        this.semaphore = semaphore;
    }

     public synchronized void run() {
        try {
            semaphore.acquire(1);
            sleep(1000);
            System.out.println("На сервер загрузилось " + size + "мб");
            latch.countDown();
            semaphore.release(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
