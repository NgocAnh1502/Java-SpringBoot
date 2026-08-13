package org.multithreading;

class EvenOddNumber{
    private int number = 1;
    private final int MAX = 20;

    public synchronized void oddNumber(){
        while (number <= MAX){
            while (number % 2 == 0){
                try{
                    wait();
                } catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
            if (number <= MAX){
                System.out.println(Thread.currentThread().getName() + ": so le " + number);
                number ++;
            }
            notifyAll();
        }
    }

    public synchronized void evenNumber(){
        while (number <= MAX){
            while (number % 2 !=0){
                try{
                    wait();
                } catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
            if (number <= MAX){
                System.out.println(Thread.currentThread().getName() + ": so chan " + number);
                number ++;
            }
            notifyAll();
        }
    }
}

public class thread {
    public static void main(String[] args) {
        EvenOddNumber eonumber  = new EvenOddNumber();
        Thread oodThread = new Thread(() -> eonumber.oddNumber(), "Thread-1");
        Thread evenThread = new Thread(() -> eonumber.evenNumber(), "Thread-2");

        oodThread.start();
        evenThread.start();
    }
}
