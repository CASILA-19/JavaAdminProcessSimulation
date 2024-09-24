package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Simulator {
    private ArrayList<Process> processes;

    public Simulator() {
        processes = new ArrayList<>();
    }

    public void addProcess(Process processTAdd) {
        processes.add(processTAdd);
    }

    public String seleccionarAlgoritmo(int opcion) {
        switch (opcion) {
            case 1:
                return simularFCFS();
            case 2:
                return simularSJF();
            case 3:
                return simularSJFExpropiativo();
            case 4:
                return simularPrioridad();
            case 5:
                return simularRoundRobin();
            case 6:
                return "Hasta pronto";
            default:
                return "Algoritmo no válido.";
        }
    }

    private String simularFCFS() {
        String result = "Simulación FCFS (First Come First Served)\n";

        processes.sort(Comparator.comparingInt(Process::getArriveTime));

        int tiempoActual = 0;
        int sumaTiempoRespuesta = 0;
        int sumaTiempoEspera = 0;

        for (Process proceso : processes) {
            if (tiempoActual < proceso.getArriveTime()) {
                tiempoActual = proceso.getArriveTime();
            }

            int tiempoInicio = tiempoActual;

            tiempoActual += proceso.getTicksCPU();

            int tiempoFinal = tiempoActual;

            int tiempoRespuesta = tiempoFinal - proceso.getArriveTime();

            int tiempoEspera = tiempoRespuesta - proceso.getTicksCPU();

            sumaTiempoRespuesta += tiempoRespuesta;
            sumaTiempoEspera += tiempoEspera;

            result += proceso.getName() + " - Inicio: " + tiempoInicio +
                    ", Fin: " + tiempoFinal +
                    ", T (Tiempo de Respuesta): " + tiempoRespuesta +
                    ", E (Tiempo de Espera): " + tiempoEspera + "\n";
        }

        double tiempoPromedioRespuesta = (double) sumaTiempoRespuesta / processes.size();
        double tiempoPromedioEspera = (double) sumaTiempoEspera / processes.size();

        result += "Tiempo promedio de respuesta: " + tiempoPromedioRespuesta + "\n";
        result += "Tiempo promedio de espera: " + tiempoPromedioEspera;

        return result;
    }

    private String simularSJF() {
        String result = "Simulación SJF (Shortest Job First)\n";

        int tiempoActual = 0;
        int sumaTiempoRespuesta = 0;
        int sumaTiempoEspera = 0;

        ArrayList<Process> procesosEjecutados = new ArrayList<>();
        ArrayList<Process> procesosListos = new ArrayList<>(processes);

        while (procesosEjecutados.size() < processes.size()) {
            ArrayList<Process> listos = new ArrayList<>();
            for (Process p : procesosListos) {
                if (p.getArriveTime() <= tiempoActual && !procesosEjecutados.contains(p)) {
                    listos.add(p);
                }
            }

            if (listos.isEmpty()) {
                tiempoActual++;
                continue;
            }

            Process proceso = listos.stream()
                    .min(Comparator.comparingInt(Process::getTicksCPU))
                    .orElseThrow();

            int tiempoInicio = tiempoActual;
            tiempoActual += proceso.getTicksCPU();
            int tiempoFinal = tiempoActual;

            int tiempoRespuesta = tiempoFinal - proceso.getArriveTime();
            int tiempoEspera = tiempoRespuesta - proceso.getTicksCPU();

            sumaTiempoRespuesta += tiempoRespuesta;
            sumaTiempoEspera += tiempoEspera;

            procesosEjecutados.add(proceso);
            result += proceso.getName() + " - Inicio: " + tiempoInicio +
                    ", Fin: " + tiempoFinal +
                    ", T (Tiempo de Respuesta): " + tiempoRespuesta +
                    ", E (Tiempo de Espera): " + tiempoEspera + "\n";
        }

        double tiempoPromedioRespuesta = (double) sumaTiempoRespuesta / processes.size();
        double tiempoPromedioEspera = (double) sumaTiempoEspera / processes.size();

        result += "Tiempo promedio de respuesta: " + tiempoPromedioRespuesta + "\n";
        result += "Tiempo promedio de espera: " + tiempoPromedioEspera;
        return result;
    }

    private String simularSJFExpropiativo() {
        String result = "Simulacion de SJF Expropiativo\n";

        return result;
    }

    private String simularPrioridad() {
        String result = "Simulación de Prioridad\n";

        int tiempoActual = 0;
        int sumaTiempoRespuesta = 0;
        int sumaTiempoEspera = 0;

        ArrayList<Process> procesosEjecutados = new ArrayList<>();
        ArrayList<Process> procesosListos = new ArrayList<>(processes);

        while (procesosEjecutados.size() < processes.size()) {
            ArrayList<Process> listos = new ArrayList<>();
            for (Process p : procesosListos) {
                if (p.getArriveTime() <= tiempoActual && !procesosEjecutados.contains(p)) {
                    listos.add(p);
                }
            }

            if (listos.isEmpty()) {
                tiempoActual++;
                continue;
            }

            Process proceso = listos.stream()
                    .min(Comparator.comparingInt(Process::getPriority))
                    .orElseThrow();

            int tiempoInicio = tiempoActual;
            tiempoActual += proceso.getTicksCPU();
            int tiempoFinal = tiempoActual;

            int tiempoRespuesta = tiempoFinal - proceso.getArriveTime();
            int tiempoEspera = tiempoRespuesta - proceso.getTicksCPU();

            sumaTiempoRespuesta += tiempoRespuesta;
            sumaTiempoEspera += tiempoEspera;

            procesosEjecutados.add(proceso);
            result += proceso.getName() + " - Inicio: " + tiempoInicio +
                    ", Fin: " + tiempoFinal +
                    ", T (Tiempo de Respuesta): " + tiempoRespuesta +
                    ", E (Tiempo de Espera): " + tiempoEspera + "\n";
        }

        double tiempoPromedioRespuesta = (double) sumaTiempoRespuesta / processes.size();
        double tiempoPromedioEspera = (double) sumaTiempoEspera / processes.size();

        result += "Tiempo promedio de respuesta: " + tiempoPromedioRespuesta + "\n";
        result += "Tiempo promedio de espera: " + tiempoPromedioEspera;
        return result;
    }

    private String simularRoundRobin() {
        String result = "Simulación Round Robin\n";

        // int quantum = (int) (0.8 * calcularPromedioTicksCPU());
        int quantum = 3;
        int tiempoActual = 0;
        int sumaTiempoRespuesta = 0;
        int sumaTiempoEspera = 0;

        Queue<Process> cola = new LinkedList<>();
        Map<Process, Integer> tiemposRestantes = new HashMap<>();
        ArrayList<Process> procesosPendientes = new ArrayList<>(processes);

        for (Process p : processes) {
            tiemposRestantes.put(p, p.getTicksCPU());
        }

        ArrayList<Process> procesosEjecutados = new ArrayList<>();
        Map<Process, Integer> tiempoLlegadaEfectivo = new HashMap<>();
        Map<Process, Integer> tiemposDeInicio = new HashMap<>();
        Map<Process, Integer> tiemposDeEspera = new HashMap<>();

        for (Process p : processes) {
            tiemposDeEspera.put(p, 0);
        }

        while (procesosEjecutados.size() < processes.size()) {
            for (Process p : procesosPendientes) {
                if (p.getArriveTime() <= tiempoActual && !cola.contains(p) && !procesosEjecutados.contains(p)) {
                    cola.add(p);
                    tiempoLlegadaEfectivo.put(p, tiempoActual); // El momento en que realmente entra a la cola
                }
            }

            if (cola.isEmpty()) {
                tiempoActual++;
                continue;
            }

            Process proceso = cola.poll();
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
                int tiempoRespuesta = tiempoActual - proceso.getArriveTime();
                int tiempoEspera = tiempoRespuesta - proceso.getTicksCPU();

                sumaTiempoRespuesta += tiempoRespuesta;
                sumaTiempoEspera += tiempoEspera;

                procesosEjecutados.add(proceso);
                result += proceso.getName() + " - Inicio: " + tiemposDeInicio.get(proceso) + ", Fin: " + tiempoFin +
                        ", Tiempo de Respuesta: " + tiempoRespuesta + ", Tiempo de Espera: " + tiempoEspera + "\n";
            } else {
                tiemposRestantes.put(proceso, tiempoRestante);
                cola.add(proceso);
                result += proceso.getName() + " - Inicio: " + tiempoInicio + ", Fin: " + tiempoFin +
                        ", Tiempo restante: " + tiempoRestante + "\n";
            }
        }

        double tiempoPromedioRespuesta = (double) sumaTiempoRespuesta / processes.size();
        double tiempoPromedioEspera = (double) sumaTiempoEspera / processes.size();

        result += "Tiempo promedio de respuesta: " + tiempoPromedioRespuesta + "\n";
        result += "Tiempo promedio de espera: " + tiempoPromedioEspera;
        return result;
    }

    private double calcularPromedioTicksCPU() {
        return processes.stream().mapToInt(Process::getTicksCPU).average().orElse(0);
    }
}
