package estructuras;

import java.io.Serializable;

public class ArrayListPropio<E> implements Serializable {
    private E[] elementos;
    private int tamanio;

    @SuppressWarnings("unchecked")
    public ArrayListPropio() {
        this.elementos = (E[]) new Object[10];
        this.tamanio = 0;
    }

    public void add(E elemento) {
        if (tamanio == elementos.length) {
            redimensionar();
        }
        elementos[tamanio++] = elemento;
    }

    public E get(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        return elementos[indice];
    }

    public E remove(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        E eliminado = elementos[indice];
        System.arraycopy(elementos, indice + 1, elementos, indice, tamanio - indice - 1);
        elementos[--tamanio] = null;
        return eliminado;
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

    public void printAll() {
        for (int i = 0; i < tamanio; i++) {
            System.out.print(elementos[i] + " ");
        }
        System.out.println();
    }
}