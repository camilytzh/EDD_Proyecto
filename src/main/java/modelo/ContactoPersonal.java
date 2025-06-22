package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class ContactoPersonal extends Contacto implements Serializable {
    public String alias;
    public HashMap<String,String> redesSociales;

    public ContactoPersonal(String nombre){
        super(nombre);
    }
    public ContactoPersonal(String nombre, String alias){
        super(nombre);
        this.alias = alias;
        this.redesSociales = new HashMap<> ();
    }
    public String getFechaNacimiento(){
        return alias;
    }

    public void setFechanacimiento(Date fechaNacimiento){
        this.alias = alias;
    }

    public void agregarRedSocial(String plataforma, String usuario){
        redesSociales.put(plataforma, usuario);
    }

    public void elminarRedSocial(String plataforma){
        redesSociales.remove(plataforma);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre del contacto: " + getNombre()+" \n");
        System.out.println("Alias: " + alias + " \n");

        System.out.println("Telefonos registrados:");
        getTelef().forEach((tipo, numero) -> System.out.println(tipo + ": " + numero));

        System.out.println("Emails registrados:");
        getEmails().forEach((tipo, email) -> System.out.println(tipo + ": " + email));

        System.out.println("Redes Sociales:");
        redesSociales.forEach((plataforma, usuario) -> {
            System.out.println(plataforma + ": " + usuario);
        });

        System.out.println("Fotos:");
        mostrarFotos();

        System.out.println("Fechas de interés:");
        getFechasDeInteres().forEach((descripcion, fecha) -> {
            System.out.println(descripcion + ": " + fecha);
        });
    }
}

