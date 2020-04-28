package Ejercicio2;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import sun.awt.X11.XSystemTrayPeer;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    ArrayList<Persona> personas = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    String nombre;
    char sexo;
    double peso;
    double altura;
    int num = 0;

    public static void main(String[] args) {
        Main programa = new Main();
        programa.inicio();
    }

    public void inicio() {
        int opcion = 0;
        do {
            System.out.println("Elige la opción:");
            System.out.println("1. Añadir. \n2. Consultar IMC. \n3. Mostrar resumen. \n4. Salir.");
            if (input.hasNextInt()) {
                opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.println("Introduce los datos");
                        nombre = introduceNombre();
                        sexo = introduceSexo();
                        peso = introducePeso();
                        altura = introduceAltura();
                        añadir(nombre, sexo, peso, altura);
                        mostrarPersonas();
                        break;
                    case 2:
                        if(estaVacio()){
                            break;
                        }else{
                        mostrarPersonas();
                        System.out.println("¿Qué ID quieres consultar");
                        getnum();
                        mostrarIMC();
                        break;}
                    case 3:
                        if(estaVacio()) {
                            break;
                        }else{
                        mostrarPorcentajes();
                        break;}
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
        } while (!sexoIntroducido.equals("H")  && !sexoIntroducido.equals("M") );
        return sexoIntroducido.charAt(0);
    }

    public double introducePeso() {
        boolean exit = false;
        double pesoIntroducido = -1;
        do {
            System.out.println("Introduce el peso");
            boolean isNum = input.hasNextDouble();
            if (isNum) {
                pesoIntroducido = input.nextDouble();
                if (pesoIntroducido > 0) {
                    exit = true;
                } else {
                    System.out.println("Introduce un número mayor a 0");
                }
            } else {
                System.out.println("Introduce un entero");
                input.nextLine();
            }
        } while (!exit);
        return pesoIntroducido;
    }

    public double introduceAltura() {
        boolean exit = false;
        double alturaIntroducido = -1;
        do {
            System.out.println("Introduce la altura");
            boolean isNum = input.hasNextDouble();
            if (isNum) {
                alturaIntroducido = input.nextDouble();
                if (alturaIntroducido > 0) {
                    exit = true;
                } else {
                    System.out.println("Introduce un número mayor a 0");
                }
            } else {
                System.out.println("Introduce un entero");
                input.nextLine();
            }
        } while (!exit);
        return alturaIntroducido;
    }
    public void añadir(String nombre, char sexo, double peso, double altura){
        Persona persona = new Persona(nombre, sexo, peso, altura);
        personas.add(persona);
    }
    public void mostrarPersonas(){
        for(int i =0; i < personas.size(); i++){
            System.out.println(personas.get(i).toString());
        }
    }
    public int getnum(){
        num = input.nextInt();
        input.nextLine();
        return num;
    }
    public boolean estaVacio() {
        if (personas.isEmpty()) {
            System.out.println("No hay datos introducidos");
            return true;
        } else {
            return false;
        }
    }
    public void mostrarIMC(){
        int index = -1;
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getId() == num) {
                index = i;
            }
        }
                if(personas.get(index).getId() == num) {
                switch (personas.get(index).calcularIMC()) {
                case 0:
                    System.out.println("Peso ideal");
                    break;
                case -1:
                    System.out.println("Por debajo del peso ideal");
                    break;
                case 1:
                    System.out.println("Sobrepeso");
                    break;
            }
        }else{
                    System.out.println("Usuario no encontrado");
                }
    }
    public void mostrarPorcentajes(){
       double pIdeal = 0;
       double pBajoIdeal = 0;
       double sobrepeso = 0;
       for(int i = 0; i < personas.size(); i++){
           switch (personas.get(i).calcularIMC()) {
               case 0:
                   pIdeal++;
                   break;
               case -1:
                   pBajoIdeal++;
                   break;
               case 1:
                   sobrepeso++;
                   break;
           }
       System.out.println("El % de peso ideal es :"+(pIdeal/personas.size())*100);
           System.out.println("El % de peso por debajo del ideal es :"+(pBajoIdeal/personas.size())*100);
           System.out.println("El % de sobrepeso es :"+(sobrepeso/personas.size())*100);

       }
        }
    }