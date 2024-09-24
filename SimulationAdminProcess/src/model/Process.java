package model;

public class Process {
    private String name;
    private int ticksCPU;
    private int arriveTime;
    private int priority;
    
    public Process(String name, int ticksCPU, int arriveTime, int priority) {
        this.name = name;
        this.ticksCPU = ticksCPU;
        this.arriveTime = arriveTime;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getTicksCPU() {
        return ticksCPU;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getPriority() {
        return priority;
    }
}

