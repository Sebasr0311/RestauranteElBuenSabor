package com.mycompany.restauranteelbuensabor;

public class Factura {
    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_ITEMS_DESCUENTO = 3;

    private final Pedido pedido;
    private final int numeroFactura;

    public Factura(Pedido pedido, int numeroFactura) {
        this.pedido = pedido;
        this.numeroFactura = numeroFactura;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public double calcularSubtotal() {
        return pedido.calcularSubtotal();
    }

    public double calcularDescuento() {
        if (pedido.contarItemsDiferentes() > MIN_ITEMS_DESCUENTO) {
            return calcularSubtotal() * TASA_DESCUENTO;
        }
        return 0;
    }

    public double calcularSubtotalConDescuento() {
        return calcularSubtotal() - calcularDescuento();
    }

    public double calcularIVA() {
        return calcularIVA(calcularSubtotalConDescuento());
    }

    public double calcularIVA(double base) {
        return base * TASA_IVA;
    }

    public double calcularPropina() {
        double subtotalConDescuento = calcularSubtotalConDescuento();
        if (subtotalConDescuento > UMBRAL_PROPINA) {
            double baseConIva = subtotalConDescuento + calcularIVA(subtotalConDescuento);
            return baseConIva * TASA_PROPINA;
        }
        return 0;
    }

    public double calcularTotal() {
        double subtotalConDescuento = calcularSubtotalConDescuento();
        double iva = calcularIVA(subtotalConDescuento);
        double propina = 0;
        if (subtotalConDescuento > UMBRAL_PROPINA) {
            propina = (subtotalConDescuento + iva) * TASA_PROPINA;
        }
        return subtotalConDescuento + iva + propina;
    }
}
