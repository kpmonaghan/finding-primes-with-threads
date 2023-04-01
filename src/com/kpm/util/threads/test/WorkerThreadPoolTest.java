package com.kpm.util.threads.test;

import com.kpm.util.threads.WorkerThreadPool;

public class WorkerThreadPoolTest {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        WorkerThreadPool workerThreadPool = new WorkerThreadPool(20, 10);

        workerThreadPool.addThread(new PrimeWorkerThread(2, 1000000));
        workerThreadPool.addThread(new PrimeWorkerThread(1000001, 2000000));
        workerThreadPool.addThread(new PrimeWorkerThread(2000001, 3000000));
        workerThreadPool.addThread(new PrimeWorkerThread(3000001, 4000000));
        workerThreadPool.addThread(new PrimeWorkerThread(4000001, 5000000));
        workerThreadPool.addThread(new PrimeWorkerThread(5000001, 6000000));
        workerThreadPool.addThread(new PrimeWorkerThread(6000001, 7000000));
        workerThreadPool.addThread(new PrimeWorkerThread(7000001, 8000000));
        workerThreadPool.addThread(new PrimeWorkerThread(8000001, 9000000));
        workerThreadPool.addThread(new PrimeWorkerThread(9000001, 10000000));

        workerThreadPool.addThread(new PrimeWorkerThread(10000001, 11000000));
        workerThreadPool.addThread(new PrimeWorkerThread(11000001, 12000000));
        workerThreadPool.addThread(new PrimeWorkerThread(12000001, 13000000));
        workerThreadPool.addThread(new PrimeWorkerThread(13000001, 14000000));
        workerThreadPool.addThread(new PrimeWorkerThread(14000001, 15000000));
        workerThreadPool.addThread(new PrimeWorkerThread(15000001, 16000000));
        workerThreadPool.addThread(new PrimeWorkerThread(16000001, 17000000));
        workerThreadPool.addThread(new PrimeWorkerThread(17000001, 18000000));
        workerThreadPool.addThread(new PrimeWorkerThread(18000001, 19000000));
        workerThreadPool.addThread(new PrimeWorkerThread(19000001, 20000000));

        workerThreadPool.shutdown();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println(
            String.format("Duration: %d ms.", duration)
        );
    } // main()
    
} // class
