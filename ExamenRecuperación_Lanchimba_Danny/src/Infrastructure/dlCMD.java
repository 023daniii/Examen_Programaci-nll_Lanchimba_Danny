package Infrastructure;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class dlCMD {
    // La ruta debe ser exacta a tu carpeta storage
    private static final String dlPath = "storage/DataFiles/MutaGenoTracer.txt";

    // Debe ser STATIC para que App.java lo reconozca sin hacer "new"
    public static void dlWriteTracer(String dlMensaje) {
        // Imprime en consola
        System.out.println(dlMensaje);

        // Intenta escribir en el archivo
        try (FileWriter fw = new FileWriter(dlPath, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(dlMensaje);
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }
}