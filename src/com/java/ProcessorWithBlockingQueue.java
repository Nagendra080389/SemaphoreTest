package com.java;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by singhnk on 18-01-2017.
 */
public class ProcessorWithBlockingQueue {
    //BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

    LinkedList<Integer> integerList = new LinkedList<>();

    Semaphore semaphore = new Semaphore(1);



    public void produces() throws InterruptedException {

        int value = 0;
        while (true){
            semaphore.acquire();
            if(integerList.size() != 10 ) {
                integerList.add(value++);
            }
            semaphore.release();
        }

    }

    public void consumes() throws InterruptedException {
        semaphore.acquire();
        while (true){
            Integer take = integerList.removeFirst();
            System.out.println("Size of the BlockingQueue is : "+ integerList.size()+" and the value consumed is :"+take);
            Thread.sleep(100);
            semaphore.release();
        }
    }
}
