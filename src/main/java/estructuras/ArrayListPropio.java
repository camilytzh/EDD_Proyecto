package estructuras;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Comparator;


public class ArrayListPropio<E> implements Serializable {
    private E[] elementos;
    private int tamanio;
    private Class<E> tipo;
    private int capacidad;



    //Constructor
    @SuppressWarnings("unchecked")
    // Constructor con capacidad inicial predeterminada (10)
    public ArrayListPropio(Class<E> tipo) {
        this(tipo, 10); // Llama al constructor con capacidad personalizada
    }

    // Constructor que permite especificar una capacidad inicial personalizada
    @SuppressWarnings("unchecked")
    public ArrayListPropio(Class<E> tipo, int capacidadInicial) {
        this.tipo = tipo;
        this.capacidad = capacidadInicial;
        this.elementos = (E[]) Array.newInstance(tipo, capacidad); // Crear un array de tipo E con la capacidad inicial
    }

    //añadir un elemento
    public void add(E elemento) {
        if (tamanio == elementos.length) {
            expandirCapacidad();
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
    private void expandirCapacidad() {
        capacidad = capacidad + (capacidad / 2); // Incremento del 50%
        E[] nuevoArray = (E[]) Array.newInstance(tipo, capacidad); // Crear un nuevo array de tipo E con capacidad expandida
        // Copiar elementos del array antiguo al nuevo
        for (int i = 0; i < tamanio; i++) {
            nuevoArray[i] = elementos[i];
        }
        elementos = nuevoArray; // Reemplazar el array antiguo por el nuevo
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