package restauranteelbuensabor.Dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

    private final List<ItemPedido> items = new ArrayList<>();

    // Si el producto ya existe en el pedido, aumenta la cantidad.
    // De lo contrario, crea un nuevo ítem.
    public void agregarItem(Producto producto, int cantidad) {
        for (ItemPedido item : items) {
            if (item.getProducto().equals(producto)) {
                item.aumentarCantidad(cantidad);
                return;
            }
        }
        items.add(new ItemPedido(producto, cantidad));
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : items) {
            subtotal += item.calcularSubtotal();
        }
        return subtotal;
    }

    public int contarItemsDiferentes() {
        return items.size();
    }

    public boolean tieneProductos() {
        return !items.isEmpty();
    }

    public List<ItemPedido> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void limpiar() {
        items.clear();
    }
}

