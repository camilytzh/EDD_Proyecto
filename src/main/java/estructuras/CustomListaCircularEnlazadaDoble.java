package estructuras;

import modelo.Contacto;

import java.io.*;

class NodoCircularDoble<T> implements Serializable{
    T dato;
    NodoCircularDoble<T> siguiente, anterior;

    public NodoCircularDoble(T dato) {
        this.dato = dato;
        this.siguiente = this.anterior = null;
    }
}

public class CustomListaCircularEnlazadaDoble<T> implements Serializable {

    public NodoCircularDoble<Contacto> miCabecera;
    private NodoCircularDoble<Contacto> nodoNavegacion;
    private int tamanio;

    public CustomListaCircularEnlazadaDoble(){

        this.miCabecera = null;
        this.nodoNavegacion = null;
        this.tamanio = 0;

    }

    //Metodo para agregar un contacto
    public void addLast(Contacto contacto){

        NodoCircularDoble<Contacto> nuevoNodo = new NodoCircularDoble(contacto);

        if(miCabecera == null){

            miCabecera = nuevoNodo;
            miCabecera.siguiente = miCabecera;
            miCabecera.anterior = miCabecera;
            nodoNavegacion = miCabecera;
        }else{
            NodoCircularDoble<Contacto> ultimoNodo = miCabecera.anterior;

            ultimoNodo.siguiente = nuevoNodo;
            nuevoNodo.anterior = ultimoNodo;
            nuevoNodo.siguiente = miCabecera;
            miCabecera.anterior = nuevoNodo;

        }
        tamanio++;

    }

    // Método para eliminar un contacto específico
    public boolean eliminar(Contacto contacto) {
        if (miCabecera == null) {
            System.out.println("La lista esta vacia. No se puede eliminar.");
            return false;
        }

        NodoCircularDoble<Contacto> actual = miCabecera;

        // Recorremos la lista buscando el nodo con el contacto
        do {
            if (actual.dato.equals(contacto)) { // Comparamos por igualdad
                if (actual == miCabecera && tamanio == 1) {
                    // Si solo hay un elemento en la lista
                    miCabecera = null;
                    nodoNavegacion = null;
                } else {
                    // Ajustamos los enlaces para eliminar el nodo
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;

                    // Si el nodo a eliminar es la cabeza, ajustamos miCabecera
                    if (actual == miCabecera) {
                        miCabecera = actual.siguiente;
                    }

                    // Si el nodo a eliminar es el nodoNavegacion, lo actualizamos
                    if (actual == nodoNavegacion) {
                        nodoNavegacion = actual.siguiente;
                    }
                }
                tamanio--;
                System.out.println("Contacto eliminado: " + contacto.getNombre());
                return true;
            }
            actual = actual.siguiente; // Avanzamos al siguiente nodo
        } while (actual != miCabecera);

        System.out.println("Contacto no encontrado.");
        return false;
    }
    // Metodos para poder navegar por la lista

    public void avanzar(){
        if(nodoNavegacion != null){
            nodoNavegacion = nodoNavegacion.siguiente;
        }else{
            System.out.println("La lista no tiene elementos");
        }
    }

    public void anterior(){
        if(nodoNavegacion!= null){
            nodoNavegacion = nodoNavegacion.anterior;
        }else{
            System.out.println("La lista no tiene elementos");
        }

    }

    public boolean estaVacia(){
        return tamanio == 0;
    }

    public Contacto mostrarPosicionContactoActual() {
        if (nodoNavegacion != null) {
            return nodoNavegacion.dato;
        } else {
            return null; // Lista vacía o nodoNavegacion no inicializado
        }
    }
    public NodoCircularDoble<Contacto> getCabecera() {
        return miCabecera;
    }


    public void mostrarContactos(){
        if (miCabecera == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        NodoCircularDoble<Contacto> actual = miCabecera;
        do {
            actual.dato.mostrarInformacion();
            actual = actual.siguiente;
        } while (actual != miCabecera);

    }

}

