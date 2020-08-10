package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloaders extends Thread {
    private Semaphore sem;
    private CountDownLatch latch;
    private int id;
    private int fileSize;
    public Downloaders(Semaphore sem,CountDownLatch latch,int id){
        this.latch = latch;
        this.sem = sem;
        this.id = id;
    }
    public synchronized void run(){
        try {
            sem.acquire();
            System.out.println("Пользователь "+id+" начал загрузку");
            for (fileSize = 0; fileSize <501 ; fileSize+=100) {
                sleep(1000);
                System.out.println("Пользователь "+id+" загрузил"+fileSize+ "мб");
            }
            sem.release();
            latch.countDown();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
