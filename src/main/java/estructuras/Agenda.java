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
        contactos.avanzar(); // Navega hacia adelante por la lista
        Contacto contactoActual = contactos.mostrarPosicionContactoActual();

        if (contactoActual != null) { // Si el contacto no es nulo, imprime la información del contacto posterior
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
        contactos.anterior(); // Navega hacia atras por la lista
        Contacto contactoActual = contactos.mostrarPosicionContactoActual();

        if (contactoActual != null) { // Si el contacto no es nulo, imprime la información del contacto anterior
            contactoActual.mostrarInformacion();
        } else {
            System.out.println("No hay un contacto actual para mostrar.");
        }
    }
    public void agregarContacto(Contacto contacto) {
        if (contacto != null) {
            contactos.addLast(contacto); // Añade el contacto al final de la lista
            System.out.println("Contacto agregado exitosamente: " + contacto.getNombre());
        } else {
            System.out.println("No se pudo agregar un contacto vacio.");
        }
    }
    public void eliminarContacto() {

        String telefono = Validador.pedirTexto("Ingrese el numero de telefono del contacto a eliminar:");

        // Verifica si la lista de contactos está vacía o si no hay un contacto actual
        if (contactos == null || contactos.mostrarPosicionContactoActual() == null) {
            System.out.println("No hay contactos en la agenda para eliminar.");
            return;
        }
        boolean eliminado = false;

        // Comienza desde el nodo cabecera de la lista circular
        NodoCircularDoble<Contacto> actual = contactos.miCabecera;
        if (actual == null) {
            System.out.println("No hay contactos en la lista.");
            return;
        }
        // Recorre la lista circular hasta volver a la cabecera
        do {
            Contacto contacto = actual.dato;
            if (contacto.getTelef().containsValue(telefono)) {
                eliminado = contactos.eliminar(contacto);
                break; // Sale del bucle porque ya encontró y eliminó al contacto
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
        // Datos a pedir por teclado que son necesarios para crear un contacto persona
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
        contactos.addLast(persona); // Añade al contacto al final de la lista
        System.out.println("Contacto creado y agregado a la lista");
    }
    public void añadirContactoEmpresa() {
        // Datos a pedir por teclado que son necesarios para crear un contacto empresa
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

        contactos.addLast(empresa); // Añade al contacto al final de la lista
        System.out.println("Empresa añadida exitosamente.");
    }

    public void guardarContactos(String archivo) {
        // Intenta abrir un flujo de salida para guardar objetos en un archivo binario
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            NodoCircularDoble<Contacto> actual = contactos.miCabecera;
            // Si la lista no está vacía, recorre todos los nodos de la lista circular
            if (actual != null) {
                do {
                    // Escribe el objeto Contacto actual en el archivo
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
        // Intenta abrir un flujo de entrada para leer objetos de un archivo binario
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            while (true) {
                try {
                    Contacto contacto = (Contacto) ois.readObject();
                    contactos.addLast(contacto); // Agrega cada contacto al final de la lista
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
        // Recorre la lista circular para buscar un contacto con nombre igual
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

        // Busca el contacto por nombre
        Contacto contacto = buscarPorNombre(nombre);

        if (contacto == null) {
            System.out.println("No se encontró ningún contacto con ese nombre.");
            return;
        }

        System.out.println("\nContacto encontrado: " + contacto.getNombre());
        System.out.println("Seleccione el atributo a modificar:");

        // Mapa para asociar opciones numéricas con nombres de atributos
        int i = 1;
        HashMap<Integer, String> opciones = new HashMap<>();

        // Imprime opciones y las almacena para referencia posterior
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

        // Opciones específicas según el tipo de contacto
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
        // Según el atributo elegido, ejecuta la acción correspondiente
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
    public void filtrarPorComparador(Comparator<Contacto> comparador, Contacto referencia) {
        if (contactos == null || contactos.miCabecera == null) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }

        NodoCircularDoble<Contacto> actual = contactos.miCabecera;
        boolean encontrado = false;

        do {
            if (comparador.compare(actual.dato, referencia) == 0) {
                actual.dato.mostrarInformacion();
                System.out.println("------------------------------");
                encontrado = true;
            }
            actual = actual.siguiente;
        } while (actual != contactos.miCabecera);

        if (!encontrado) {
            System.out.println("No se encontraron contactos con ese criterio.");
        }
    }

    public void filtrarContactos(){
        System.out.println("¿Cómo deseas filtrar los contactos?");
        System.out.println("1. Por nombre");
        System.out.println("2. Por país");
        System.out.println("3. Por tipo de contacto (personal/laboral)");

        int opcion = Validador.pedirNumero("Opcion: ");

        Comparator<Contacto> comparador;
        Contacto referencia;

        switch (opcion) {
            case 1:
                String nombre = Validador.pedirTexto("Ingrese el nombre a buscar: ");
                comparador = ComparadoresContacto.comparadorPornombre();
                referencia = new ContactoPersonal(nombre, "TMP", "TMP"); // solo importa el nombre
                filtrarPorComparador(comparador, referencia);
                break;

            case 2:
                String pais = Validador.pedirTexto("Ingrese el país a buscar: ");
                comparador = ComparadoresContacto.comparadorPorPais();
                referencia = new ContactoPersonal("TMP", pais, "TMP"); // solo importa el país
                filtrarPorComparador(comparador, referencia);
                break;

            case 3:
                String tipo = Validador.pedirTexto("Ingrese el tipo (personal/laboral): ").toLowerCase();
                comparador = ComparadoresContacto.comparadorPorTipo();

                if (tipo.equals("personal")) {
                    referencia = new ContactoPersonal("TMP", "TMP", "TMP"); // se filtra por instancia
                } else if (tipo.equals("laboral")) {
                    referencia = new ContactoLaboral("TMP", "TMP", "TMP");
                } else {
                    System.out.println("Tipo inválido.");
                    return;
                }

                filtrarPorComparador(comparador, referencia);
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }
    public void mostrarContactosFiltradosUOrdenados(){
        System.out.println("Selecciona una forma de visualizar los contactos. ");
        System.out.println("1. Mostrar Contactos Filtrados");
        System.out.println("2. Mostrar Contactos Ordenados");

        int opcion = Validador.pedirNumero("Opcion: ");
        switch (opcion) {
            case 1:
                filtrarContactos();
                break;
            case 2:
                mostrarContactosOrdenadosPorUsuario();
                break;
            default:
                System.out.println("Opción inválida.");
                return;
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
                comparador = ComparadoresContacto.comparadorPornombre(); // Utiliza el comparador para ordenar por nombre
                break;
            case 2:
                comparador = ComparadoresContacto.comparadorPorPais(); // Utiliza el comparador para ordenar por pais
                break;
            case 3:
                comparador = ComparadoresContacto.comparadorPorTipo(); // Utiliza el comparador para ordenar por tipo de contacto
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        if (comparador == null) return;

        // Crear tu lista propia
        ArrayListPropio<Contacto> listaTemporal = new ArrayListPropio<>(Contacto.class);

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







