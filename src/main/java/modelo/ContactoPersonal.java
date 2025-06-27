package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class ContactoPersonal extends Contacto implements Serializable {
    public String alias;

    public ContactoPersonal(String nombre,String pais, String alias){
        super(nombre,pais);
        this.alias = alias;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre del contacto: " + getNombre()+" \n");
        System.out.println("Pais: " + getPais()+" \n");
        System.out.println("Alias: " + alias + " \n");

        System.out.println("Telefonos registrados:");
        getTelef().forEach((tipo, numero) -> System.out.println(tipo + ": " + numero));

        System.out.println("Emails registrados:");
        getEmails().forEach((tipo, email) -> System.out.println(tipo + ": " + email));

        System.out.println("Fotos:");
        mostrarFotos();

        System.out.println("Fechas de interés:");
        getFechasDeInteres().forEach((descripcion, fecha) -> {
            System.out.println(descripcion + ": " + fecha);
        });

        System.out.println("Contactos relacionados:");
        mostrarContactosRelacionados();
    }
}

