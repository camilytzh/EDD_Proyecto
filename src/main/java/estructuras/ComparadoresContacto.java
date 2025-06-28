package estructuras;

import modelo.Contacto;
import modelo.ContactoLaboral;
import modelo.ContactoPersonal;

import java.util.Comparator;


//Clase aparte solo para comparadores (ordenacion de lista y Filtracion)
public class ComparadoresContacto {


    //Comparador Por Nombre
    static Comparator<Contacto> comparadorPornombre(){

        return Comparator.comparing(Contacto::getNombre);


    }


    //Comparador Por Pais
    static Comparator<Contacto> comparadorPorPais(){
        return Comparator.comparing(Contacto::getPais);

    }


    //Ordenada la lista en donde primero van los contactos personales y luego ya los Laborales

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
