package estructuras;

class NodoCircularDoble<T> {
    T dato;
    NodoCircularDoble<T> siguiente, anterior;

    public NodoCircularDoble(T dato) {
        this.dato = dato;
        this.siguiente = this.anterior = null;
    }
}
