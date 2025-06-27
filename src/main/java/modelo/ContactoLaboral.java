package modelo;

import java.io.Serializable;

public class ContactoLaboral extends Contacto implements Serializable {
    private String direccionTrabajo;

    public ContactoLaboral(String nombre,String pais, String direccionTrabajo){
        super(nombre,pais);
        setNombre(nombre);
        this.direccionTrabajo = direccionTrabajo;
    }

    public String getDireccionTrabajo() {
        return direccionTrabajo;
    }

    public void setDireccionTrabajo(String direccionTrabajo) {
        this.direccionTrabajo = direccionTrabajo;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre del Contacto: " + this.getNombre()+" \n");
        System.out.println("Pais: " + getPais());
        System.out.println("Direccion: " + this.getDireccionTrabajo());

        System.out.println("[Telefonos registrados]");
        for (String tipo : this.getTelef().keySet()) {
            System.out.println("> " + tipo + ": " + this.getTelef().get(tipo));
        }

        System.out.println("[Emails registrados]");
        for (String tipo : this.getEmails().keySet()) {
            System.out.println("> " + tipo + ": " + this.getEmails().get(tipo));
        }

        mostrarFotos();

        System.out.println("[Fechas de interés]");
        getFechasDeInteres().forEach((descripcion, fecha) -> {
            System.out.println(descripcion + ": " + fecha);
        });

        System.out.println("\n[Contactos relacionados]");
        System.out.println("----------------------------------");
        mostrarContactosRelacionados();
    }
}