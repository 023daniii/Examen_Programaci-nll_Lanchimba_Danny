package DataAccess.DAO;

import DataAccess.DTO.dlGatoDTO;
import DataAccess.dlDataFileHelper;

public class dlGatoDAO {

    /**
     * Valida el acceso con las credenciales del examen
     */
    public dlGatoDTO dlLogin(String dlUser, String dlPass) {
        if (dlUser.equals("patmic") && dlPass.equals("1234")) {
            return new dlGatoDTO(1, "Lanchimba Danny", "AUTORIZADO");
        }
        return null; 
    }

    /**
     * Registra actividad llamando al Helper
     */
    public void dlRegistrarActividad(String mensaje) {
        dlDataFileHelper.dlRegistrarAccion(mensaje);
    }

    /**
     * Lee la munición (Asegúrate de tener el archivo Munision.txt en storage/DataFiles)
     */
    public String dlObtenerMunicion(String arma) {
        // Por ahora retorna un valor por defecto si no tienes el lector de archivos listo
        return "Proyectil MutaGeno"; 
    }
}