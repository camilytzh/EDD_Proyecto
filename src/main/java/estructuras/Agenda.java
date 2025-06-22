package estructuras;

import modelo.Contacto;
import modelo.ContactoLaboral;
import modelo.ContactoPersonal;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Agenda {
    Scanner scanner = new Scanner(System.in);
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
        System.out.println("Ingrese el numero de telefono del contacto a eliminar:");
        String telefono = scanner.nextLine();

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la persona:");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el alias del contacto:");
        String alias = sc.nextLine();
        ContactoPersonal persona = new ContactoPersonal(nombre, alias);
        System.out.println("Desea agregar numeros de telefono (s/n):");
        String respuesta = sc.nextLine();
        while (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el tipo de telefono (Ej: movil, casa, trabajo):");
            String tipo = sc.nextLine();
            System.out.println("Ingrese el numero de telefono:");
            String numero = sc.nextLine();
            persona.getTelef().put(tipo, numero);

            System.out.println("Desea agregar otro numero de telefono (s/n):");
            respuesta = sc.nextLine();
        }
        System.out.println("Desea agregar correos electronicos (s/n):");
        respuesta = sc.nextLine();
        while (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el tipo de correo (Ej: personal, trabajo):");
            String tipo = sc.nextLine();
            System.out.println("Ingrese el correo:");
            String correo = sc.nextLine();
            persona.getEmails().put(tipo, correo);
            System.out.println("Desea agregar otro correo (s/n):");
            respuesta = sc.nextLine();
        }
        System.out.println("Desea agregar redes sociales (s/n):");
        respuesta = sc.nextLine();
        while (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese la plataforma (Ej: Instagram):");
            String plataforma = sc.nextLine();
            System.out.println("Ingrese el usuario:");
            String usuario = sc.nextLine();
            persona.agregarRedSocial(plataforma, usuario);

            System.out.println("Desea agregar otra red social (s/n):");
            respuesta = sc.nextLine();
        }

        System.out.println("¿Desea agregar fotos? (s/n):");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            do {
                System.out.println("Ruta de la foto:");
                String foto = sc.nextLine();
                persona.agregarFoto(foto);
                System.out.println("¿Desea agregar otra foto? (s/n):");
            } while (sc.nextLine().equalsIgnoreCase("s"));
        }

        System.out.println("¿Desea agregar fechas de interés? (s/n):");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            do {
                System.out.println("Descripción de la fecha:");
                String descripcion = sc.nextLine();
                System.out.println("Fecha:");
                String fecha = sc.nextLine();
                persona.agregarFechaDeInteres(descripcion, fecha);
                System.out.println("¿Desea agregar otra fecha? (s/n):");
            } while (sc.nextLine().equalsIgnoreCase("s"));
        }
        contactos.addLast(persona);
        System.out.println("Contacto creado y agregado a la lista");
    }
    public void añadirContactoEmpresa() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre de la empresa:");
        String nombreEmpresa = scanner.nextLine();
        System.out.println("Ingrese la dirección del trabajo:");
        String direccionEmpresa = scanner.nextLine();
        HashMap<String, String> telefonosEmpresa = new HashMap<>();
        System.out.println("Ingrese el tipo de teléfono de la empresa (Móvil, Oficina):");
        String tipoTelf = scanner.nextLine();
        System.out.println("Ingrese el número de teléfono:");
        String numTelf = scanner.nextLine();
        telefonosEmpresa.put(tipoTelf, numTelf);
        HashMap<String, String> emailsEmpresa = new HashMap<>();
        System.out.println("Ingrese el tipo de email de la empresa (Ejecutivo):");
        String tipoEmail = scanner.nextLine();
        System.out.println("Ingrese el correo electrónico:");
        String correoEmail = scanner.nextLine();
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
