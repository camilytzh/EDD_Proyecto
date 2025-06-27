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

        String respuesta = Validador.pedirTexto("Desea agregar numeros de telefono (s/n):");

        while (respuesta.equalsIgnoreCase("s")) {

            String tipo = Validador.pedirTexto("Ingrese el tipo de telefono (Ej: movil, casa, trabajo):");

            String numero = Validador.pedirTexto("Ingrese el numero de telefono:");

            persona.getTelef().put(tipo, numero);

            respuesta = Validador.pedirTexto("Desea agregar otro numero de telefono (s/n):");
        }

        respuesta = Validador.pedirTexto("Desea agregar correos electronicos (s/n):");
        while (respuesta.equalsIgnoreCase("s")) {

            String tipo = Validador.pedirTexto("Ingrese el tipo de correo (Ej: personal, trabajo):");

            String correo = Validador.pedirTexto("Ingrese el correo:");

            persona.getEmails().put(tipo, correo);
            respuesta = Validador.pedirTexto("Desea agregar otro correo (s/n):");
        }
        respuesta = Validador.pedirTexto("Desea agregar redes sociales (s/n):");
        while (respuesta.equalsIgnoreCase("s")) {


            String plataforma = Validador.pedirTexto("Ingrese la plataforma (Ej: Instagram):");
            System.out.println();
            String usuario = Validador.pedirTexto("Ingrese el usuario:");
            persona.agregarRedSocial(plataforma, usuario);


            respuesta =Validador.pedirTexto("Desea agregar otra red social (s/n):");
        }



        if (Validador.pedirTexto("¿Desea agregar fotos? (s/n):").equalsIgnoreCase("s")) {
            do {

                String foto = Validador.pedirTexto("Ruta de la foto:");
                persona.agregarFoto(foto);
                System.out.println();


            } while (Validador.pedirTexto("¿Desea agregar otra foto? (s/n):").equalsIgnoreCase("s"));
        }


        if (Validador.pedirTexto("¿Desea agregar fechas de interés? (s/n):").equalsIgnoreCase("s")) {
            do {
                String descripcion = Validador.pedirTexto("Descripción de la fecha:");

                String fecha = Validador.pedirTexto("Fecha:");
                persona.agregarFechaDeInteres(descripcion, fecha);

            } while (Validador.pedirTexto("¿Desea agregar otra fecha? (s/n):").equalsIgnoreCase("s"));
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

    public Contacto buscarPorNombre(String nombre) {
        if (contactos.miCabecera == null) return null;

        NodoCircularDoble<Contacto> actual = contactos.miCabecera;
        do {
            if (actual.dato.getNombre().equalsIgnoreCase(nombre)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        } while (actual != contactos.miCabecera);

        return null;
    }
    public void modificarAtributoContacto() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del contacto a modificar: ");
        String nombre = sc.nextLine();

        Contacto contacto = buscarPorNombre(nombre);

        if (contacto == null) {
            System.out.println("No se encontró ningún contacto con ese nombre.");
            return;
        }

        System.out.println("\n Contacto encontrado: " + contacto.getNombre());
        System.out.println("Seleccione el atributo a modificar:");

        int i = 1;
        HashMap<Integer, String> opciones = new HashMap<>();

        System.out.println((i) + ". Nombre");
        opciones.put(i++, "nombre");

        System.out.println((i) + ". Teléfono");
        opciones.put(i++, "telefono");

        System.out.println((i) + ". Email");
        opciones.put(i++, "email");

        System.out.println((i) + ". Red Social");
        opciones.put(i++, "red");

        System.out.println((i) + ". Fecha de Interés");
        opciones.put(i++, "fecha");

        if (contacto instanceof ContactoPersonal) {
            System.out.println((i) + ". Alias");
            opciones.put(i++, "alias");
        }

        if (contacto instanceof ContactoLaboral) {
            System.out.println((i) + ". Dirección de Trabajo");
            opciones.put(i++, "direccionTrabajo");
        }

        System.out.print("Opción: ");
        int opcion = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        String tipo = opciones.get(opcion);

        if (tipo == null) {
            System.out.println("Opción inválida.");
            return;
        }

        switch (tipo) {
            case "nombre":
                System.out.print("Nuevo nombre: ");
                contacto.setNombre(sc.nextLine());
                break;

            case "telefono":
                System.out.print("¿Desea (1) Agregar/Modificar o (2) Eliminar un número de teléfono? ");
                int accionTel = sc.nextInt(); sc.nextLine();

                System.out.print("Etiqueta del teléfono (ej. casa, móvil): ");
                String tipoTel = sc.nextLine();

                if (accionTel == 1) {
                    System.out.print("Nuevo número: ");
                    String nuevoNumero = sc.nextLine();
                    contacto.getTelef().put(tipoTel, nuevoNumero);
                    System.out.println("Teléfono agregado/modificado.");
                } else if (accionTel == 2) {
                    if (contacto.getTelef().remove(tipoTel) != null) {
                        System.out.println("Teléfono eliminado.");
                    } else {
                        System.out.println("No se encontró esa etiqueta.");
                    }
                } else {
                    System.out.println("Opción inválida.");
                }
                break;

            case "email":
                System.out.print("¿Desea (1) Agregar/Modificar o (2) Eliminar un email? ");
                int accionEmail = sc.nextInt(); sc.nextLine();

                System.out.print("Etiqueta del email (ej. personal, trabajo): ");
                String claveEmail = sc.nextLine();

                if (accionEmail == 1) {
                    System.out.print("Nuevo email: ");
                    String nuevoEmail = sc.nextLine();
                    contacto.getEmails().put(claveEmail, nuevoEmail);
                    System.out.println("Email agregado/modificado.");
                } else if (accionEmail == 2) {
                    if (contacto.getEmails().remove(claveEmail) != null) {
                        System.out.println("Email eliminado.");
                    } else {
                        System.out.println("No se encontró esa etiqueta.");
                    }
                } else {
                    System.out.println("Opción inválida.");
                }
                break;

            case "red":
                System.out.print("¿Desea (1) Agregar/Modificar o (2) Eliminar una red social? ");
                int accionRed = sc.nextInt(); sc.nextLine();

                System.out.print("Nombre de la red social (ej. IG, Twitter): ");
                String red = sc.nextLine();

                if (accionRed == 1) {
                    System.out.print("Usuario o enlace: ");
                    String cuenta = sc.nextLine();
                    contacto.getRedesSociales().put(red, cuenta);
                    System.out.println("Red social agregada/modificada.");
                } else if (accionRed == 2) {
                    if (contacto.getRedesSociales().remove(red) != null) {
                        System.out.println("Red social eliminada.");
                    } else {
                        System.out.println("No se encontró esa red.");
                    }
                } else {
                    System.out.println("Opción inválida.");
                }
                break;

            case "fecha":
                System.out.print("¿Desea (1) Agregar/Modificar o (2) Eliminar una fecha? ");
                int accionFecha = sc.nextInt(); sc.nextLine();

                System.out.print("Descripción (ej. cumpleaños, aniversario): ");
                String desc = sc.nextLine();

                if (accionFecha == 1) {
                    System.out.print("Nueva fecha (ej. 01/01/2025): ");
                    String nuevaFecha = sc.nextLine();
                    contacto.getFechasDeInteres().put(desc, nuevaFecha);
                    System.out.println("Fecha agregada/modificada.");
                } else if (accionFecha == 2) {
                    if (contacto.getFechasDeInteres().remove(desc) != null) {
                        System.out.println("Fecha eliminada.");
                    } else {
                        System.out.println("No se encontró esa descripción.");
                    }
                } else {
                    System.out.println("Opción inválida.");
                }
                break;

            case "alias":
                System.out.print("Nuevo alias: ");
                ((ContactoPersonal) contacto).alias = sc.nextLine();
                System.out.println("Alias actualizado.");
                break;

            case "direccionTrabajo":
                System.out.print("Nueva dirección de trabajo: ");
                ((ContactoLaboral) contacto).setDireccionTrabajo(sc.nextLine());
                System.out.println("Dirección de trabajo actualizada.");
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

}
