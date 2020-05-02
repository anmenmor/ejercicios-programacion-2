package Ejercicio1;

public class Cuenta {
    private String titular;
    private double saldo;

    public Cuenta(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public Cuenta(String titular) {
        this.titular = titular;
        this.saldo = 0;
    }
    public double getSaldo() {
        return saldo;
    }
    public double ingresar(double cantidad){
        if(cantidad >= 0){
           saldo += cantidad;
        }
        return saldo;
    }
    public double retirar(double cantidad){
        if(cantidad < 0){
            return saldo;
        }else{
            saldo -= cantidad;
            if(saldo < 0){
                saldo = 0;
            }
            return saldo;
        }
    }

}

