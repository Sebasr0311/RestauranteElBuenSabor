package restauranteelbuensabor.Dominio;

public class Mesa {
    private int numeroMesaActual;
    private boolean estadoMesa;

    public int getNumeroMesaActual() {
        return numeroMesaActual;
    }

    public boolean getEstadoMesa() {
        return estadoMesa;
    }

    public void activar(int numeroMesa) {
        if (numeroMesa > 0) {
            numeroMesaActual = numeroMesa;
        } else {
            numeroMesaActual = 1;
        }
        estadoMesa = true;
    }

    public void desactivar() {
        estadoMesa = false;
    }

    public void reiniciar() {
        numeroMesaActual = 0;
        estadoMesa = false;
    }
}
