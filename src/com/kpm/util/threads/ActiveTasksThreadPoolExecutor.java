package com.kpm.util.threads;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ActiveTasksThreadPoolExecutor extends ThreadPoolExecutor {

    private final ConcurrentHashMap<Runnable, Boolean> activeTasks = new ConcurrentHashMap<>();

    public ActiveTasksThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    } // constructor

    public ActiveTasksThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    } // constructor

    public ActiveTasksThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    } // constructor

    public ActiveTasksThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    } // constructor

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        activeTasks.put(r, Boolean.TRUE);
        super.beforeExecute(t, r);
    } // beforeExecute()

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        activeTasks.remove(r);
    } // afterExecute()

    public Set<Runnable> getActiveTasks() {
        // the returned set will not throw a ConcurrentModificationException.
        return activeTasks.keySet();
    } // getActiveTasks()

} // class
