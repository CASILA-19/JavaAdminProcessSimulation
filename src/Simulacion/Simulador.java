package Simulacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Simulador {
    private List<Proceso> procesos;

    public Simulador(List<Proceso> procesos) {
        this.procesos = procesos;
    }

    public void seleccionarAlgoritmo(int opcion) {
        switch (opcion) {
            case 1:
                simularFCFS();
                break;
            case 2:
                simularSJF();
                break;
            case 3:
            	simularSJFExpropiativo();
                break;
            case 4:
            	simularPrioridad();
            	break;
            case 5:
                simularRoundRobin();
                break;
            default:
                System.out.println("Algoritmo no válido.");
        }
    }

    private void simularFCFS() {
        System.out.println("Simulación FCFS (First Come First Served)");

        procesos.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));

        int tiempoActual = 0;
        int sumaTiempoRespuesta = 0;
        int sumaTiempoEspera = 0; 

        for (Proceso proceso : procesos) {
            if (tiempoActual < proceso.getTiempoLlegada()) {
                tiempoActual = proceso.getTiempoLlegada();
            }

            int tiempoInicio = tiempoActual;

            tiempoActual += proceso.getTicksCPU();

            int tiempoFinal = tiempoActual;

            int tiempoRespuesta = tiempoFinal - proceso.getTiempoLlegada();

            int tiempoEspera = tiempoRespuesta - proceso.getTicksCPU();

            sumaTiempoRespuesta += tiempoRespuesta;
            sumaTiempoEspera += tiempoEspera;

            System.out.println(proceso.getNombre() + " - Inicio: " + tiempoInicio +
                    ", Fin: " + tiempoFinal +
                    ", T (Tiempo de Respuesta): " + tiempoRespuesta +
                    ", E (Tiempo de Espera): " + tiempoEspera);
        }

        double tiempoPromedioRespuesta = (double) sumaTiempoRespuesta / procesos.size();
        double tiempoPromedioEspera = (double) sumaTiempoEspera / procesos.size();

        System.out.println("Tiempo promedio de respuesta: " + tiempoPromedioRespuesta);
        System.out.println("Tiempo promedio de espera: " + tiempoPromedioEspera);
    }



    private void simularSJF() {
        System.out.println("Simulación SJF (Shortest Job First)");

        int tiempoActual = 0;
        int sumaTiempoRespuesta = 0;
        int sumaTiempoEspera = 0;

        List<Proceso> procesosEjecutados = new ArrayList<>();
        List<Proceso> procesosListos = new ArrayList<>(procesos);

        while (procesosEjecutados.size() < procesos.size()) {
            List<Proceso> listos = new ArrayList<>();
            for (Proceso p : procesosListos) {
                if (p.getTiempoLlegada() <= tiempoActual && !procesosEjecutados.contains(p)) {
                    listos.add(p);
                }
            }

            if (listos.isEmpty()) {
                tiempoActual++;
                continue;
            }

            Proceso proceso = listos.stream()
                .min(Comparator.comparingInt(Proceso::getTicksCPU))
                .orElseThrow();

            int tiempoInicio = tiempoActual;
            tiempoActual += proceso.getTicksCPU();
            int tiempoFinal = tiempoActual;

            int tiempoRespuesta = tiempoFinal - proceso.getTiempoLlegada();
            int tiempoEspera = tiempoRespuesta - proceso.getTicksCPU();

            sumaTiempoRespuesta += tiempoRespuesta;
            sumaTiempoEspera += tiempoEspera;

            procesosEjecutados.add(proceso);
            System.out.println(proceso.getNombre() + " - Inicio: " + tiempoInicio +
                    ", Fin: " + tiempoFinal +
                    ", T (Tiempo de Respuesta): " + tiempoRespuesta +
                    ", E (Tiempo de Espera): " + tiempoEspera);
        }

        double tiempoPromedioRespuesta = (double) sumaTiempoRespuesta / procesos.size();
        double tiempoPromedioEspera = (double) sumaTiempoEspera / procesos.size();

        System.out.println("Tiempo promedio de respuesta: " + tiempoPromedioRespuesta);
        System.out.println("Tiempo promedio de espera: " + tiempoPromedioEspera);
    }
    
    private void simularSJFExpropiativo() {
    	
    }


    private void simularPrioridad() {
        System.out.println("Simulación de Prioridad");

        int tiempoActual = 0;
        int sumaTiempoRespuesta = 0;
        int sumaTiempoEspera = 0;

        List<Proceso> procesosEjecutados = new ArrayList<>();
        List<Proceso> procesosListos = new ArrayList<>(procesos);

        while (procesosEjecutados.size() < procesos.size()) {
            List<Proceso> listos = new ArrayList<>();
            for (Proceso p : procesosListos) {
                if (p.getTiempoLlegada() <= tiempoActual && !procesosEjecutados.contains(p)) {
                    listos.add(p);
                }
            }

            if (listos.isEmpty()) {
                tiempoActual++;
                continue;
            }

            Proceso proceso = listos.stream()
                .min(Comparator.comparingInt(Proceso::getPrioridad))
                .orElseThrow();

            int tiempoInicio = tiempoActual;
            tiempoActual += proceso.getTicksCPU();
            int tiempoFinal = tiempoActual;

            int tiempoRespuesta = tiempoFinal - proceso.getTiempoLlegada();
            int tiempoEspera = tiempoRespuesta - proceso.getTicksCPU();

            sumaTiempoRespuesta += tiempoRespuesta;
            sumaTiempoEspera += tiempoEspera;

            procesosEjecutados.add(proceso);
            System.out.println(proceso.getNombre() + " - Inicio: " + tiempoInicio +
                    ", Fin: " + tiempoFinal +
                    ", T (Tiempo de Respuesta): " + tiempoRespuesta +
                    ", E (Tiempo de Espera): " + tiempoEspera);
        }

        double tiempoPromedioRespuesta = (double) sumaTiempoRespuesta / procesos.size();
        double tiempoPromedioEspera = (double) sumaTiempoEspera / procesos.size();

        System.out.println("Tiempo promedio de respuesta: " + tiempoPromedioRespuesta);
        System.out.println("Tiempo promedio de espera: " + tiempoPromedioEspera);
    }




    private void simularRoundRobin() {
        System.out.println("Simulación Round Robin");

//        int quantum = (int) (0.8 * calcularPromedioTicksCPU());
        int quantum = 3;
        int tiempoActual = 0;
        int sumaTiempoRespuesta = 0;
        int sumaTiempoEspera = 0;

        Queue<Proceso> cola = new LinkedList<>();
        Map<Proceso, Integer> tiemposRestantes = new HashMap<>();
        List<Proceso> procesosPendientes = new ArrayList<>(procesos);

        for (Proceso p : procesos) {
            tiemposRestantes.put(p, p.getTicksCPU());
        }

        List<Proceso> procesosEjecutados = new ArrayList<>();
        Map<Proceso, Integer> tiempoLlegadaEfectivo = new HashMap<>();
        Map<Proceso, Integer> tiemposDeInicio = new HashMap<>();
        Map<Proceso, Integer> tiemposDeEspera = new HashMap<>();
        
        for (Proceso p : procesos) {
            tiemposDeEspera.put(p, 0);
        }

        while (procesosEjecutados.size() < procesos.size()) {
            for (Proceso p : procesosPendientes) {
                if (p.getTiempoLlegada() <= tiempoActual && !cola.contains(p) && !procesosEjecutados.contains(p)) {
                    cola.add(p);
                    tiempoLlegadaEfectivo.put(p, tiempoActual); // El momento en que realmente entra a la cola
                }
            }

            if (cola.isEmpty()) {
                tiempoActual++;
                continue;
            }

            Proceso proceso = cola.poll();
            int tiempoRestante = tiemposRestantes.get(proceso);

            int tiempoInicio = tiempoActual;

            int tiempoEjecucion = Math.min(quantum, tiempoRestante);
            tiempoRestante -= tiempoEjecucion;
            tiempoActual += tiempoEjecucion;

            int tiempoFin = tiempoActual;

            if (!tiemposDeInicio.containsKey(proceso)) {
                tiemposDeInicio.put(proceso, tiempoInicio); 
            } else {
                int tiempoEspera = tiempoInicio - tiempoFin + tiempoEjecucion;
                tiemposDeEspera.put(proceso, tiemposDeEspera.get(proceso) + tiempoEspera);
            }

            if (tiempoRestante == 0) {
                int tiempoRespuesta = tiempoActual - proceso.getTiempoLlegada();
                int tiempoEspera = tiempoRespuesta - proceso.getTicksCPU(); 

                sumaTiempoRespuesta += tiempoRespuesta;
                sumaTiempoEspera += tiempoEspera;

                procesosEjecutados.add(proceso);
                System.out.println(proceso.getNombre() + " - Inicio: " + tiemposDeInicio.get(proceso) + ", Fin: " + tiempoFin + 
                        ", Tiempo de Respuesta: " + tiempoRespuesta + ", Tiempo de Espera: " + tiempoEspera);
            } else {
                tiemposRestantes.put(proceso, tiempoRestante);
                cola.add(proceso);
                System.out.println(proceso.getNombre() + " - Inicio: " + tiempoInicio + ", Fin: " + tiempoFin + 
                        ", Tiempo restante: " + tiempoRestante);
            }
        }

        double tiempoPromedioRespuesta = (double) sumaTiempoRespuesta / procesos.size();
        double tiempoPromedioEspera = (double) sumaTiempoEspera / procesos.size();

        System.out.println("Tiempo promedio de respuesta: " + tiempoPromedioRespuesta);
        System.out.println("Tiempo promedio de espera: " + tiempoPromedioEspera);
    }

    private double calcularPromedioTicksCPU() {
        return procesos.stream().mapToInt(Proceso::getTicksCPU).average().orElse(0);
    }



}
