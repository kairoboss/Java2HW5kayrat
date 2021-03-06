package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        // write your code

        Semaphore sem1 = new Semaphore(1);
        Semaphore sem = new Semaphore(3);
        CountDownLatch CDL = new CountDownLatch(25);
        Uploader uploader = new Uploader(CDL);
        uploader.start();
        try {
            CDL.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Загрузка на сервер завершилась");
        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 1; i < 11; i++) {
            new Downloaders(sem, cdl, i).start();
        }
        try {
            cdl.await();
            System.out.println("Пользователи загрузили файлы 10 раз");
            System.out.println("Файл удаляется с сервера");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}