public class listadoble {
    private int tamanio;
    private nododoble cabeza;
    private nododoble cola;
    
    // Crea la lista vacía
    public listadoble() {
        tamanio = 0;
        cabeza = null;
        cola = null;
    }
    
    // Devuelve cuántos elementos hay
    public int getTamanio() {
        return tamanio;
    }
        
    // Revisa si la lista no tiene nada
    public boolean estaVacia() {
        return cabeza == null && cola == null;
    }
        
    // Busca si existe un número en la lista
    public boolean buscar(int dato) {
        nododoble aux = cabeza;
        while (aux != null) {
            if (aux.getDato() == dato) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }
        
    // Muestra la lista de inicio a fin
    @Override
    public String toString() {
        String res = "null <- ";
        nododoble aux = cabeza;
        while (aux != null) {
            res += "[" + aux.getDato() + "]";
            if (aux.getSiguiente() != null) {
                res += " <-> ";
            }
            aux = aux.getSiguiente();
        }
        res += " -> null";
        return res;
    }

    // Muestra la lista de fin a inicio
    public String imprimirReversa() {
        String res = "null <- ";
        nododoble aux = cola;
        while (aux != null) {
            res += "[" + aux.getDato() + "]";
            if (aux.getAnterior() != null) {
                res += " <-> ";
            }
            aux = aux.getAnterior();
        }
        res += " -> null";
        return res;
    }
    
    // Devuelve el nodo de una posición (empezando en 0)
    public nododoble obtenerNodo(int pos) {
        if (pos < 0 || pos >= tamanio) {
            throw new IndexOutOfBoundsException("Posición fuera de rango: " + pos);
        }
        nododoble aux = cabeza;
        for (int i = 0; i < pos; i++) {
            aux = aux.getSiguiente();
        }
        return aux;
    }
        
    // Cambia el valor en una posición
    public void actualizar(int pos, int valor) {
        nododoble val = obtenerNodo(pos);
        val.setDato(valor);
    }
    
    // Agrega un nodo al principio
    public void insertarInicio(int valor) {
        nododoble nuevo = new nododoble(valor);
        
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cabeza.setAnterior(nuevo);
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        }
        tamanio++;
    }
    
    // Agrega un nodo al final
    public void insertarfinal(int valor) {
        nododoble nuevo = new nododoble(valor);
        
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
        tamanio++;
    }
    
    // Borra el primer nodo y devuelve su valor
    public int eliminarInicio() {
        if (estaVacia()) {
            throw new IllegalStateException("La lista está vacía");
        }

        int dato = cabeza.getDato();
        
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.getSiguiente();
            cabeza.getAnterior().setSiguiente(null);
            cabeza.setAnterior(null);
        }
        tamanio--;
        return dato; 
    }

    // Borra el último nodo
    public boolean eliminarUltimo() {
        if (estaVacia()) {
            return false;
        }

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cola = cola.getAnterior();
            cola.getSiguiente().setAnterior(null);
            cola.setSiguiente(null);
        }
        tamanio--;
        return true;
    }

    // Agrega un nodo en una posición específica
    public void insertarEnPosicion(int pos, int dato) {
        if (pos < 0 || pos > tamanio) {
            throw new IndexOutOfBoundsException("Posición inválida: " + pos);
        }

        if (pos == 0) {
            insertarInicio(dato);
        } else if (pos == tamanio) {
            insertarfinal(dato);
        } else {
            nododoble nuevo = new nododoble(dato);
            nododoble actual = obtenerNodo(pos);
            nododoble previo = actual.getAnterior();

            nuevo.setAnterior(previo);
            nuevo.setSiguiente(actual);
            previo.setSiguiente(nuevo);
            actual.setAnterior(nuevo);
            
            tamanio++;
        }
    }

    // Borra el nodo de una posición y devuelve su valor
    public int eliminarEnPosicion(int pos) {
        if (estaVacia()) {
            throw new IllegalStateException("La lista está vacía");
        }
        if (pos < 0 || pos >= tamanio) {
            throw new IndexOutOfBoundsException("Posición fuera de rango: " + pos);
        }

        if (pos == 0) {
            return eliminarInicio();
        }
        if (pos == tamanio - 1) {
            int dato = cola.getDato();
            eliminarUltimo();
            return dato;
        }

        nododoble actual = obtenerNodo(pos);
        int dato = actual.getDato();
        nododoble previo = actual.getAnterior();
        nododoble siguiente = actual.getSiguiente();

        previo.setSiguiente(siguiente);
        siguiente.setAnterior(previo);

        actual.setAnterior(null);
        actual.setSiguiente(null);
        
        tamanio--;
        return dato;
    }

    // Busca un número y borra el nodo que lo tenga
    public boolean eliminarPorValor(int x) {
        if (estaVacia()) {
            return false;
        }

        nododoble aux = cabeza;
        while (aux != null) {
            if (aux.getDato() == x) {
                if (aux == cabeza) {
                    eliminarInicio();
                } else if (aux == cola) {
                    eliminarUltimo();
                } else {
                    aux.getAnterior().setSiguiente(aux.getSiguiente());
                    aux.getSiguiente().setAnterior(aux.getAnterior());
                    aux.setAnterior(null);
                    aux.setSiguiente(null);
                    tamanio--;
                }
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    // Inserta el número en su lugar para mantener la lista ordenada
    public void insertarOrdenado(int dato) {
        if (estaVacia() || dato <= cabeza.getDato()) {
            insertarInicio(dato);
            return;
        }
        if (dato >= cola.getDato()) {
            insertarfinal(dato);
            return;
        }

        nododoble nuevo = new nododoble(dato);
        nododoble aux = cabeza;

        while (aux != null && aux.getDato() < dato) {
            aux = aux.getSiguiente();
        }

        nododoble previo = aux.getAnterior();
        nuevo.setSiguiente(aux);
        nuevo.setAnterior(previo);
        previo.setSiguiente(nuevo);
        aux.setAnterior(nuevo);

        tamanio++;
    }
}
