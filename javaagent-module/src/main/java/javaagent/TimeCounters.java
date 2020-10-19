package javaagent;

public class TimeCounters {
    public static long minTime = Long.MAX_VALUE;
    public static long maxTime = -1;
    public static long averageTime = 0;
    public static long numberCalls = 0;

    public static long getMinTime() {
        return minTime;
    }

    public static long getAverageTime() {
        return averageTime;
    }

    public static long getMaxTime() {
        return maxTime;
    }

    public static long getNumberCalls() {
        return numberCalls;
    }

    public static void setAverageTime(long averageTime) {
        TimeCounters.averageTime = averageTime;
    }

    public static void setMaxTime(long maxTime) {
        TimeCounters.maxTime = maxTime;
    }

    public static void setMinTime(long minTime) {
        TimeCounters.minTime = minTime;
    }

    public static void setNumberCalls(long numberCalls) {
        TimeCounters.numberCalls = numberCalls;
    }
}
