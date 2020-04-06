package task3;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyExecutor implements Executor {

    private PriorityBlockingQueue<Thread> priorityBlockingQueue = new PriorityBlockingQueue(1, new ThreadComparator());
    private int poolVolume;
    private int numberOfThreads;
    private Object o;

    public MyExecutor(int poolVolume, int numberOfThreads) {
        this.poolVolume = poolVolume;
        this.numberOfThreads = numberOfThreads;
        o = new Object();
    }

    @Override
    public void execute(Runnable runnable) {
        Thread thread = new Thread(() -> {
            runnable.run();
            /*synchronized(o) {
                o.notify();
            }*/
        });

        priorityBlockingQueue.add(thread);
        run();
    }

    private void run() {
        if (priorityBlockingQueue.size() < poolVolume && priorityBlockingQueue.size() < numberOfThreads) {
            return;
        }
        for (int i = 0; i < poolVolume; i++) {
            try {
                Thread t = priorityBlockingQueue.poll(1, TimeUnit.SECONDS);
                t.run();
                System.out.println(t.getName());
                //o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadComparator implements Comparator<Thread> {
    @Override
    public int compare(Thread t1, Thread t2) {
        return 0;
    }
}