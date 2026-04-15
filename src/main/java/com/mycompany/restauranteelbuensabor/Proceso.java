package com.mycompany.restauranteelbuensabor;

public class Proceso {
    private final Factura factura;

    public Proceso(Factura factura) {
        this.factura = factura;
    }

    public double calcularTotalFactura() {
        return factura.calcularTotal();
    }
}
