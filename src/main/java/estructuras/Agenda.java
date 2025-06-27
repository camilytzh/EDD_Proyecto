package estructuras;

import modelo.Contacto;
import modelo.ContactoLaboral;
import modelo.ContactoPersonal;
import modelo.Validador;

import java.io.*;
import java.util.Comparator;
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

        String pais = Validador.pedirTexto("Ingrese el pais: ");

        ContactoPersonal persona = new ContactoPersonal(nombre,pais, alias);

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
        String pais = Validador.pedirTexto("Ingrese el pais:");

        emailsEmpresa.put(tipoEmail, correoEmail);

        ContactoLaboral empresa = new ContactoLaboral(nombreEmpresa,pais, direccionEmpresa);

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
        String nombre = Validador.pedirTexto("Ingrese el nombre del contacto a modificar: ");

        Contacto contacto = buscarPorNombre(nombre);

        if (contacto == null) {
            System.out.println("No se encontró ningún contacto con ese nombre.");
            return;
        }

        System.out.println("\nContacto encontrado: " + contacto.getNombre());
        System.out.println("Seleccione el atributo a modificar:");

        int i = 1;
        HashMap<Integer, String> opciones = new HashMap<>();

        System.out.println((i) + ". Nombre");
        opciones.put(i++, "nombre");

        System.out.println((i) + ". Pais");
        opciones.put(i++, "pais");

        System.out.println((i) + ". Teléfono");
        opciones.put(i++, "telefono");

        System.out.println((i) + ". Email");
        opciones.put(i++, "email");

        System.out.println((i) + ". Red Social");
        opciones.put(i++, "red");

        System.out.println((i) + ". Fecha de Interés");
        opciones.put(i++, "fecha");

        System.out.println((i) + ". Contacto Relacionado");
        opciones.put(i++, "relacionado");


        if (contacto instanceof ContactoPersonal) {
            System.out.println((i) + ". Alias");
            opciones.put(i++, "alias");
        }

        if (contacto instanceof ContactoLaboral) {
            System.out.println((i) + ". Dirección de Trabajo");
            opciones.put(i++, "direccionTrabajo");
        }

        int opcion = Validador.pedirNumero("Opcion: ");

        String tipo = opciones.get(opcion);

        if (tipo == null) {
            System.out.println("Opción inválida.");
            return;
        }

        switch (tipo) {
            case "nombre":
                contacto.setNombre(Validador.pedirTexto("Nuevo nombre: "));
                break;

            case "pais":
                contacto.setPais(Validador.pedirTexto("Nuevo pais: "));
                break;

            case "telefono":
                int accionTel = Validador.pedirNumero("¿Desea (1) Agregar/Modificar o (2) Eliminar un número de teléfono? ");

                String tipoTel = Validador.pedirTexto("Etiqueta del teléfono (ej. casa, móvil): ");

                if (accionTel == 1) {
                    String nuevoNumero = Validador.pedirTexto("Nuevo número: ");
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
                int accionEmail = Validador.pedirNumero("¿Desea (1) Agregar/Modificar o (2) Eliminar un email? ");

                String claveEmail = Validador.pedirTexto("Etiqueta del email (ej. personal, trabajo): ");

                if (accionEmail == 1) {
                    String nuevoEmail = Validador.pedirTexto("Nuevo email: ");
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
                int accionRed = Validador.pedirNumero("¿Desea (1) Agregar/Modificar o (2) Eliminar una red social? ");

                String red = Validador.pedirTexto("Nombre de la red social (ej. IG, Twitter): ");

                if (accionRed == 1) {
                    String cuenta = Validador.pedirTexto("Usuario o enlace: ");
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
                int accionFecha = Validador.pedirNumero("¿Desea (1) Agregar/Modificar o (2) Eliminar una fecha? ");

                String desc = Validador.pedirTexto("Descripción (ej. cumpleaños, aniversario): ");

                if (accionFecha == 1) {
                    String nuevaFecha = Validador.pedirTexto("Nueva fecha (ej. 01/01/2025): ");
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
                ((ContactoPersonal) contacto).alias = Validador.pedirTexto("Nuevo alias: ");
                System.out.println("Alias actualizado.");
                break;

            case "direccionTrabajo":
                ((ContactoLaboral) contacto).setDireccionTrabajo(Validador.pedirTexto("Nueva dirección de trabajo: "));
                System.out.println("Dirección de trabajo actualizada.");
                break;

            case "relacionado":
                int accionRel = Validador.pedirNumero("¿Desea (1) Agregar o (2) Eliminar un contacto relacionado? ");
                if (accionRel == 1) {
                    String nombre2 = Validador.pedirTexto("Nombre del contacto que quiere asociar: ");
                    Contacto c2 = buscarPorNombre(nombre2);
                    if (c2 == null) {
                        System.out.println("No se encontró el segundo contacto.");
                        break;
                    }
                    String tipoRelacion = Validador.pedirTexto("Tipo de relación (ej. amigo, colega): ");
                    contacto.agregarRelacionado(tipoRelacion, c2);
                    System.out.println("Contacto relacionado agregado con éxito.");
                } else if (accionRel == 2) {
                    String nombre2 = Validador.pedirTexto("Nombre del contacto relacionado a eliminar: ");
                    String tipoRelacion = Validador.pedirTexto("Tipo de relación: ");
                    boolean eliminado = contacto.eliminarRelacionado(tipoRelacion, nombre2);
                    if (eliminado) {
                        System.out.println("Contacto relacionado eliminado con éxito.");
                    } else {
                        System.out.println("No se encontró esa relación.");
                    }
                } else {
                    System.out.println("Opción inválida.");
                }
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

    public void mostrarContactosOrdenadosPorUsuario() {
        System.out.println("¿Cómo deseas ordenar los contactos?");
        System.out.println("1. Por nombre");
        System.out.println("2. Por país");
        System.out.println("3. Por tipo de contacto (personal/laboral)");

        int opcion = Validador.pedirNumero("Opcion: ");

        Comparator<Contacto> comparador = null;

        switch (opcion) {
            case 1:
                comparador = ComparadoresContacto.comparadorPornombre();
                break;
            case 2:
                comparador = ComparadoresContacto.comparadorPorPais();
                break;
            case 3:
                comparador = ComparadoresContacto.comparadorPorTipo();
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        if (comparador == null) return;

        // Crear tu lista propia
        ArrayListPropio<Contacto> listaTemporal = new ArrayListPropio<>();

        if (contactos.estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        // Rellenar desde la lista circular
        NodoCircularDoble<Contacto> actual = contactos.getCabecera();
        do {
            listaTemporal.add(actual.dato);
            actual = actual.siguiente;
        } while (actual != contactos.getCabecera());

        // Ordenar
        listaTemporal.ordenar(comparador);

        // Mostrar
        System.out.println("--------- Contactos ordenados ---------");
        for (int i = 0; i < listaTemporal.size(); i++) {
            listaTemporal.get(i).mostrarInformacion();
        }
    }
    }







