package task3;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyExecutorTest {

    @Test
    public void oneThread() throws IOException, ClassNotFoundException {

        int poolVolume = 1;
        int numberOfThreads = 1;

        MyExecutor myExecutor = new MyExecutor(poolVolume, numberOfThreads);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        myExecutor.execute(countDownLatch::countDown);
        try {
            countDownLatch.await(200, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, countDownLatch.getCount());
    }

    @Test
    public void nThreads() throws IOException, ClassNotFoundException {

        int poolVolume = 3;
        int numberOfThreads = 3;

        MyExecutor myExecutor = new MyExecutor(poolVolume, numberOfThreads);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            myExecutor.execute(countDownLatch::countDown);
            try {
                countDownLatch.await(200, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(0, countDownLatch.getCount());
    }

    @Test
    public void moreThreads() throws IOException, ClassNotFoundException {

        int poolVolume = 5;
        int numberOfThreads = 2 * poolVolume;

        MyExecutor myExecutor = new MyExecutor(poolVolume, numberOfThreads);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            myExecutor.execute(countDownLatch::countDown);
            try {
                countDownLatch.await(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(0, countDownLatch.getCount());
    }

}