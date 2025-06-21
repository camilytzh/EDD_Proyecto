package app;

import java.util.Scanner;

import java.io.File;
import java.util.Scanner;

public class SistemasContactos {
    public static void main(String[] args) {
        Agenda gestor = new Agenda();
        String archivo = "contactos.bin";

        // Cargar contactos automáticamente si el archivo existe
        File file = new File(archivo);
        if (file.exists()) {
            gestor.cargarContactos(archivo);
        } else {
            System.out.println("No hay contactos previos. Se iniciará con la agenda vacía.");
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Agregar Persona");
            System.out.println("2. Agregar Empresa");
            System.out.println("3. Mostrar Contactos Adelante");
            System.out.println("4. Mostrar Contactos Atrás");
            System.out.println("5. Eliminar Contacto");
            System.out.println("6. Guardar Contactos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    gestor.crearContactoPersona();
                    break;
                case 2:
                    gestor.añadirContactoEmpresa();
                    break;
                case 3:
                    gestor.mostrarContactosAdelante();
                    break;
                case 4:
                    gestor.mostrarContactosAtras();
                    break;
                case 5:
                    gestor.eliminarContacto();
                    break;
                case 6:
                    gestor.guardarContactos(archivo);
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    gestor.guardarContactos(archivo); // Guardar al salir
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
