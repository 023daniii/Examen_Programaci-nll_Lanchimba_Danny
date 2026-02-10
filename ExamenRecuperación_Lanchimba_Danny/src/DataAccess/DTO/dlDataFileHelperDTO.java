package DataAccess.DTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class dlDataFileHelperDTO {

    private static final String dlPathMunision = "storage/DataFiles/Munision.txt";
    private static int dlIndiceActual = 0;

    public static String dlGetNextMunicion() {
        List<String> dlMuniciones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dlPathMunision))) {
            String dlLinea;
            while ((dlLinea = br.readLine()) != null) {
                if (!dlLinea.trim().isEmpty()) {
                    dlMuniciones.add(dlLinea.trim());
                }
            }
        } catch (IOException e) {
            return "BALA (Error lectura)";
        }

        if (dlMuniciones.isEmpty()) {
            return "SIN MUNICIÓN";
        }

        // Retorna la siguiente munición de forma circular
        String dlSeleccionada = dlMuniciones.get(dlIndiceActual % dlMuniciones.size());
        dlIndiceActual++;
        return dlSeleccionada;
    }
}
