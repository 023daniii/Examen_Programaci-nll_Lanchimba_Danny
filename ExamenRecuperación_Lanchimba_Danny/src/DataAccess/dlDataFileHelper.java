package DataAccess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dlDataFileHelper {
    private static final String dlPATH_TRACER = "storage/DataFiles/MutaGenoTracer.txt";

    /**
     * Guarda cualquier acción en el archivo de texto.
     * Si las carpetas no existen, las crea.
     */
    public static void dlRegistrarAccion(String dlMensaje) {
        try {
            // Verificar si la carpeta existe, si no, crearla
            File dlDirectorio = new File("storage/DataFiles");
            if (!dlDirectorio.exists()) {
                dlDirectorio.mkdirs();
            }

            // Escribir en el archivo (true para no borrar lo anterior)
            try (FileWriter dlFw = new FileWriter(dlPATH_TRACER, true);
                 PrintWriter dlPw = new PrintWriter(dlFw)) {
                
                DateTimeFormatter dlDtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                dlPw.println(dlDtf.format(LocalDateTime.now()) + " - " + dlMensaje);
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el log: " + e.getMessage());
        }
    }
}