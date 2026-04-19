package restauranteelbuensabor;

import restauranteelbuensabor.Dominio.Pedido;

public class Utilidades {
    private Utilidades() {
    }

    public static boolean hayProductosEnPedido(Pedido pedido) {
        return pedido.tieneProductos();
    }
}
