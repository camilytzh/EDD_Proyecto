package app;

import estructuras.Agenda;
import modelo.Validador;

import java.util.Scanner;

import java.io.File;
import java.util.Scanner;


//MAIN MENU DEL PROYECTO
public class SistemasContactos {
    public static void main(String[] args) {

        //instancia de mi gestor de mi agenda
        Agenda gestor = new Agenda();
        String archivo = "contactos.bin"; //archivo a usar en la serializacion

        // Cargar contactos automáticamente si el archivo existe
        File file = new File(archivo);
        if (file.exists()) {
            gestor.cargarContactos(archivo);
        } else {
            System.out.println("No hay contactos previos. Se iniciará con la agenda vacía.");
        }

        //menú
        while (true) {
            System.out.println("======== MENU DE OPCIONES ========");
            System.out.println("1. Agregar Persona");
            System.out.println("2. Agregar Empresa");
            System.out.println("3. Mostrar TODOS los Contactos");
            System.out.println("4. Mostrar Contactos Adelante");
            System.out.println("5. Mostrar Contactos Atrás");
            System.out.println("6. Eliminar Contacto");
            System.out.println("7. Guardar Contactos");
            System.out.println("8. Modificar Contacto ");
            System.out.println("9. Salir");
            System.out.println("==================================");


            int opcion = Validador.pedirNumero("Seleccione una opción: ");
            System.out.println("==================================");

            switch (opcion) {
                case 1:
                    gestor.crearContactoPersona();
                    break;
                case 2:
                    gestor.añadirContactoEmpresa();
                    break;
                case 3 :
                    gestor.mostrarContactosFiltradosUOrdenados();
                    break;
                case 4:
                    gestor.mostrarContactosAdelante();
                    break;
                case 5:
                    gestor.mostrarContactosAtras();
                    break;
                case 6:
                    gestor.eliminarContacto();
                    break;
                case 7:
                    gestor.guardarContactos(archivo);
                    break;
                case 8:
                    gestor.modificarAtributoContacto();
                    break;
                case 9:
                    System.out.println("Saliendo del programa...");
                    gestor.guardarContactos(archivo); // Guardar al salir
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
