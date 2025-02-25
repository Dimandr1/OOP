package ru.nsu.stolyarov;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueue {
    private int[] orderQueue;
    private int left;
    private int right;
    public int size;
    public MyQueue(int n){
        orderQueue = new int[n];
        left = 0;
        right = 0;
        size = n;
    }

    public int len(){
        if(right >= left){
            return right - left;
        }
        return size - (left - right);
    }

    public void add(int element) throws ArrayIndexOutOfBoundsException {
        if(len() == size - 1){
            throw new ArrayIndexOutOfBoundsException("queue is full!");
        }
        orderQueue[right] = element;
        right++;
        if(right == size){
            right = 0;
        }
    }

    public int extract() throws ArrayIndexOutOfBoundsException {
        if(len() == 0){
            throw new ArrayIndexOutOfBoundsException("empty queue!");
        }
        int ret = orderQueue[left];
        left++;
        if(left == size){
            left = 0;
        }
        return ret;
    }
}
