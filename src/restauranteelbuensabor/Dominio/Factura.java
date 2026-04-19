package restauranteelbuensabor.Dominio;

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

    // El IVA se calcula sobre el subtotal después de aplicar descuentos
    public double calcularIVA() {
        return calcularSubtotalConDescuento() * TASA_IVA;
    }

    // La propina aplica solo si el consumo supera el umbral definido.
    // Se calcula sobre el total con IVA incluido.
    public double calcularPropina() {
        double base = calcularSubtotalConDescuento();
        if (base > UMBRAL_PROPINA) {
            double totalConIva = base + calcularIVA();
            return totalConIva * TASA_PROPINA;
        }
        return 0;
    }

    // El total incluye subtotal con descuento, IVA y propina (si aplica)
    public double calcularTotal() {
        double base = calcularSubtotalConDescuento();
        double iva = calcularIVA();
        double propina = calcularPropina();
        return base + iva + propina;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }
}
