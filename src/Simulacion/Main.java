package Simulacion;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Proceso> listaProcesos = new ArrayList<>();

        System.out.print("Ingrese el número de procesos: ");
        int numProcesos = scanner.nextInt();

        for (int i = 0; i < numProcesos; i++) {
            System.out.println("Proceso " + (i + 1) + ":");
            System.out.print("Nombre: ");
            String nombre = scanner.next();
            System.out.print("Ticks de CPU: ");
            int ticksCPU = scanner.nextInt();
            System.out.print("Tiempo de llegada: ");
            int tiempoLlegada = scanner.nextInt();
            System.out.print("Prioridad: ");
            int prioridad = scanner.nextInt();

            listaProcesos.add(new Proceso(nombre, ticksCPU, tiempoLlegada, prioridad));
        }
        
        /*listaProcesos.add(new Proceso("A", 3, 0, 10));
        listaProcesos.add(new Proceso("B", 5, 1, 3));
        listaProcesos.add(new Proceso("C", 2, 3, 2));
        listaProcesos.add(new Proceso("D", 5, 9, 4));
        listaProcesos.add(new Proceso("E", 5, 12, 5));*/

        System.out.println("Seleccione el algoritmo de planificación:");
        System.out.println("1. FCFS");
        System.out.println("2. SJF no expropiativo");
        System.out.println("3. SJF expropiativo");
        System.out.println("4. Prioridad");
        System.out.println("5. Round Robin");
        int opcion = scanner.nextInt();

        Simulador simulador = new Simulador(listaProcesos);
        simulador.seleccionarAlgoritmo(opcion);

    }
}
