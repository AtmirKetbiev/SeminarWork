package task3;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;

public class MyExecutor implements Executor {

    private PriorityBlockingQueue<Thread> priorityBlockingQueue = new PriorityBlockingQueue(1, new ThreadComparator());
    private int poolVolume;
    private Object o = new Object();
    private int cout = 2;

    public MyExecutor(int poolVolume) {
        this.poolVolume = poolVolume;
    }

    @Override
    public void execute(Runnable runnable) {
        cout++;
        if (priorityBlockingQueue.size() == poolVolume) {
            synchronized (o) {
                try {
                    System.out.println("in queue" + cout);
                    o.wait();
                    System.out.println("out queue" + cout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Thread thread = new Thread(runnable) {
            @Override
            public void run() {
                runnable.run();
                try {
                    System.out.println("Start " + Thread.currentThread().getName());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                priorityBlockingQueue.poll();
                synchronized (o) {
                    o.notify();
                }
            }
        };

        priorityBlockingQueue.add(thread);
        thread.start();
    }
}

class ThreadComparator implements Comparator<Thread> {
    @Override
    public int compare(Thread t1, Thread t2) {
        return 0;
    }
}