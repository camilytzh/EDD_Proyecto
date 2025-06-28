package estructuras;

import modelo.Contacto;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;


//Implementacion de un nodo para hacer uso en mi linkedList
class NodoCircularDoble<T> implements Serializable{
    T dato;
    NodoCircularDoble<T> siguiente, anterior;

    public NodoCircularDoble(T dato) {
        this.dato = dato;
        this.siguiente = this.anterior = null;
    }
}

//Mi propia ListaCircularEnlazadaDoble creado desde 0
public class CustomListaCircularEnlazadaDoble<T> implements Iterable<T>, Serializable{

    //nodo cabeza y ultimo
    public NodoCircularDoble<T> miCabecera;
    private NodoCircularDoble<T> nodoNavegacion;
    private int tamanio;


    //constructor
    public CustomListaCircularEnlazadaDoble(){

        this.miCabecera = null;
        this.nodoNavegacion = null;
        this.tamanio = 0;

    }




    //Metodo para agregar un contacto al final de mi lista :)
    public void addLast(T contacto){

        NodoCircularDoble<T> nuevoNodo = new NodoCircularDoble(contacto);

        if(miCabecera == null){

            miCabecera = nuevoNodo;
            miCabecera.siguiente = miCabecera;
            miCabecera.anterior = miCabecera;
            nodoNavegacion = miCabecera;
        }else{
            NodoCircularDoble<T> ultimoNodo = miCabecera.anterior;

            ultimoNodo.siguiente = nuevoNodo;
            nuevoNodo.anterior = ultimoNodo;
            nuevoNodo.siguiente = miCabecera;
            miCabecera.anterior = nuevoNodo;

        }
        tamanio++;

    }

    //metodo para obtener el tamaño
    public int getSize(){
        return tamanio;
    }

    //get por indice
    public T get(int index) {
        if (miCabecera == null || index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        NodoCircularDoble<T> actual = miCabecera;

        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }

        return actual.dato;
    }

    // Metodo para eliminar un contacto específico
    public boolean eliminar(Contacto contacto) {
        if (miCabecera == null) {
            System.out.println("La lista esta vacia. No se puede eliminar.");
            return false;
        }

        NodoCircularDoble<T> actual = miCabecera;

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

    // Metodos para poder navegar por la lista (debe ser con iterator)

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

    public T mostrarPosicionContactoActual() {
        if (nodoNavegacion != null) {
            return nodoNavegacion.dato;
        } else {
            return null; // Lista vacía o nodoNavegacion no inicializado
        }
    }

    //para verificar si la lista esta vacia
    public boolean estaVacia(){
        return tamanio == 0;
    }

    //obtener mi primer elemento
    public NodoCircularDoble<T> getCabecera() {
        return miCabecera;
    }

    //metodo para mostrar todos mis elementos (contactos)
    public void mostrarContactos(){
        if (miCabecera == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        NodoCircularDoble<T> actual = miCabecera;
        do {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        } while (actual != miCabecera);

    }


    @Override
    public Iterator<T> iterator() {
        return new IteradorListaCircular();
    }



    private class IteradorListaCircular implements Iterator<T> {
        private NodoCircularDoble<T> actual = miCabecera;
        private boolean inicio = true;

        @Override
        public boolean hasNext() {
            return actual != null && (inicio || actual != miCabecera);
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T dato = actual.dato;
            actual = actual.siguiente;
            inicio = false;
            return dato;
        }
    }









}

