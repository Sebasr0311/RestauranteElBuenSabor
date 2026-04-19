package restauranteelbuensabor.Presentacion;

import restauranteelbuensabor.Aplicacion.ServicioFacturacion;
import restauranteelbuensabor.Dominio.*;
import restauranteelbuensabor.Infraestructura.FacturaImpresor;

import java.util.Scanner;

public class RestauranteElBuenSabor {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MenuRestaurante menuRestaurante = new MenuRestaurante();
        Pedido pedido = new Pedido();
        Mesa mesa = new Mesa();
        FacturaImpresor facturaImpresor = new FacturaImpresor(menuRestaurante);

        ServicioFacturacion servicio = new ServicioFacturacion(
                menuRestaurante,
                facturaImpresor,
                pedido,
                mesa
        );

        int opcionMenu;
        boolean sistemaActivo = true;
        int intentosInvalidos = 0;

        System.out.println("========================================");
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println("========================================");

        while (sistemaActivo) {

            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");

            opcionMenu = scanner.nextInt();

            switch (opcionMenu) {

                case 1:
                    facturaImpresor.mostrarCarta();
                    System.out.println();
                    break;

                case 2:
                    System.out.println("--- AGREGAR PRODUCTO ---");

                    System.out.print("Numero de producto (1-" + servicio.getCantidadProductosMenu() + "): ");
                    int numeroProducto = scanner.nextInt();

                    System.out.print("Cantidad: ");
                    int cantidad = scanner.nextInt();

                    System.out.print("Numero de mesa: ");
                    int numeroMesa = scanner.nextInt();

                    try {
                        servicio.agregarProducto(numeroProducto, cantidad, numeroMesa);
                        System.out.println("Producto agregado al pedido.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println();
                    break;

                case 3:
                    System.out.println();

                    if (servicio.tieneProductosEnPedido()) {
                        facturaImpresor.mostrarPedido(servicio.obtenerPedido());
                    } else {
                        System.out.println("No hay productos en el pedido actual.");
                        System.out.println("Use la opcion 2 para agregar productos.");
                    }

                    System.out.println();
                    break;

                case 4:
                    System.out.println();

                    try {
                        Factura factura = servicio.generarFactura();
                        facturaImpresor.imprimirFacturaCompleta(factura);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println();
                    break;

                case 5:
                    System.out.println();

                    servicio.reiniciarMesa();
                    System.out.println("Mesa reiniciada. Lista para nuevo cliente.");

                    System.out.println();
                    break;

                case 0:
                    sistemaActivo = false;
                    System.out.println("Hasta luego!");
                    break;

                default:
                    System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
                    intentosInvalidos++;

                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    }

                    if (intentosInvalidos > 3) {
                        System.out.println("Demasiados intentos invalidos.");
                        intentosInvalidos = 0;
                    }
            }
        }

        scanner.close();
    }
}

