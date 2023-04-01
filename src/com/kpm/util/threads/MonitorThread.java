package com.kpm.util.threads;

import java.util.Iterator;

public class MonitorThread implements Runnable {

    private ActiveTasksThreadPoolExecutor executor;
    private int seconds;
    private boolean run = true;

    public MonitorThread(ActiveTasksThreadPoolExecutor executor, int delayInSeconds) {
        this.executor = executor;
        this.seconds = delayInSeconds;
    } // constructor

    public void shutdown() {
        this.run = false;
    } // shutdown()

    @Override
    public void run() {
        while(run) {
            Iterator<Runnable> activeTasksIterator = this.executor.getActiveTasks().iterator();
            StringBuffer sb = new StringBuffer();
            Runnable activeTask = null;

            while (activeTasksIterator.hasNext()) {
                activeTask = activeTasksIterator.next();
                sb.append(activeTask.toString());

                if (activeTasksIterator.hasNext()) {
                    sb.append("; ");
                } // if
            } // while

            System.out.println(
                String.format("[monitor-thread] [%d/%d] Active: %d, Completed: %d, Queued: %d, Total Tasks: %d, isShutdown: %s, isTerminated: %s%n[monitor-thread] Active Tasks: %s",
                    this.executor.getPoolSize(),    
                    this.executor.getCorePoolSize(),
                    this.executor.getActiveCount(),
                    this.executor.getCompletedTaskCount(),
                    this.executor.getQueue().size(),
                    this.executor.getTaskCount(),
                    this.executor.isShutdown(),
                    this.executor.isTerminated(),
                    sb.toString()
                )
            );

            try {
                Thread.sleep(seconds*1000);
            } // try
            catch (InterruptedException ie) {
                ie.printStackTrace();
            } // catch
        } // while
    } // run()

} // class
