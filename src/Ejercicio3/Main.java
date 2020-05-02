package Ejercicio3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Main programa = new Main();
        programa.inicio();
    }

    public void inicio() {
        ArrayList<Venta> ventas = new ArrayList<>();
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
                        introducirVenta(ventas);
                        break;
                    case 2:
                        mostrarVentasDeUnMes(ventas);
                        break;
                    case 3:
                        mostrarVentasDeUnArticulo(ventas);
                        break;
                    case 4:
                        borrarUnaVenta(ventas);
                        break;
                    case 5:
                        modificarElImporteDeUnaVenta(ventas);
                        break;
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

    private void modificarElImporteDeUnaVenta(ArrayList<Venta> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay registros");
        } else {
            mostrarVentas(lista);
            System.out.println("¿Qué venta quieres modificar?");
            int index = getindex(lista);
            System.out.println("¿Qué importe quieres añadir?");
            double nuevoPrecio = getNuevoPrecio();
            modificarPrecio(lista, index, nuevoPrecio);
        }
    }

    private void borrarUnaVenta(ArrayList<Venta> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay registros");
        } else {
            mostrarVentas(lista);
            System.out.println("¿Qué venta quieres borrar?");
            int index = getindex(lista);
            borrarVenta(lista, index);
        }
    }

    private void mostrarVentasDeUnArticulo(ArrayList<Venta> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay registros");
        } else {
            System.out.println("¿Qué artículo quieres consultar?");
            String nombre = getnombre();
            mostrarVentasArticulo(lista, nombre);
        }
    }

    private void mostrarVentasDeUnMes(ArrayList<Venta> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay registros");
        } else {
            System.out.println("¿Qué mes quieres consultar?");
            int numMes = getnumMes();
            mostrarVentasMes(lista, numMes);
        }
    }

    private void introducirVenta(ArrayList<Venta> lista) {
        System.out.println("Introduce los datos");
        String articulo = introduceArticulo();
        int mes = introduceMes();
        double importe = introduceImporte();
        añadirVenta(articulo, mes, importe, lista);
    }

    public String introduceArticulo() {
        String articuloIntroducido = "";
        do {
            System.out.println("Introduce articulo");
            articuloIntroducido = input.nextLine().trim();
        } while (articuloIntroducido.isEmpty() ||
                (!articuloIntroducido.equals("running") && !articuloIntroducido.equals("asfalto") &&
                        !articuloIntroducido.equals("triatlon") && !articuloIntroducido.equals("trial")));
        return articuloIntroducido;
    }

    public int introduceMes() {
        int mesIntroducido = -1;
        do {
            System.out.println("Introduce el mes");
            boolean isNum = input.hasNextInt();
            if (isNum) {
                mesIntroducido = input.nextInt();
                input.nextLine();
                if (mesIntroducido <= 0 || mesIntroducido > 12) {
                    System.out.println("Introduce un número entre 1 y 12");
                }
            } else {
                System.out.println("Introduce un entero");
                input.nextLine();
            }
        } while (mesIntroducido <= 0 || mesIntroducido > 12);
        return mesIntroducido;
    }

    public double introduceImporte() {
        double importeIntroducido = -1;
        do {
            System.out.println("Introduce el importe");
            boolean isNum = input.hasNextDouble();
            if (isNum) {
                importeIntroducido = input.nextDouble();
                if (importeIntroducido <= 0) {
                    System.out.println("Introduce un número mayor a 0");
                }
            } else {
                System.out.println("Introduce un numero");
                input.nextLine();
            }
        } while (importeIntroducido <= 0);
        return importeIntroducido;
    }

    public void añadirVenta(String articulo, int mes, double importe, ArrayList<Venta> lista) {
        Venta venta = new Venta(articulo, mes, importe);
        lista.add(venta);
    }

    public int getnumMes() {
        int numMes = -1;
        do {
            boolean isNum = input.hasNextInt();
            if (isNum) {
                numMes = input.nextInt();
                input.nextLine();
                if (numMes <= 0 || numMes > 12){
                    System.out.println("Introduce un número entre 1 y 12");
                }
            } else {
                System.out.println("Introduce un entero");
                input.nextLine();
            }
        } while (numMes <= 0 || numMes > 12);
        return numMes;
    }

    public void mostrarVentasMes(ArrayList<Venta> lista, int numMes) {
        double ventasRunning = 0;
        double ventasAsfalto = 0;
        double ventasTriatlon = 0;
        double ventasTrial = 0;
        double ventasTotalesMes = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getMes() == numMes) {
                ventasTotalesMes += lista.get(i).getImporte();
                switch (lista.get(i).getArticulo()) {
                    case "running":
                        ventasRunning += lista.get(i).getImporte();
                        break;
                    case "asfalto":
                        ventasAsfalto += lista.get(i).getImporte();
                        break;
                    case "triatlon":
                        ventasTriatlon += lista.get(i).getImporte();
                        break;
                    case "trial":
                        ventasTrial += lista.get(i).getImporte();
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
            System.out.println("Introduce articulo");
            nombre = input.nextLine().trim();
        } while (nombre.isEmpty() ||
                (!nombre.equals("running") && !nombre.equals("asfalto") &&
                        !nombre.equals("triatlon") && !nombre.equals("trial")));
        return nombre;
    }

    public void mostrarVentasArticulo(ArrayList<Venta> lista, String nombre) {
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
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getArticulo().equals(nombre)) {
                ventasTotalesArticulo += lista.get(i).getImporte();
                switch (lista.get(i).getMes()) {
                    case 1:
                        ventasEnero += lista.get(i).getImporte();
                        break;
                    case 2:
                        ventasFebrero += lista.get(i).getImporte();
                        break;
                    case 3:
                        ventasMarzo += lista.get(i).getImporte();
                        break;
                    case 4:
                        ventasAbril += lista.get(i).getImporte();
                        break;
                    case 5:
                        ventasMayo += lista.get(i).getImporte();
                        break;
                    case 6:
                        ventasJunio += lista.get(i).getImporte();
                        break;
                    case 7:
                        ventasJulio += lista.get(i).getImporte();
                        break;
                    case 8:
                        ventasAgosto += lista.get(i).getImporte();
                        break;
                    case 9:
                        ventasSeptiembre += lista.get(i).getImporte();
                        break;
                    case 10:
                        ventasOctubre += lista.get(i).getImporte();
                        break;
                    case 11:
                        ventasNoviembre += lista.get(i).getImporte();
                        break;
                    case 12:
                        ventasDiciembre += lista.get(i).getImporte();
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

    public void mostrarVentas(ArrayList<Venta> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + " - " + lista.get(i).toString());
        }
    }

    public int getindex(ArrayList<Venta> lista) {
        int index = -1;
        do {
            boolean isNum = input.hasNextInt();
            if (isNum) {
                index = input.nextInt();
                if (index < 0 || index >= lista.size()){
                    System.out.println("Introduce un número correcto");
                }
            } else {
                System.out.println("Introduce un entero");
                input.nextLine();
            }
        } while (index < 0 || index >= lista.size());
        return index;
    }

    public void borrarVenta(ArrayList<Venta> lista, int index){
        lista.remove(index);
        System.out.println("venta borrada");
    }

    public double getNuevoPrecio() {
        double nuevoPrecio = -1;
        do {
            boolean isNum = input.hasNextDouble();
            if (isNum) {
                 nuevoPrecio = input.nextDouble();
                if (nuevoPrecio <= 0) {
                    System.out.println("Introduce un número mayor a 0");
                }
            } else {
                System.out.println("Introduce un numero");
                input.nextLine();
            }
        } while (nuevoPrecio <= 0);
        return nuevoPrecio;
    }

    public void modificarPrecio(ArrayList<Venta> lista, int index, double nuevoPrecio) {
        lista.get(index).setImporte(nuevoPrecio);
        System.out.println("Venta modificada");
    }


}



