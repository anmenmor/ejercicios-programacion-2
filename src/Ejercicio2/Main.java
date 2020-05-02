package Ejercicio2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Main programa = new Main();
        programa.inicio();
    }

    public void inicio() {
        ArrayList<Persona> personas = new ArrayList<>();
        int opcion = 0;
        do {
            System.out.println("Elige la opción:");
            System.out.println("1. Añadir. \n2. Consultar IMC. \n3. Mostrar resumen. \n4. Salir.");
            if (input.hasNextInt()) {
                opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 1:
                        introduceDatos(personas);
                        break;
                    case 2:
                        consultarIMC(personas);
                        break;
                    case 3:
                        mostrarResumen(personas);
                        break;
                    case 4:
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
        } while (opcion != 4);
    }

    private void mostrarResumen(ArrayList<Persona> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay registros");
        } else {
            mostrarPorcentajes(lista);
        }
    }

    private void consultarIMC(ArrayList<Persona> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay registros");
        } else {
            mostrarPersonas(lista);
            System.out.println("¿Qué ID quieres consultar");
            int numElemento = getnum();
            mostrarIMC(lista, numElemento);
        }
    }

    private void introduceDatos(ArrayList<Persona> lista) {
        System.out.println("Introduce los datos");
        String nombre = introduceNombre();
        char sexo = introduceSexo();
        double peso = introducePeso();
        double altura = introduceAltura();
        añadir(nombre, sexo, peso, altura, lista);
        mostrarPersonas(lista);
    }

    public String introduceNombre() {
        String nombreIntroducido = "";
        do {
            System.out.println("Introduce nombre");
            nombreIntroducido = input.nextLine().trim();
        } while (nombreIntroducido.isEmpty());
        return nombreIntroducido;
    }

    public char introduceSexo() {
        String sexoIntroducido = "";
        do {
            System.out.println("Introduce sexo: H Hombre, M Mujer");
            sexoIntroducido = input.next();
            input.nextLine();
        } while (!sexoIntroducido.equals("H") && !sexoIntroducido.equals("M"));
        return sexoIntroducido.charAt(0);
    }

    public double introducePeso() {
        double pesoIntroducido = -1;
        do {
            System.out.println("Introduce el peso");
            boolean isNum = input.hasNextDouble();
            if (isNum) {
                pesoIntroducido = input.nextDouble();
                input.nextLine();
                if (pesoIntroducido <= 0) {
                    System.out.println("Introduce un número mayor a 0");
                }
            } else {
                System.out.println("Introduce un numero");
                input.nextLine();
            }
        } while (pesoIntroducido <= 0);
        return pesoIntroducido;
    }

    public double introduceAltura() {
        double alturaIntroducido = -1;
        do {
            System.out.println("Introduce la altura");
            boolean isNum = input.hasNextDouble();
            if (isNum) {
                alturaIntroducido = input.nextDouble();
                input.nextLine();
                if (alturaIntroducido <= 0) {
                    System.out.println("Introduce un número mayor a 0");
                }
            } else {
                System.out.println("Introduce un numero");
                input.nextLine();
            }
        } while (alturaIntroducido <= 0);
        return alturaIntroducido;
    }

    public void añadir(String nombre, char sexo, double peso, double altura, ArrayList<Persona> lista) {
        Persona persona = new Persona(nombre, sexo, peso, altura);
        lista.add(persona);
    }

    public void mostrarPersonas(ArrayList<Persona> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
    }

    public int getnum() {
        int num = -1;
        do {
            if (input.hasNextInt()) {
                num = input.nextInt();
            } else {
                System.out.println("Introduce un número");
            }
            input.nextLine();
        } while (num == -1);
        return num;
    }

    public void mostrarIMC(ArrayList<Persona> lista, int numElemento) {
        int index = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == numElemento) {
                index = i;
            }
        }
        if (index != -1) {
            switch (lista.get(index).calcularIMC()) {
                case 0:
                    System.out.println("Por debajo del peso ideal");
                    break;
                case -1:
                    System.out.println("Peso ideal");
                    break;
                case 1:
                    System.out.println("Sobrepeso");
                    break;
            }
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    public void mostrarPorcentajes(ArrayList<Persona> lista) {
        double pIdeal = 0;
        double pBajoIdeal = 0;
        double sobrepeso = 0;
        for (int i = 0; i < lista.size(); i++) {
            switch (lista.get(i).calcularIMC()) {
                case 0:
                    pBajoIdeal++;
                    break;
                case -1:
                    pIdeal++;
                    break;
                case 1:
                    sobrepeso++;
                    break;
            }
        }
        System.out.println("El % de peso ideal es :" + (pIdeal / lista.size()) * 100);
        System.out.println("El % de peso por debajo del ideal es :" + (pBajoIdeal / lista.size()) * 100);
        System.out.println("El % de sobrepeso es :" + (sobrepeso / lista.size()) * 100);
    }
}