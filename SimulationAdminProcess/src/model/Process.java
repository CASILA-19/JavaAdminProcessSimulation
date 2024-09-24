package model;

public class Process {
    private final String name;
    private int ticksCPU;
    private int arriveTime;
    private int priority;
    private int initialTicksCPU;  // Nuevo campo para almacenar el tiempo total inicial
    
    public Process(String name, int ticksCPU, int arriveTime, int priority) {
        this.name = name;
        this.ticksCPU = ticksCPU;
        this.arriveTime = arriveTime;
        this.priority = priority;
        this.initialTicksCPU = ticksCPU;  // Guardamos el tiempo total inicial al crear el proceso
    }

    public void setTicksCPU(int ticksCPU) {
        this.ticksCPU = ticksCPU;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void setPriority(int priority) {
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

    public int getInitialTicksCPU() {return initialTicksCPU;
    }
}

