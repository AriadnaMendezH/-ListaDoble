public class Main {
    public static void main(String[] args) {
        listadoble lista = new listadoble();
        int[] numeros = {30, 10, 50, 20, 40};

        // Metemos los números del arreglo en orden
        for (int i = 0; i < numeros.length; i++) {
            lista.insertarOrdenado(numeros[i]);
        }
        System.out.println("Lista ordenada: " + lista);
        System.out.println("Al revés:       " + lista.imprimirReversa());

        // Metemos datos en posiciones exactas
        lista.insertarEnPosicion(0, 5);   // al inicio
        lista.insertarEnPosicion(3, 25);  // en medio
        lista.insertarEnPosicion(7, 60);  // al final
        System.out.println("\nDespués de insertar: " + lista);

        // Borramos el número 25
        lista.eliminarPorValor(25);
        System.out.println("\nSin el 25: " + lista);

        // Borramos lo que esté en la posición 2
        int borrado = lista.eliminarEnPosicion(2);
        System.out.println("\nSe borró el " + borrado + ": " + lista);

        // Borramos el primero y el último
        lista.eliminarInicio();
        lista.eliminarUltimo();
        System.out.println("\nSin primero ni último: " + lista);
        System.out.println("Al revés:              " + lista.imprimirReversa());
    }
}
