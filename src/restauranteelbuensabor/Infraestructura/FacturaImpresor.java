package restauranteelbuensabor.Infraestructura;

import restauranteelbuensabor.Dominio.ItemPedido;
import restauranteelbuensabor.Dominio.Pedido;
import restauranteelbuensabor.Dominio.Producto;
import restauranteelbuensabor.Dominio.Factura;
import restauranteelbuensabor.Dominio.MenuRestaurante;

public class FacturaImpresor {
    private static final String SEPARADOR = "========================================";

    private final MenuRestaurante menuRestaurante;

    public FacturaImpresor(MenuRestaurante menuRestaurante) {
        this.menuRestaurante = menuRestaurante;
    }

    public void mostrarCarta() {
        System.out.println(SEPARADOR);
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR);
        int indice = 0;
        while (indice < menuRestaurante.getCantidadProductos()) {
            Producto producto = menuRestaurante.getProductos().get(indice);
            System.out.printf("%d. %-22s $%,.0f%n", (indice + 1), producto.getNombre(), producto.getPrecio());
            indice++;
        }
        System.out.println(SEPARADOR);
    }

    public void mostrarPedido(Pedido pedido) {
        double subtotal = 0;
        System.out.println("--- PEDIDO ACTUAL ---");
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf("%-20s x%-6d $%,.0f%n", item.getProducto().getNombre(), item.getCantidad(), item.calcularSubtotal());
            subtotal = subtotal + item.calcularSubtotal();
        }
        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    public void imprimirFacturaCompleta(Factura factura) {
        imprimirEncabezado(factura.getNumeroFactura(), "");
        System.out.println("----------------------------------------");
        for (ItemPedido item : factura.getPedido().getItems()) {
            System.out.printf("%-20s x%-6d $%,.0f%n", item.getProducto().getNombre(), item.getCantidad(), item.calcularSubtotal());
        }
        imprimirResumenTotales(factura);
    }

    public void imprimirFacturaResumen(Factura factura) {
        imprimirEncabezado(factura.getNumeroFactura(), " (RESUMEN)");
        imprimirResumenTotales(factura);
    }

    private void imprimirEncabezado(int numeroFactura, String sufijoTitulo) {
        System.out.println(SEPARADOR);
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    " + MenuRestaurante.DIRECCION);
        System.out.println("    NIT: " + MenuRestaurante.NIT);
        System.out.println(SEPARADOR);
        System.out.printf("FACTURA No. %03d%s%n", numeroFactura, sufijoTitulo);
    }

    private void imprimirResumenTotales(Factura factura) {
        double subtotalConDescuento = factura.calcularSubtotalConDescuento();
        double iva = factura.calcularIVA();
        double propina = factura.calcularPropina();
        double total = factura.calcularTotal();

        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", iva);
        if (propina > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", propina);
        }
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", total);
        System.out.println(SEPARADOR);
        System.out.println("Gracias por su visita!");
        System.out.println("El Buen Sabor - Valledupar");
        System.out.println(SEPARADOR);
    }
}
