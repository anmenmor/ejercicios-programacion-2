package Ejercicio3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    Scanner input = new Scanner(System.in);
    ArrayList<Venta> ventas = new ArrayList<>();
    String articulo;
    int mes;
    double importe;
    int num = 0;
    String nombre = null;
    int index = -1;
    double nuevoPrecio = -1;

    public static void main(String[] args) {
        Ejercicio3.Main programa = new Ejercicio3.Main();
        programa.inicio();
    }

    public void inicio() {
        int opcion = 0;
        do {
            System.out.println("Elige la opción:");
            System.out.println("1. Introducir venta. \n2. Mostrar venta de un mes. \n3. Mostrar ventas de un articulo.");
            System.out.println("4. Borrar una venta. \n5. Modificar el importe de una venta. \n6. Salir");
            if (input.hasNextInt()) {
                opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.println("Introduce los datos");
                        articulo = introduceArticulo();
                        mes = introduceMes();
                        importe = introduceImporte();
                        añadirVenta(articulo, mes, importe);
                        break;
                    case 2:
                        if (estaVacio()) {
                            break;
                        } else {
                            System.out.println("¿Qué mes quieres consultar?");
                            num = getnumMes();
                            mostrarVentasMes();
                            break;
                        }
                    case 3:
                        if (estaVacio()) {
                            break;
                        } else {
                            System.out.println("¿Qué artículo quieres consultar?");
                            nombre = getnombre();
                            mostrarVentasArticulo();
                            break;
                        }
                    case 4:
                        if (estaVacio()) {
                            break;
                        } else {
                            mostrarVentas();
                            System.out.println("¿Qué venta quieres borrar?");
                            index = getindex();
                            borrarVenta();
                            break;
                        }
                    case 5:
                        if (estaVacio()) {
                            break;
                        } else {
                            mostrarVentas();
                            System.out.println("¿Qué venta quieres modificar?");
                            index = getindex();
                            System.out.println("¿Qué importe quieres añadir?");
                            nuevoPrecio = getNuevoPrecio();
                            modificarPrecio();

                            break;
                        }
                    case 6:
                        System.out.println("Saliendo.");
                        break;
                    default:
                        System.out.println("opción no válida");
                        break;
                }
            } else {
                System.out.println("Opción no válida. Introduce un entero");
                input.nextLine();
            }
        } while (opcion != 6);
    }

    public String introduceArticulo() {
        String articuloIntroducido = "";
        do {
            System.out.println("Introduce articulo");
            articuloIntroducido = input.nextLine().trim();
        } while (articuloIntroducido.isEmpty() || !articuloIntroducido.equals("running") && !articuloIntroducido.equals("asfalto") && !articuloIntroducido.equals("triatlon") && !articuloIntroducido.equals("trial"));
        return articuloIntroducido;
    }

    public int introduceMes() {
        boolean exit = false;
        int mesIntroducido = -1;
        do {
            System.out.println("Introduce el mes");
            boolean isNum = input.hasNextInt();
            if (isNum) {
                mesIntroducido = input.nextInt();
                if (mesIntroducido > 0 && mesIntroducido < 12) {
                    exit = true;
                } else {
                    System.out.println("Introduce un número entre 1 y 12");
                }
            } else {
                System.out.println("Introduce un entero");
                input.nextLine();
            }
        } while (!exit);
        return mesIntroducido;
    }

    public double introduceImporte() {
        boolean exit = false;
        double importeIntroducido = -1;
        do {
            System.out.println("Introduce el importe");
            boolean isNum = input.hasNextDouble();
            if (isNum) {
                importeIntroducido = input.nextDouble();
                if (importeIntroducido > 0) {
                    exit = true;
                } else {
                    System.out.println("Introduce un número mayor a 0");
                }
            } else {
                System.out.println("Introduce un numero");
                input.nextLine();
            }
        } while (!exit);
        return importeIntroducido;
    }

    public void añadirVenta(String articulo, int mes, double importe) {
        Venta venta = new Venta(articulo, mes, importe);
        ventas.add(venta);
    }

    public boolean estaVacio() {
        if (ventas.isEmpty()) {
            System.out.println("No hay datos introducidos");
            return true;
        } else {
            return false;
        }
    }

    public int getnumMes() {
        boolean exit = false;
        int num = -1;
        do {
            boolean isNum = input.hasNextInt();
            if (isNum) {
                num = input.nextInt();
                if (num > 0 && num < 12) {
                    exit = true;
                } else {
                    System.out.println("Introduce un número entre 1 y 12");
                }
            } else {
                System.out.println("Introduce un entero");
                input.nextLine();
            }
        } while (!exit);
        return num;
    }

    public void mostrarVentasMes() {
        double ventasRunning = 0;
        double ventasAsfalto = 0;
        double ventasTriatlon = 0;
        double ventasTrial = 0;
        double ventasTotalesMes = 0;
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getMes() == num) {
                ventasTotalesMes += ventas.get(i).getImporte();
                switch (ventas.get(i).getArticulo()) {
                    case "running":
                        ventasRunning += ventas.get(i).getImporte();
                        break;
                    case "asfalto":
                        ventasAsfalto += ventas.get(i).getImporte();
                        break;
                    case "triatlon":
                        ventasTriatlon += ventas.get(i).getImporte();
                        break;
                    case "trial":
                        ventasTrial += ventas.get(i).getImporte();
                        break;
                }
            }
        }
        System.out.println("El total ventas de Running es: " + ventasRunning);
        System.out.println("El total ventas de Asfalto es: " + ventasAsfalto);
        System.out.println("El total ventas de Triatlon es: " + ventasTriatlon);
        System.out.println("El total ventas de Trial es: " + ventasTrial);
        System.out.println("Las ventas totales son: " + ventasTotalesMes);
    }

    public String getnombre() {
        String nombre = "";
        do {
            nombre = input.nextLine().trim();
        } while (nombre.isEmpty() || !nombre.equals("running") && !nombre.equals("asfalto") && !nombre.equals("triatlon") && !nombre.equals("trial"));
        return nombre;
    }

    public void mostrarVentasArticulo() {
        double ventasEnero = 0;
        double ventasFebrero = 0;
        double ventasMarzo = 0;
        double ventasAbril = 0;
        double ventasMayo = 0;
        double ventasJunio = 0;
        double ventasJulio = 0;
        double ventasAgosto = 0;
        double ventasSeptiembre = 0;
        double ventasOctubre = 0;
        double ventasNoviembre = 0;
        double ventasDiciembre = 0;
        double ventasTotalesArticulo = 0;
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getArticulo().equals(nombre)) {
                ventasTotalesArticulo += ventas.get(i).getImporte();
                switch (ventas.get(i).getMes()) {
                    case 1:
                        ventasEnero += ventas.get(i).getImporte();
                        break;
                    case 2:
                        ventasFebrero += ventas.get(i).getImporte();
                        break;
                    case 3:
                        ventasMarzo += ventas.get(i).getImporte();
                        break;
                    case 4:
                        ventasAbril += ventas.get(i).getImporte();
                        break;
                    case 5:
                        ventasMayo += ventas.get(i).getImporte();
                        break;
                    case 6:
                        ventasJunio += ventas.get(i).getImporte();
                        break;
                    case 7:
                        ventasJulio += ventas.get(i).getImporte();
                        break;
                    case 8:
                        ventasAgosto += ventas.get(i).getImporte();
                        break;
                    case 9:
                        ventasSeptiembre += ventas.get(i).getImporte();
                        break;
                    case 10:
                        ventasOctubre += ventas.get(i).getImporte();
                        break;
                    case 11:
                        ventasNoviembre += ventas.get(i).getImporte();
                        break;
                    case 12:
                        ventasDiciembre += ventas.get(i).getImporte();
                        break;

                }
            }
        }
        System.out.println("El total ventas de Enero es: " + ventasEnero);
        System.out.println("El total ventas de Febrero es: " + ventasFebrero);
        System.out.println("El total ventas de Marzo es: " + ventasMarzo);
        System.out.println("El total ventas de Abril es: " + ventasAbril);
        System.out.println("El total ventas de Mayo es: " + ventasMayo);
        System.out.println("El total ventas de Junio es: " + ventasJunio);
        System.out.println("El total ventas de Julio es: " + ventasJulio);
        System.out.println("El total ventas de Agosto es: " + ventasAgosto);
        System.out.println("El total ventas de Septiembre es: " + ventasSeptiembre);
        System.out.println("El total ventas de Octubre es: " + ventasOctubre);
        System.out.println("El total ventas de Noviembre es: " + ventasNoviembre);
        System.out.println("El total ventas de Diciembre es: " + ventasDiciembre);
        System.out.println("El total de ventas es: " + ventasTotalesArticulo);
    }

    public void mostrarVentas() {
        for (int i = 0; i < ventas.size(); i++) {
            System.out.println(i + " - " + ventas.get(i).toString());
        }
    }

    public int getindex() {
        boolean exit = false;
        do {
            boolean isNum = input.hasNextInt();
            if (isNum) {
                index = input.nextInt();
                if (index >= 0 && index <= ventas.size() - 1) {
                    exit = true;
                } else {
                    System.out.println("Introduce un número correcto");
                }
            } else {
                System.out.println("Introduce un entero");
                input.nextLine();
            }
        } while (!exit);
        return index;
    }

    public void borrarVenta(){
        ventas.remove(index);
        System.out.println("venta borrada");
    }

    public double getNuevoPrecio() {
        boolean exit = false;
        do {
            boolean isNum = input.hasNextDouble();
            if (isNum) {
                nuevoPrecio = input.nextDouble();
                if (nuevoPrecio > 0) {
                    exit = true;
                } else {
                    System.out.println("Introduce un número mayor a 0");
                }
            } else {
                System.out.println("Introduce un numero");
                input.nextLine();
            }
        } while (!exit);
        return nuevoPrecio;
    }

    public void modificarPrecio() {
        ventas.get(index).setImporte(nuevoPrecio);
        System.out.println("Venta modificada");
    }


}



