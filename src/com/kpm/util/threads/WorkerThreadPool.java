package com.kpm.util.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class WorkerThreadPool {

    private int maxPoolSize = 0;
    private int keepAliveTimeInSeconds = 0;
    private int cores = 0;
    private MonitorThread monitorThread = null;
    private ActiveTasksThreadPoolExecutor threadPoolExecutor = null;

    public WorkerThreadPool(int maxPoolSize, int keepAliveTimeInSeconds) {
        this.maxPoolSize = maxPoolSize;
        this.keepAliveTimeInSeconds = keepAliveTimeInSeconds;

        // get the number of cores...
        this.cores = Runtime.getRuntime().availableProcessors();

        System.out.println("Cores: " + this.cores);
        System.out.println("Max Pool Size: " +this.maxPoolSize);

        if (this.cores > this.maxPoolSize) {
            this.cores = this.maxPoolSize;
        } // if

        // RejectedExecutionHandler implementation
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();

        // Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // creating the ThreadPoolExecutor
        try {
            this.threadPoolExecutor = new ActiveTasksThreadPoolExecutor(cores, this.maxPoolSize, this.keepAliveTimeInSeconds, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
        } // try
        catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
        } // catch
        
        // start the monitoring thread
        this.monitorThread = new MonitorThread(this.threadPoolExecutor, 5);
        Thread thread = new Thread(this.monitorThread);
        thread.start();
    } // constructor

    public void addThread(WorkerThread workerThread) {
        this.threadPoolExecutor.execute(workerThread);
    } // addThread()

    public void shutdown() {
        try {
            this.threadPoolExecutor.shutdown();

            while (this.threadPoolExecutor.isShutdown() && this.threadPoolExecutor.getActiveCount() != 0) {
                Thread.sleep(10000);
            } // while

            this.monitorThread.shutdown();
        } // try
        catch (InterruptedException ie) {
            System.err.println (ie.getMessage());
        } // catch
    } // shutdown()

} // class
