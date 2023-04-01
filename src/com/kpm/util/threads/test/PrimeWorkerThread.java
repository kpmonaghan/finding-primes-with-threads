package com.kpm.util.threads.test;

import java.util.Set;
import java.util.TreeSet;

import com.kpm.util.threads.WorkerThread;

public class PrimeWorkerThread extends WorkerThread {

    private String name = null;
    private int lowerRange = 0;
    private int marker = 0;
    private int upperRange = 0;

    Set<Integer> primesFound = new TreeSet<Integer>();

    public PrimeWorkerThread (int lowerRange, int upperRange) {
        this.name = Thread.currentThread().getName();

        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
    } // constructor

    @Override
    protected String getName() {
        return this.name;
    } // getName()

    @Override
    protected float getPercentComplete() {
        return (float)(marker - lowerRange) / (float)(upperRange - lowerRange) * 100;
    } // getPercentComplete()

    @Override
    protected void preProcessCommand() {
        this.name = Thread.currentThread().getName();

        System.out.println(
            String.format("[%s] Searching for primes betweed %d and %d...", this.getName(), this.lowerRange, this.upperRange)
        );
    } // preProcessCommand()

    @Override
    protected void processCommand() {
        for (int i = this.lowerRange; i <= this.upperRange; i++) {
            boolean isPrime = true;
            this.setMarker(i);

            for (int j = 2; j <= i / 2; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                } // if
            } // for

            if (isPrime) {
                this.primesFound.add(i);
            } // if

            Thread.yield();
        } // for
    } // processCommand()

    @Override
    protected void postProcessCommand() {
        System.out.println(
            String.format("[%s] Found %d primes.", this.getName(), this.primesFound.size())
        );
    } // postProcessCommand()

    public void setMarker(int marker) {
        this.marker = marker;
    } // setMarker()

} // class
