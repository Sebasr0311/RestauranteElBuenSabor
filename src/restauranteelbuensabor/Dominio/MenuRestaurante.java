package restauranteelbuensabor.Dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuRestaurante {
    public static final String NOMBRE_RESTAURANTE = "El Buen Sabor";
    public static final String DIRECCION = "Calle 15 #8-32, Valledupar";
    public static final String NIT = "900.123.456-7";

    private final List<Producto> productos;

    public MenuRestaurante() {
        List<Producto> carta = new ArrayList<>();
        carta.add(new Producto("Bandeja Paisa", 32000));
        carta.add(new Producto("Sancocho de Gallina", 28000));
        carta.add(new Producto("Arepa con Huevo", 8000));
        carta.add(new Producto("Jugo Natural", 7000));
        carta.add(new Producto("Gaseosa", 4500));
        carta.add(new Producto("Cerveza Poker", 6000));
        carta.add(new Producto("Agua Panela", 3500));
        carta.add(new Producto("Arroz con Pollo", 25000));
        productos = Collections.unmodifiableList(carta);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public int getCantidadProductos() {
        return productos.size();
    }

    public Producto getProductoPorNumero(int numeroProducto) {
        if (numeroProducto <= 0 || numeroProducto > productos.size()) {
            throw new IllegalArgumentException("Numero de producto fuera de rango: " + numeroProducto);
        }
        return productos.get(numeroProducto - 1);
    }
}
