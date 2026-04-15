package com.mycompany.restauranteelbuensabor;

public class Imprimir {
    private final FacturaImpresor facturaImpresor;

    public Imprimir(MenuRestaurante menuRestaurante) {
        facturaImpresor = new FacturaImpresor(menuRestaurante);
    }

    public void mostrarCarta() {
        facturaImpresor.mostrarCarta();
    }

    public void mostrarPedido(Pedido pedido) {
        facturaImpresor.mostrarPedido(pedido);
    }

    public void imprimirFacturaCompleta(Factura factura) {
        facturaImpresor.imprimirFacturaCompleta(factura);
    }

    public void imprimirFacturaResumen(Factura factura) {
        facturaImpresor.imprimirFacturaResumen(factura);
    }
}
