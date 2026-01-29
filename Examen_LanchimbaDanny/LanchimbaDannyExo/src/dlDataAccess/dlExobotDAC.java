package dlDataAccess;

import dlInfrastructure.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * © 2K26 ❱──💀──❰ dlExobotDAC
 * REFACTORIZACIÓN: Se implementó un método estático de consulta genérica para 
 * desacoplar la lógica de archivos de la lógica de negocio.
 */
public class dlExobotDAC {

    /**
     * REFACTORIZACIÓN: Método optimizado para lectura de recursos.
     * Se cambió a un manejo de excepciones más robusto y se integró el Tracer Tracer.
     */
    public List<String> dlLeerRecursos(String filtro) {
        List<String> encontrados = new ArrayList<>();
        
        // REFACTORIZACIÓN: Validación de ruta centralizada en dlAppConfig
        try (BufferedReader br = new BufferedReader(new FileReader(dlAppConfig.DL_DATA_FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Refactorización: Uso de regex para mayor flexibilidad en el split
                String[] items = linea.split("\\s*,\\s*"); 
                for (String item : items) {
                    if (item.toUpperCase().contains(filtro.toUpperCase())) {
                        encontrados.add(item.trim());
                    }
                }
            }
            
            // Log de éxito refactorizado para el Tracer
            if (!encontrados.isEmpty()) {
                dlCMD.dlImprimir("GOOD: [Refactor DAC] Recursos encontrados para: " + filtro);
            }

        } catch (Exception e) {
            // Refactorización del manejo de errores para auditoría
            dlCMD.dlImprimir("ERROR: [Refactor DAC] Fallo al leer " + dlAppConfig.DL_DATA_FILE + " : " + e.getMessage());
        }
        return encontrados;
    }
}