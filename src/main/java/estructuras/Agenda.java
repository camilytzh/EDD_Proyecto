package estructuras;

import modelo.Contacto;
import modelo.ContactoLaboral;
import modelo.ContactoPersonal;
import modelo.Validador;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Agenda {

    private CustomListaCircularEnlazadaDoble<Contacto> contactos;

    public Agenda(){
        this.contactos = new CustomListaCircularEnlazadaDoble<>();
    }

    public void mostrarContactosAdelante() {
        if (contactos == null || contactos.mostrarPosicionContactoActual() == null) {
            System.out.println("La lista no tiene elementos.");
            return;
        }
        contactos.avanzar();
        Contacto contactoActual = contactos.mostrarPosicionContactoActual();

        if (contactoActual != null) {
            contactoActual.mostrarInformacion();
        } else {
            System.out.println("No hay un contacto actual para mostrar.");
        }
    }
    public void mostrarContactosAtras() {
        if (contactos == null || contactos.mostrarPosicionContactoActual() == null) {
            System.out.println("La lista no tiene elementos.");
            return;
        }
        contactos.anterior();
        Contacto contactoActual = contactos.mostrarPosicionContactoActual();

        if (contactoActual != null) {
            contactoActual.mostrarInformacion();
        } else {
            System.out.println("No hay un contacto actual para mostrar.");
        }
    }
    public void agregarContacto(Contacto contacto) {
        if (contacto != null) {
            contactos.addLast(contacto);
            System.out.println("Contacto agregado exitosamente: " + contacto.getNombre());
        } else {
            System.out.println("No se pudo agregar un contacto vacio.");
        }
    }
    public void eliminarContacto() {

        String telefono = Validador.pedirTexto("Ingrese el numero de telefono del contacto a eliminar:");

        if (contactos == null || contactos.mostrarPosicionContactoActual() == null) {
            System.out.println("No hay contactos en la agenda para eliminar.");
            return;
        }
        boolean eliminado = false;
        NodoCircularDoble<Contacto> actual = contactos.miCabecera;
        if (actual == null) {
            System.out.println("No hay contactos en la lista.");
            return;
        }
        do {
            Contacto contacto = actual.dato;
            if (contacto.getTelef().containsValue(telefono)) {
                eliminado = contactos.eliminar(contacto);
                break;
            }
            actual = actual.siguiente;
        } while (actual != contactos.miCabecera);
        if (eliminado) {
            System.out.println("El contacto fue eliminado con exito!");
        } else {
            System.out.println("No se encontro un contacto con ese numero de telefono.");
        }
    }
    public void crearContactoPersona() {
        String nombre = Validador.pedirTexto("Ingrese el nombre de la persona:");

        String alias = Validador.pedirTexto("Ingrese el alias del contacto:");

        ContactoPersonal persona = new ContactoPersonal(nombre, alias);

        String respuesta = Validador.pedirTexto("¿Desea agregar numeros de telefono? (s -> si / cualquier tecla -> continuar):");

        while (respuesta.equalsIgnoreCase("s")) {

            String tipo = Validador.pedirTexto("Ingrese el tipo de telefono (Ej: movil, casa, trabajo):");

            String numero = Validador.pedirTexto("Ingrese el numero de telefono:");

            persona.getTelef().put(tipo, numero);

            respuesta = Validador.pedirTexto("Desea agregar otro numero de telefono (s/cualquier tecla -> [continuar]):");
        }

        respuesta = Validador.pedirTexto("Desea agregar correos electronicos (s -> si / cualquier tecla -> continuar):");
        while (respuesta.equalsIgnoreCase("s")) {

            String tipo = Validador.pedirTexto("Ingrese el tipo de correo (Ej: personal, trabajo):");

            String correo = Validador.pedirTexto("Ingrese el correo:");

            persona.getEmails().put(tipo, correo);
            respuesta = Validador.pedirTexto("Desea agregar otro correo (s -> si / cualquier tecla -> continuar):");
        }
        respuesta = Validador.pedirTexto("Desea agregar redes sociales (s -> si / cualquier tecla -> continuar):");
        while (respuesta.equalsIgnoreCase("s")) {


            String plataforma = Validador.pedirTexto("Ingrese la plataforma (Ej: Instagram):");
            System.out.println();
            String usuario = Validador.pedirTexto("Ingrese el usuario:");
            persona.agregarRedSocial(plataforma, usuario);


            respuesta =Validador.pedirTexto("Desea agregar otra red social (s -> si / cualquier tecla -> continuar):");
        }



        if (Validador.pedirTexto("¿Desea agregar fotos? (s -> si / cualquier tecla -> continuar):").equalsIgnoreCase("s")) {
            do {

                String foto = Validador.pedirTexto("Ruta de la foto:");
                persona.agregarFoto(foto);
                System.out.println();


            } while (Validador.pedirTexto("¿Desea agregar otra foto? (s -> si / cualquier tecla -> continuar):").equalsIgnoreCase("s"));
        }


        if (Validador.pedirTexto("¿Desea agregar fechas de interés? (s -> si / cualquier tecla -> continuar):").equalsIgnoreCase("s")) {
            do {
                String descripcion = Validador.pedirTexto("Descripción de la fecha:");

                String fecha = Validador.pedirTexto("Fecha:");
                persona.agregarFechaDeInteres(descripcion, fecha);

            } while (Validador.pedirTexto("¿Desea agregar otra fecha? (s -> si / cualquier tecla -> continuar):").equalsIgnoreCase("s"));
        }
        contactos.addLast(persona);
        System.out.println("Contacto creado y agregado a la lista");
    }
    public void añadirContactoEmpresa() {

        String nombreEmpresa = Validador.pedirTexto("Ingrese el nombre de la empresa:");
        String direccionEmpresa = Validador.pedirTexto("Ingrese la dirección del trabajo:");

        HashMap<String, String> telefonosEmpresa = new HashMap<>();
        String tipoTelf = Validador.pedirTexto("Ingrese el tipo de teléfono de la empresa (Móvil, Oficina):");

        String numTelf = Validador.pedirTexto("Ingrese el número de teléfono:");
        telefonosEmpresa.put(tipoTelf, numTelf);
        HashMap<String, String> emailsEmpresa = new HashMap<>();

        String tipoEmail = Validador.pedirTexto("Ingrese el tipo de email de la empresa (Ejecutivo):");
        String correoEmail = Validador.pedirTexto("Ingrese el correo electrónico:");

        emailsEmpresa.put(tipoEmail, correoEmail);

        ContactoLaboral empresa = new ContactoLaboral(nombreEmpresa, direccionEmpresa);

        empresa.getTelef().putAll(telefonosEmpresa);
        empresa.getEmails().putAll(emailsEmpresa);

        contactos.addLast(empresa);
        System.out.println("Empresa añadida exitosamente.");
    }

    public void guardarContactos(String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            NodoCircularDoble<Contacto> actual = contactos.miCabecera;
            if (actual != null) {
                do {
                    oos.writeObject(actual.dato);
                    actual = actual.siguiente;
                } while (actual != contactos.miCabecera);
            }
            System.out.println("Contactos guardados exitosamente como binarios.");
        } catch (IOException e) {
            System.err.println("Error al guardar contactos: " + e.getMessage());
        }
    }
    public void cargarContactos(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            while (true) {
                try {
                    Contacto contacto = (Contacto) ois.readObject();
                    contactos.addLast(contacto);
                } catch (EOFException e) {
                    break;
                }
            }
            System.out.println("Contactos cargados exitosamente desde archivo binario.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar contactos: " + e.getMessage());
        }
    }
}
