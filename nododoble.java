public class nododoble {
    private int dato;
    private nododoble siguiente;
    private nododoble anterior;

    public nododoble(int dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public nododoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nododoble siguiente) {
        this.siguiente = siguiente;
    }

    public nododoble getAnterior() {
        return anterior;
    }

    public void setAnterior(nododoble anterior) {
        this.anterior = anterior;
    }
}
