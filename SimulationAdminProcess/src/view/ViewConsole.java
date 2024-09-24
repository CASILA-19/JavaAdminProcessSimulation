package view;

import java.util.Scanner;

public class ViewConsole {
    private Scanner sc;

    public ViewConsole() {
        sc = new Scanner(System.in);
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public String readString(String message){
        showMessage(message);
        return sc.nextLine();
    }

    public int readInt(String message){
        return Integer.parseInt(readString(message));
    }
}
