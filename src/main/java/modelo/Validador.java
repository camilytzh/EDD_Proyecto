package modelo;

import java.util.Scanner;

public class Validador {

    private static final Scanner scanner = new Scanner(System.in);

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
