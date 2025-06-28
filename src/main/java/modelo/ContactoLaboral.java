package modelo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



//Hereda de Contacto
//Implementa serializable debido a que vamos a serializar datos con los atributos de esta clase
public class ContactoLaboral extends Contacto implements Serializable {
    private String direccionTrabajo;

    //Constructor
    public ContactoLaboral(String nombre,String pais, String direccionTrabajo){
        super(nombre,pais);
        setNombre(nombre);
        this.direccionTrabajo = direccionTrabajo;
    }


    //Getters y Setters Necesarios
    public String getDireccionTrabajo() {
        return direccionTrabajo;
    }

    public void setDireccionTrabajo(String direccionTrabajo) {
        this.direccionTrabajo = direccionTrabajo;
    }


    //Sobreescritura del metodo mostrarInformacion para mostrar Los Datos de esta instacia de Contacto
    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre del Contacto: " + this.getNombre()+" \n");
        System.out.println("Pais: " + getPais());
        System.out.println("Direccion: " + this.getDireccionTrabajo());

        System.out.println("[Telefonos registrados]");
        HashMap<String,String> telefonos = getTelef();
        for(Map.Entry entry : telefonos.entrySet()){
            System.out.println("> " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("[Emails registrados]");
        HashMap<String,String> emails = getEmails();
        for(Map.Entry entry : emails.entrySet()){
            System.out.println("> " + entry.getKey() + ": " + entry.getValue());
        }


        mostrarFotos();

        System.out.println("[Fechas de interés]");
        HashMap<String,String> fechasIn = getFechasDeInteres();
        for(Map.Entry entry : fechasIn.entrySet()){
            System.out.println("> " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n[Contactos relacionados]");
        System.out.println("----------------------------------");
        mostrarContactosRelacionados();
    }
}