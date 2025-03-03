package ru.nsu.stolyarov;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import ru.nsu.stolyarov.interfaces.QueueTimedAddable;
import ru.nsu.stolyarov.interfaces.QueueTimedGettable;


/**
 * Обертка над MyQueue, обеспечивающая Thread-safety.
 */
public class SafeQueueManager implements QueueTimedGettable, QueueTimedAddable {
    private MyQueue queue;
    ReentrantLock lock;
    Condition cond;

    /**
     * Инициализация очереди.
     *
     * @param size максимальный размер очереди
     */
    public SafeQueueManager(int size) {
        queue = new MyQueue(size);
        lock = new ReentrantLock();
        cond = lock.newCondition();
    }

    /**
     * Возвращает длину очереди.
     *
     * @return количество элементов в очереди
     */
    public int len() {
        return queue.len();
    }

    @Override
    public boolean tryAdd(int element, long limit) {
        if (limit == 0) {
            add(element);
            return true;
        } else {
            long startTime = System.currentTimeMillis();
            try {
                if (!lock.tryLock(limit - (System.currentTimeMillis() - startTime),
                        TimeUnit.MILLISECONDS)) {
                    return false;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                while (queue.len() == queue.size - 1) {
                    if (System.currentTimeMillis() - startTime > limit) {
                        return false;
                    }
                    try {
                        cond.await(limit - (System.currentTimeMillis() - startTime),
                                TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                queue.add(element);
                cond.signalAll();
            } finally {
                lock.unlock();
            }
            return true;
        }
    }

    /**
     * Добавить элемент в очередь. Если заполнена, то блокируется до освобождения.
     *
     * @param element элемент для добавления
     */
    public void add(int element) {
        lock.lock();
        try {
            while (queue.len() == queue.size - 1) {
                cond.await();
            }
            queue.add(element);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            cond.signalAll();
            lock.unlock();
        }
    }


    @Override
    public int tryGet(long limit) {
        if (limit == 0) {
            return get();
        }
        long startTime = System.currentTimeMillis();
        int ret;
        try {
            if (!lock.tryLock(limit - (System.currentTimeMillis() - startTime),
                    TimeUnit.MILLISECONDS)) {
                return -1;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            while (queue.len() == 0) {
                if (System.currentTimeMillis() - startTime > limit) {
                    return -1;
                }
                try {
                    cond.await(limit - (System.currentTimeMillis() - startTime),
                            TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            ret = queue.extract();
            cond.signalAll();
        } finally {
            lock.unlock();
        }
        return ret;
    }

    public int get() {
        int ret;
        lock.lock();
        try {
            while (queue.len() == 0) {
                try {
                    cond.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            ret = queue.extract();
            cond.signalAll();
        } finally {
            lock.unlock();
        }
        return ret;
    }
}
