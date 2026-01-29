package dlInfrastructure;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * © 2K26 ❱──💀──❰ dlAppException
 * Clase para la gestión personalizada de errores del sistema ExoTrooper.
 * Estudiante: Lanchimba Danny
 */
public class dlAppException extends Exception {

    public dlAppException(String dlMensaje) {
        super(dlMensaje);
        dlLogException(dlMensaje);
    }

    public dlAppException(Exception e, String dlClase, String dlMetodo) {
        super("Error en " + dlClase + "." + dlMetodo + " -> " + e.getMessage());
        dlLogException(e, dlClase, dlMetodo);
    }

    /**
     * Almacena el error en el archivo de seguimiento ExoTracer.txt
     * cumpliendo con el requisito de Tracer del sistema.
     */
    private void dlLogException(String dlMsg) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dlFecha = dtf.format(LocalDateTime.now());
        
        try (PrintWriter dlOut = new PrintWriter(new FileWriter("storage/DataFiles/ExoTracer.txt", true))) {
            dlOut.println(dlFecha + " [ERROR] " + dlMsg);
        } catch (Exception dlEx) {
            System.err.println("No se pudo escribir en el Tracer: " + dlEx.getMessage());
        }
    }

    private void dlLogException(Exception e, String dlClase, String dlMetodo) {
        dlLogException("Clase: " + dlClase + " | Metodo: " + dlMetodo + " | Error: " + e.getMessage());
    }
}