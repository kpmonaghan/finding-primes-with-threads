package com.kpm.util.threads;

public abstract class WorkerThread implements Runnable {

    protected abstract String getName();
    protected abstract float getPercentComplete();
    
    protected abstract void preProcessCommand();
    protected abstract void processCommand();
    protected abstract void postProcessCommand();

    @Override
    public void run() {
        preProcessCommand();
        processCommand();
        postProcessCommand();
    } // run()

    @Override
    public String toString() {
        return String.format("[%s] %3.1f%% complete", this.getName(), this.getPercentComplete());
    } // toString()

} // class
