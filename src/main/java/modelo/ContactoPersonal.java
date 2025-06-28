package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Hereda de Contacto
//Implementa serializable debido a que vamos a serializar datos con los atributos de esta clase
public class ContactoPersonal extends Contacto implements Serializable {
    public String alias;


    //Constructor
    public ContactoPersonal(String nombre,String pais, String alias){
        super(nombre,pais);
        this.alias = alias;
    }


    //Sobreescritura del metodo mostrarInformacion para mostrar Los Datos de esta instacia de Contacto
    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre del Contacto: " + getNombre()+" \n");
        System.out.println("Pais: " + getPais());
        System.out.println("Alias: " + alias);

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

