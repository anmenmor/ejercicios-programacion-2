package Ejercicio2;

import java.util.Random;

public class Persona {

    private String nombre;
    private int id;
    private char sexo;
    private double peso;
    private double altura;

    public Persona(String nombre, char sexo, double peso, double altura){
        this.nombre = nombre;
        this.id = generarID();
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", sexo=" + sexo +
                ", peso=" + peso +
                ", altura=" + altura +
                '}';
    }
    private int generarID(){
        return new Random().nextInt(100);

    }
    public int calcularIMC(){
        double IMC = peso/Math.pow(altura,2);
        if(IMC < 20){
            return 0;
        }else if(IMC >= 20 && IMC <= 25){
            return -1;
        }else{
            return 1;
        }
    }
    public int getId() {
        return id;
    }

}
