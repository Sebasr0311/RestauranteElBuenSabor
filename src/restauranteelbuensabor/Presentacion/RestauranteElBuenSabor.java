package restauranteelbuensabor.Presentacion;

import restauranteelbuensabor.*;
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

        int opcionMenu = 0;
        boolean sistemaActivo = true;
        int intentosInvalidos = 0;
        int numeroFactura = 1;

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

            if (opcionMenu == 1) {
                facturaImpresor.mostrarCarta();
                System.out.println();
            } else if (opcionMenu == 2) {
                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print("Numero de producto (1-" + menuRestaurante.getCantidadProductos() + "): ");
                int numeroProducto = scanner.nextInt();
                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();

                if (numeroProducto > 0 && numeroProducto <= menuRestaurante.getCantidadProductos()) {
                    if (cantidad > 0) {
                        if (!mesa.getEstadoMesa()) {
                            System.out.print("Ingrese numero de mesa: ");
                            int numeroMesaActual = scanner.nextInt();
                            mesa.activar(numeroMesaActual);
                        }

                        Producto producto = menuRestaurante.getProductoPorNumero(numeroProducto);
                        pedido.agregarItem(producto, cantidad);
                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + producto.getNombre() + " x" + cantidad);
                    } else {
                        if (cantidad == 0) {
                            System.out.println("La cantidad no puede ser cero.");
                        } else {
                            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
                        }
                    }
                } else {
                    if (numeroProducto <= 0) {
                        System.out.println("El numero debe ser mayor a cero.");
                    } else {
                        System.out.println(
                            "Producto no existe. La carta tiene " + menuRestaurante.getCantidadProductos() + " productos."
                        );
                    }
                }
                System.out.println();
            } else if (opcionMenu == 3) {
                System.out.println();
                if (Utilidades.hayProductosEnPedido(pedido)) {
                    facturaImpresor.mostrarPedido(pedido);
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                }
                System.out.println();
            } else if (opcionMenu == 4) {
                System.out.println();
                if (Utilidades.hayProductosEnPedido(pedido)) {
                    Factura factura = new Factura(pedido, numeroFactura);
                    factura.calcularTotal();
                    facturaImpresor.imprimirFacturaCompleta(factura);
                    numeroFactura++;
                    mesa.desactivar();
                    System.out.println();
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                }
            } else if (opcionMenu == 5) {
                System.out.println();
                pedido.limpiar();
                mesa.reiniciar();
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();
            } else if (opcionMenu == 0) {
                sistemaActivo = false;
                System.out.println("Hasta luego!");
            } else {
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
