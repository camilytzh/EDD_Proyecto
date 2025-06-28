package modelo;

import estructuras.Agenda;

public class GeneradorDatosIniciales {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        // Clase para crear los datos iniciales con ayuda de la Serializacion en Java con un .dat
        ContactoPersonal contacto1 = new ContactoPersonal("Ana Torres","Ecuador", "Mejor amiga");
        contacto1.getTelef().put("celular", "0991234567");
        contacto1.getEmails().put("personal", "ana.torres@gmail.com");
        contacto1.getRedesSociales().put("instagram", "@ana.torres");
        contacto1.agregarFoto("ana1.jpg");
        contacto1.agregarFechaDeInteres("Cumpleaños", "1998-04-25");

        ContactoLaboral contacto2 = new ContactoLaboral("Empresa Textil","Ecuador", "Guasmo norte");
        contacto2.getTelef().put("trabajo", "042555000");
        contacto2.getEmails().put("corporativo", "textil@xyz.com");
        contacto2.agregarFoto("carlos_oficina.jpg");
        contacto2.agregarFechaDeInteres("Inicio Laboral", "2020-03-01");
        contacto2.agregarRelacionado("hermano", contacto1);

        ContactoPersonal contacto12 = new ContactoPersonal("Rita Torres","Venezuela", "Amiga de la infancia");
        contacto12.getTelef().put("celular", "0991234568");
        contacto12.getEmails().put("personal", "rita.torres@example.com");
        contacto12.getRedesSociales().put("instagram", "@rita_torres");
        contacto12.agregarFoto("rita_torres.jpg");
        contacto12.agregarFechaDeInteres("Cumpleaños", "1995-02-20");

        ContactoPersonal contacto13 = new ContactoPersonal("Luisito Tamayo","Peru", "Compañero de trabajo");
        contacto13.getTelef().put("celular", "0991234569");
        contacto13.getEmails().put("personal", "luisito.tamayo@example.com");
        contacto13.getRedesSociales().put("instagram", "@luisito_tamayo");
        contacto13.agregarFoto("luisito_tamayo.jpg");
        contacto13.agregarFechaDeInteres("Aniversario en la oficina", "2019-07-10");

        ContactoPersonal contacto14 = new ContactoPersonal("Pajiliam Maurad","España", "Colega de universidad");
        contacto14.getTelef().put("celular", "0991234570");
        contacto14.getEmails().put("personal", "pajiliam.maurad@example.com");
        contacto14.getRedesSociales().put("instagram", "@pajiliam_maurad");
        contacto14.agregarFoto("pajiliam_maurad.jpg");
        contacto14.agregarFechaDeInteres("Graduación", "2021-12-18");


        ContactoLaboral contacto15 = new ContactoLaboral("Amazon","Estados Unidos", "Urdesa Central");
        contacto15.getTelef().put("trabajo", "042555005");
        contacto15.getEmails().put("corporativo", "amazonsiri@marketing.com");
        contacto15.agregarFoto("amazon.jpg");
        contacto15.agregarFechaDeInteres("Fundacion de la empresa", "2020-03-25");

        ContactoLaboral contacto16 = new ContactoLaboral("Corporacion Paja","Ecuador", "Via a la Costa km32");
        contacto16.getTelef().put("trabajo", "042555006");
        contacto16.getEmails().put("corporativo", "Corporacion.paja@ventas.com");
        contacto16.agregarFoto("paja.jpg");
        contacto16.agregarFechaDeInteres("Éxito en ventas", "2018-09-30");

        // Agregar a la agenda
        agenda.agregarContacto(contacto1);
        agenda.agregarContacto(contacto2);
        agenda.agregarContacto(contacto12);
        agenda.agregarContacto(contacto13);
        agenda.agregarContacto(contacto14);
        agenda.agregarContacto(contacto15);
        agenda.agregarContacto(contacto16);



        // Guardar en archivo binario
        agenda.guardarContactos("contactos.bin");
    }
}

