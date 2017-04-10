/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuretester;

/**
 *
 * @author Jeff
 */
public class MyTimer {
    private static long startTime;
    private static long startMicroTime;
    /**
     * Sets the timer's milliTimer to now
     */
    public static void startTime(){
        startTime = System.currentTimeMillis();
    }
    
    /**
     * Measures the time between the last startTime and now in milliseconds
     * Uses System.currentTimeMillis(), so bound by its precision
     * @return time in milliseconds since the last startTime call
     */
    public static long elapsedTime(){
        return System.currentTimeMillis() - startTime;
    }
    
    /**
     * sets the timer's microTimer to now 
     */
    public static void startMicroTime(){
        startMicroTime = System.nanoTime()/1000;
    }
    /**
     * Measures the time between the last startMicroTime and now in microseconds
     * Uses System.nanoTime(), so precision may fluctuate
     * @return time in microseconds since the last startMicroTime call
     */
    public static long elapsedMicroTime(){
        return System.nanoTime()/1000 - startMicroTime;
    }
    
}
