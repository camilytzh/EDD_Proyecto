package modelo;

import java.util.Scanner;



//Esta es una clase que me ayuda a validar los inputs del usuario,
// para que ingrese lo que tenga que ingresar

public class Validador {

    //Nos ayuda tambien a tener una solo instancia de Scanner
    private static final Scanner scanner = new Scanner(System.in);


    //para numeros
    public static int pedirNumero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
    }


    //para Texto
    public static String pedirTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            if (!entrada.trim().isEmpty()) {
                return entrada;
            } else {
                System.out.println("Error: El texto no puede estar vacío.");
            }
        }
    }

}
