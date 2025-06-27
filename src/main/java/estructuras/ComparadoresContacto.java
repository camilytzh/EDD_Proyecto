package estructuras;

import modelo.Contacto;
import modelo.ContactoLaboral;
import modelo.ContactoPersonal;

import java.util.Comparator;

public class ComparadoresContacto {

    static Comparator<Contacto> comparadorPornombre(){

        return Comparator.comparing(Contacto::getNombre);


    }

    static Comparator<Contacto> comparadorPorPais(){
        return Comparator.comparing(Contacto::getPais);

    }



    //Ordenada la lista en donde primero van los contactos personales

    static Comparator<Contacto> comparadorPorTipo() {
        return Comparator.comparing(contacto -> {
            if (contacto instanceof ContactoPersonal) {
                return "1-PERSONAL";
            } else if (contacto instanceof ContactoLaboral) {
                return "2-LABORAL";
            } else {
                return "3-OTRO";
            }
        });
    }







}
