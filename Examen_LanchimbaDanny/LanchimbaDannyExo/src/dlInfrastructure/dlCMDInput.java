package dlInfrastructure;

import java.util.Scanner;

/**
 * © 2K26 ❱──💀──❰ dlCMDInput
 * Herramienta para la lectura validada de datos desde la terminal.
 */
public class dlCMDInput {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Lee una cadena de texto desde la consola asegurando que no sea nula o vacía.
     * @param etiqueta El texto descriptivo que se muestra al usuario (ej. "Usuario").
     * @return La cadena de texto ingresada por el usuario.
     */
    public static String dlLeerString(String etiqueta) {
        String entrada = "";
        while (entrada.trim().isEmpty()) {
            System.out.print(etiqueta + ": ");
            entrada = scanner.nextLine();
            
            if (entrada.trim().isEmpty()) {
                System.out.println("  (!) El dato no puede estar vacío. Inténtelo de nuevo.");
            }
        }
        return entrada;
    }

    /**
     * Método adicional para leer enteros si los necesitas en el futuro.
     */
    public static int dlLeerEntero(String etiqueta) {
        System.out.print(etiqueta + ": ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("  (!) Error: Debe ingresar un número entero.");
            return dlLeerEntero(etiqueta);
        }
    }
}