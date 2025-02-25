package ru.nsu.stolyarov;

import java.time.Clock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SafeQueueManager {
    private MyQueue queue;
    ReentrantLock lock;
    Condition cond;
    public SafeQueueManager(int size){
        queue = new MyQueue(size);
        lock = new ReentrantLock();
        cond = lock.newCondition();
    }
    public int getFullness(){
        return queue.len();
    }
    public boolean tryAdd(int element, long limit) throws InterruptedException {

        long curTime = System.currentTimeMillis();
        if(!lock.tryLock(limit - curTime, TimeUnit.MILLISECONDS)){
            return false;
        }
        try {
            while (queue.len() == queue.size - 1){
                curTime = System.currentTimeMillis();
                if(curTime > limit){
                    return false;
                }
                cond.await(limit - curTime, TimeUnit.MILLISECONDS);
            }
            queue.add(element);
        }
        finally {
            lock.unlock();
            cond.signalAll();
        }
        return true;
    }

    public void add(int element) throws InterruptedException {
        lock.lock();
        try {
            while (queue.len() == queue.size - 1){
                cond.await();
            }
            queue.add(element);
        }
        finally {
            lock.unlock();
            cond.signalAll();
            cond.signalAll();
        }
    }


    public int tryGet(long limit) throws InterruptedException {
        long curTime = System.currentTimeMillis();
        int ret;
        if(!lock.tryLock(limit - curTime, TimeUnit.MILLISECONDS)){
            return -1;
        }
        try {
            while (queue.len() == 0){
                curTime = System.currentTimeMillis();
                if(curTime > limit){
                    return -1;
                }
                cond.await(limit - curTime, TimeUnit.MILLISECONDS);
            }
            ret = queue.extract();
        }
        finally {
            lock.unlock();
            cond.signalAll();
        }
        return ret;
    }

    public int tryGet() throws InterruptedException {
        int ret;
        lock.lock();
        try {
            if(queue.len() == 0){
                return -1;
            }
            ret = queue.extract();
        }
        finally {
            lock.unlock();
            cond.signalAll();
        }
        return ret;
    }
}
