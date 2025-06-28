package estructuras;

import java.io.Serializable;
import java.util.Comparator;


public class ArrayListPropio<E> implements Serializable {
    private E[] elementos;
    private int tamanio;


    //Constructor
    @SuppressWarnings("unchecked")
    public ArrayListPropio() {
        this.elementos = (E[]) new Object[10];
        this.tamanio = 0;
    }

    //añadir un elemento
    public void add(E elemento) {
        if (tamanio == elementos.length) {
            redimensionar();
        }
        elementos[tamanio++] = elemento;
    }

    //Get por indice
    public E get(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        return elementos[indice];
    }


    public int size() {
        return tamanio;
    }

    @SuppressWarnings("unchecked")
    private void redimensionar() {
        int nuevoTamanio = (int) (elementos.length * 1.5);
        E[] nuevosElementos = (E[]) new Object[nuevoTamanio];

        for (int i = 0; i < elementos.length; i++) {
            nuevosElementos[i] = elementos[i];
        }

        elementos = nuevosElementos;
    }


    //para simular lo mismo que hace el sort en el ArrayList original de JAVA
    public void ordenar(Comparator<E> comparador) {
        for (int i = 0; i < tamanio - 1; i++) {
            for (int j = 0; j < tamanio - i - 1; j++) {
                if (comparador.compare(elementos[j], elementos[j + 1]) > 0) {
                    E temp = elementos[j];
                    elementos[j] = elementos[j + 1];
                    elementos[j + 1] = temp;
                }
            }
        }
    }

}