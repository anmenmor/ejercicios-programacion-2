package Ejercicio3;

public class Venta {

    private String articulo;
    private int mes;
    private double importe;

    public Venta(String articulo, int mes, double importe){
        this.articulo = articulo;
        this.mes = mes;
        this.importe = importe;
    }
    public int getMes() {
        return mes;
    }

    public String getArticulo() {
        return articulo;
    }

    public double getImporte() {
        return importe;
    }
    @Override
    public String toString() {
        return "Venta{" +
                "articulo='" + articulo + '\'' +
                ", mes=" + mes +
                ", importe=" + importe +
                '}';
    }
}
