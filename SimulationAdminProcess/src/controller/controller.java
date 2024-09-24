package controller;

import model.Process;
import model.Simulator;
import view.ViewConsole;

public class controller {
    private final String menu = "Seleccione el algoritmo de planificación:\n" +
            "1. FCFS.\n" +
            "2. SJF no expropiativo.\n" +
            "3. SJF expropiativo.\n" +
            "4. Prioridad.\n" +
            "5. Round Robin.\n" +
            "6. Salir.\n";
    private ViewConsole vConsole;
    private Simulator sim;

    public controller() {
        vConsole = new ViewConsole();
        sim = new Simulator();
        inpProcesses();
        init();
    }

    private void inpProcesses() {
        int numProcess = vConsole.readInt("Ingrese el numero de procesos: ");
        for (int i = 0; i < numProcess; i++) {
            String processName = vConsole.readString("Ingrese el nombre del proceso " + (i + 1) + ": ");
            int processTicks = vConsole.readInt("Ingrese los ticks de CPU: ");
            int processArrTime = vConsole.readInt("Ingrese el tiempo de llegada: ");
            int processPriority = vConsole.readInt("Ingrese la prioridad: ");
            sim.addProcess(new Process(processName, processTicks, processArrTime, processPriority));
        }
    }

    public void init() {
        boolean aux = true;
        while (aux) {
            int option = vConsole.readInt(menu);
            vConsole.showMessage(sim.seleccionarAlgoritmo(option));
            if (option == 6) {
                aux = false;
            }
        }
    }

    public static void main(String[] args) {
        new controller();
    }
}
