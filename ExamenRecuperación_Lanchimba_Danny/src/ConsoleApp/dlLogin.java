package ConsoleApp;

import java.util.Scanner;

public class dlLogin {
    // Este es el método que tu Main (App.java) está buscando
    public boolean dlValidarAcceso() {
        try (Scanner sc = new Scanner(System.in)) {
            String dlUsuarioPermitido = "patmic";
            String dlClavePermitida = "1234";
            int dlIntentos = 0;

            while (dlIntentos < 3) {
                System.out.println("\n--- LOGIN BIÓLOGO ---");
                System.out.print("Usuario: ");
                String user = sc.nextLine();
                System.out.print("Clave: ");
                String pass = sc.nextLine();

                // Requisito 1: Validar acceso a "patmic" y "1234"
                if (user.equals(dlUsuarioPermitido) && pass.equals(dlClavePermitida)) {
                    System.out.println("GOOD: Acceso concedido");
                    return true; 
                } else {
                    dlIntentos++;
                    System.out.println("ERROR: Acceso denegado. Intento " + dlIntentos + "/3");
                }
            }
        }
        System.out.println("SISTEMA BLOQUEADO: Demasiados intentos fallidos.");
        System.exit(0); // Cierra el programa si falla 3 veces
        return false;
    }
}