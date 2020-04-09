package task3;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;

public class MyExecutor implements Executor {

    private PriorityBlockingQueue<Runnable> priorityBlockingQueue = new PriorityBlockingQueue(1, new ThreadComparator());
    private int poolVolume;
    private Object o = new Object();
    private int coutThread = 0;

    public MyExecutor(int poolVolume) {
        this.poolVolume = poolVolume;
    }

    @Override
    public void execute(Runnable runnable) {
        priorityBlockingQueue.add(runnable);
        synchronized (o) {
            o.notify();
        }
        if (poolVolume > 0) {
            coutThread++;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Thread.currentThread().setName("thread " + coutThread);
                    while (!priorityBlockingQueue.isEmpty()) {
                        try {
                            priorityBlockingQueue.take().run();
                            System.out.println("Start " + Thread.currentThread().getName());
                            Thread.sleep(1000);
                            synchronized (o) {
                                o.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();
        }
        poolVolume--;
    }
}

class ThreadComparator implements Comparator<Thread> {
    @Override
    public int compare(Thread t1, Thread t2) {
        return 0;
    }
}