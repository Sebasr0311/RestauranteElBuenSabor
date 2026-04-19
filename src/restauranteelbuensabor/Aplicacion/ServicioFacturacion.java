package restauranteelbuensabor.Aplicacion;

import restauranteelbuensabor.Dominio.Factura;
import restauranteelbuensabor.Dominio.MenuRestaurante;
import restauranteelbuensabor.Dominio.Mesa;
import restauranteelbuensabor.Dominio.Pedido;
import restauranteelbuensabor.Dominio.Producto;
import restauranteelbuensabor.Infraestructura.FacturaImpresor;

public class ServicioFacturacion {

    private final MenuRestaurante menuRestaurante;
    private final Pedido pedido;
    private final Mesa mesa;
    private int numeroFactura;

    public ServicioFacturacion(MenuRestaurante menuRestaurante, FacturaImpresor facturaImpresor, Pedido pedido, Mesa mesa) {
        this.menuRestaurante = menuRestaurante;
        this.pedido = pedido;
        this.mesa = mesa;
        this.numeroFactura = 1;
    }

    public void agregarProducto(int numeroProducto, int cantidad, int numeroMesa) {

        if (numeroProducto <= 0 || numeroProducto > menuRestaurante.getCantidadProductos()) {
            throw new IllegalArgumentException("Producto no existe.");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva.");
        }

        if (!mesa.getEstadoMesa()) {
            mesa.activar(numeroMesa);
        }

        Producto producto = menuRestaurante.getProductoPorNumero(numeroProducto);
        pedido.agregarItem(producto, cantidad);
    }

    public boolean tieneProductosEnPedido() {
        return pedido.tieneProductos();
    }

    public Factura generarFactura() {

        if (!pedido.tieneProductos()) {
            throw new IllegalStateException("No hay productos en el pedido.");
        }

        Factura factura = new Factura(pedido, numeroFactura);
        numeroFactura++;
        mesa.desactivar();

        return factura;
    }

    public void reiniciarMesa() {
        pedido.limpiar();
        mesa.reiniciar();
    }

    public int getCantidadProductosMenu() {
        return menuRestaurante.getCantidadProductos();
    }

    public Pedido obtenerPedido() {
        return pedido;
    }
}
