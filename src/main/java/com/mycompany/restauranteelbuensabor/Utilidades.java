package com.mycompany.restauranteelbuensabor;

public class Utilidades {
    private Utilidades() {
    }

    public static boolean hayProductosEnPedido(Pedido pedido) {
        return pedido.tieneProductos();
    }
}
