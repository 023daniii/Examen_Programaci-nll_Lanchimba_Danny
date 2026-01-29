package dlConsoleApp;

import dlApp.dlExoTrooperGUI;
import dlInfrastructure.*;
import javax.swing.SwingUtilities;

/**
 * © 2K26 ❱──💀──❰ dlMain
 * REFACTORIZACIÓN: Optimización de flujos de seguridad y carga de arquitectura.
 */
public class dlMain {
    public static void main(String[] args) {
        // 1. Identificación del Sistema (Manteniendo tu nombre solo como autor del examen)
        dlCMD.dlImprimir("=== SISTEMA EXOTROOPER ==="); 
        dlCMD.dlImprimir("Estudiante: Danny Lanchimba | Cédula: 1050149515");
        
        // REFACTORIZACIÓN: Mensaje técnico limpio (Corrigiendo el log de la captura previa)
        dlCMD.dlImprimir("SISTEMA INICIADO: Aplicada refactorización de arquitectura.");

        int intentos = 0;
        boolean acceso = false;

        // 2. Proceso de Autenticación con credenciales del Ingeniero
        while (intentos < 3 && !acceso) {
            String u = dlCMDInput.dlLeerString("Usuario (INGpatmic)");
            String p = dlCMDInput.dlLeerString("Clave (12345)");

            if (u.equals("INGpatmic") && p.equals("12345")) { 
                acceso = true;
                dlCMD.dlImprimir("GOOD: Acceso concedido. Perfil validado.");
            } else {
                intentos++;
                dlCMD.printlnError("ERROR: Acceso denegado (" + intentos + "/3)"); 
            }
        }

        // 3. Lanzamiento de la Interfaz Gráfica
        if (acceso) {
            dlCMDProgress.dlMostrarCarga();
            
            // Mensaje de despliegue para el Tracer
            dlCMD.dlImprimir("GOOD: Desplegando dlExoTrooperGUI (Unidad Infantería - Dígito 5).");

            SwingUtilities.invokeLater(() -> {
                try {
                    dlExoTrooperGUI gui = new dlExoTrooperGUI(); 
                    gui.setVisible(true);
                    gui.setLocationRelativeTo(null); 
                } catch (Exception e) {
                    dlCMD.printlnError("ERROR: Fallo al cargar interfaz refactorizada: " + e.getMessage());
                }
            });
        } else {
            dlCMD.printlnError("SISTEMA BLOQUEADO: Registro de seguridad generado en ExoTracer.txt.");
            System.exit(0);
        }
    }
}