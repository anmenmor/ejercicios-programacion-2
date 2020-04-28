package Ejercicio1;

import java.util.Scanner;

public class Main {
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Main programa = new Main();
        programa.inicio();
    }

    private void inicio() {
        Cuenta cuenta1 = new Cuenta("andrea");
        int opcion = 0;
        double saldo = cuenta1.getSaldo();
        do {
            System.out.println("Elige la opción:");
            System.out.println("1. Ingresar. \n2. Retirar. \n3. Salir.");
            if (input.hasNextInt()) {
                opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.println("saldo: " + saldo);
                        saldo = cuenta1.ingresar(getCantidad());
                        System.out.println("saldo: " + saldo);
                        break;
                    case 2:
                        System.out.println("saldo: " + saldo);
                        saldo = cuenta1.retirar(getCantidad());
                        System.out.println("saldo: " + saldo);
                        break;
                    case 3:
                        System.out.println("Saliendo");
                        break;
                    default:
                        System.out.println("opción no válida");
                        break;
                }
            } else {
                System.out.println("Opción no válida. Introduce un entero");
                input.nextLine();
            }
        } while (opcion != 3);
    }

    private double getCantidad() {
        boolean exit = false;
        double cantidad = 0;
        do {
            System.out.println("Cantidad: ");
            if (input.hasNextDouble()) {
                cantidad = input.nextDouble();
                exit = true;
            }
            input.nextLine();
        } while (!exit);
        return cantidad;
    }
}