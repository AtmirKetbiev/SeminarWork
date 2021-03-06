package task3;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyExecutorTest {

    @Test
    public void oneThread() {

        int poolVolume = 1;
        int numberOfThreads = 1;

        MyExecutor myExecutor = new MyExecutor(poolVolume);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        myExecutor.execute(countDownLatch::countDown);
        try {
            countDownLatch.await(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, countDownLatch.getCount());
    }

    @Test
    public void nThreads() {

        int poolVolume = 6;
        int numberOfThreads = 6;

        MyExecutor myExecutor = new MyExecutor(poolVolume);
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

    @Test
    public void lessThreads() {

        int poolVolume = 7;
        int numberOfThreads = 4;

        MyExecutor myExecutor = new MyExecutor(poolVolume);
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

    @Test
    public void moreThreads() throws InterruptedException {

        int poolVolume =3;
        int numberOfThreads = 3 * poolVolume + 1;

        MyExecutor myExecutor = new MyExecutor(poolVolume);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            myExecutor.execute(countDownLatch::countDown);
            //Thread.sleep(1000);
            try {
                countDownLatch.await(300, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(0, countDownLatch.getCount());
    }

}