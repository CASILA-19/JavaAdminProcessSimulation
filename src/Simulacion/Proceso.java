package Simulacion;

public class Proceso {
    private String nombre;
    private int ticksCPU;
    private int tiempoLlegada;
    private int prioridad;
    
    public Proceso(String nombre, int ticksCPU, int tiempoLlegada, int prioridad) {
        this.nombre = nombre;
        this.ticksCPU = ticksCPU;
        this.tiempoLlegada = tiempoLlegada;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTicksCPU() {
        return ticksCPU;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public int getPrioridad() {
        return prioridad;
    }
}

