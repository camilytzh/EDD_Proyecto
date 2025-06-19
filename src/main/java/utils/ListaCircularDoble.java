package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Nodo {
    String dato;
    Nodo siguiente, anterior;

    public Nodo(String dato) {
        this.dato = dato;
        this.siguiente = this.anterior = null;
    }
}

public class ListaCircularDoble {
    private Nodo cabeza;

    public ListaCircularDoble() {
        cabeza = null;
    }


    public void agregar(String dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cabeza.siguiente = cabeza.anterior = cabeza;
        } else {
            Nodo cola = cabeza.anterior;
            cola.siguiente = nuevoNodo;
            nuevoNodo.anterior = cola;
            nuevoNodo.siguiente = cabeza;
            cabeza.anterior = nuevoNodo;
        }
    }

    //Serializacion
    public void serializar() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lista.dat"))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Deserializacion
    public static ListaCircularDoble deserializar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("lista.dat"))) {
            return (ListaCircularDoble) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Mostrar los elementos de la lista (solo para verificar)
    public void mostrar() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo temp = cabeza;
        do {
            System.out.println(temp.dato);
            temp = temp.siguiente;
        } while (temp != cabeza);
    }
}
