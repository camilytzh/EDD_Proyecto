package modelo;

public class GeneradorDatosIniciales {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        // Crear contactos de prueba
        ContactoPersonal contacto1 = new ContactoPersonal("Ana Torres", "Mejor amiga");
        contacto1.getTelef().put("celular", "0991234567");
        contacto1.getEmails().put("personal", "ana.torres@gmail.com");
        contacto1.getRedesSociales().put("instagram", "@ana.torres");
        contacto1.agregarFoto("ana1.jpg");
        contacto1.agregarFechaDeInteres("Cumpleaños", "1998-04-25");

        ContactoLaboral contacto2 = new ContactoLaboral("Carlos Ruiz", "Empresa XYZ");
        contacto2.getTelef().put("trabajo", "042555000");
        contacto2.getEmails().put("corporativo", "cruiz@xyz.com");
        contacto2.agregarFoto("carlos_oficina.jpg");
        contacto2.agregarFechaDeInteres("Inicio Laboral", "2020-03-01");

        // Agregar a la agenda
        agenda.agregarContacto(contacto1);
        agenda.agregarContacto(contacto2);

        // Guardar en archivo binario
        agenda.guardarContactos("contactos.bin");
    }
}

