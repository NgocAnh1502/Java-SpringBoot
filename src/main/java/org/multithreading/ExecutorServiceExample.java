package org.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i = 1; i <= 3; i++){
            final int taskId = i;
            executor.submit(() ->{
                System.out.println("Thread "+ taskId + " dang duoc thuc thi boi " + Thread.currentThread().getName());
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Thread " + taskId + "da xong");
            });
        }
        executor.shutdown();
    }
}
