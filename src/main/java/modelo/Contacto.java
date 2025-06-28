package modelo;

import estructuras.ArrayListPropio;
import estructuras.CustomListaCircularEnlazadaDoble;

import java.io.Serializable;
import java.util.HashMap;


//Esta es la clase Contacto padre de ContactoLaboral y ContactoPersonal
//Implementa serializable debido a que vamos a serializar datos con los atributos de esta clase

public abstract class Contacto implements Serializable {
    private String nombre;
    private HashMap<String,String> telef;
    private HashMap<String, CustomListaCircularEnlazadaDoble<Contacto>> contactosRelacionados;
    private HashMap<String,String> emails;
    private HashMap<String, String> redesSociales;
    private ArrayListPropio<String> fotos;
    private HashMap<String,String> fechasDeInteres;
    private String pais;



//Constructor
    public Contacto(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
        this.telef = new HashMap<>();
        this.contactosRelacionados = new HashMap<>();
        this.emails = new HashMap<>();
        this.redesSociales = new HashMap<>();
        this.fotos = new ArrayListPropio<>();
        this.fechasDeInteres = new HashMap<>();

    }

    //Getters y Setters NECESARIOS
    public String getNombre() {
        return nombre;
    }

    public HashMap<String, String> getTelef() {
        return telef;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public HashMap<String, String> getEmails() {
        return emails;
    }

    public HashMap<String, String> getRedesSociales() {
        return redesSociales;
    }
    public HashMap<String, String> getFechasDeInteres() {
        return fechasDeInteres;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void agregarFoto(String ruta) {
        fotos.add(ruta);
    }
    public void mostrarFotos() {
        System.out.println("[Fotos]");
        for (int i = 0; i < fotos.size(); i++) {
            System.out.println("> " + i + ": " + fotos.get(i));
        }
    }
    public void agregarRedSocial(String plataforma, String usuario){
        redesSociales.put(plataforma, usuario);
    }

    public void agregarRelacionado(String tipoRelacion, Contacto relacionado) {
        contactosRelacionados
                .computeIfAbsent(tipoRelacion, k -> new CustomListaCircularEnlazadaDoble<>())
                .addLast(relacionado);
    }


    //Busca y elimina un contacto relacionado segun su tipo de relacion y el nombre del contacto
    public boolean eliminarRelacionado(String tipoRelacion, String nombreContacto) {
        if (!contactosRelacionados.containsKey(tipoRelacion)) {
            return false;
        }

        CustomListaCircularEnlazadaDoble<Contacto> relacionados = contactosRelacionados.get(tipoRelacion);

        for (Contacto c : relacionados) {
            if (c.getNombre().equalsIgnoreCase(nombreContacto)) {
                relacionados.eliminar(c);
                if (relacionados.getSize() == 0) {
                    contactosRelacionados.remove(tipoRelacion);
                }
                return true;
            }
        }

        return false;
    }


    //Muestra todos los contactos relacionados agrupados por su tipo de relacion
    public void mostrarContactosRelacionados() {
        if (contactosRelacionados == null || contactosRelacionados.isEmpty()) {
            return;
        }

        for (String tipo : contactosRelacionados.keySet()) {
            System.out.println("> Relación: " + tipo);
            CustomListaCircularEnlazadaDoble<Contacto> lista = contactosRelacionados.get(tipo);
            if (lista != null) {
                lista.mostrarContactos();
                System.out.println("----------------------------------");
            }
        }
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", telef=" + telef +
                ", contactosRelacionados=" + contactosRelacionados +
                ", emails=" + emails +
                ", redesSociales=" + redesSociales +
                ", fotos=" + fotos +
                ", fechasDeInteres=" + fechasDeInteres +
                ", pais='" + pais + '\'' +
                '}';
    }

    //Agrega nuevos datos al mapa de fecha de interes (agrega una fecha de interes)
    public void agregarFechaDeInteres(String descripcion, String fecha) {
        fechasDeInteres.put(descripcion,fecha);
    }

    //Metodo abstracto para que las clases hijas implemente su "toString"
    public abstract void mostrarInformacion();



}
