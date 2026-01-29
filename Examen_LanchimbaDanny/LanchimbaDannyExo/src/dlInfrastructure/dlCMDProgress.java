package dlInfrastructure;

import static java.lang.Thread.sleep;

/**
 * © 2K26 ❱──💀──❰ dlCMDProgress
 * Simulación visual de carga en consola para el inicio del sistema.
 */
public class dlCMDProgress {
    
    /**
     * Muestra una barra de progreso animada en la terminal.
     */
    public static void dlMostrarCarga() {
        System.out.print("Iniciando Sistema ExoTrooper: [");
        for (int i = 0; i < 20; i++) {
            System.out.print("=");
            try { 
                // Pausa de 30ms para simular el procesamiento
                sleep(30); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("] 100% COMPLETO");
        System.out.println("Cargando entorno gráfico...");
    }
}