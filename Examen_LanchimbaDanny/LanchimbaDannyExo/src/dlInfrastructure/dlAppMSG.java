package dlInfrastructure;

/**
 * © 2K26 ❱──💀──❰ dlAppMSG
 * Diccionario centralizado de mensajes y estados del sistema ExoTrooper.
 */
public class dlAppMSG {
  
    // Etiquetas de estado
    public static final String DL_MSG_ERROR = " [ ERROR ] ";
    public static final String DL_MSG_GOOD  = " [  OK   ] ";
    
    // Mensajes de Login
    public static final String DL_LOGIN_OK   = "Acceso concedido. Bienvenido al sistema.";
    public static final String DL_LOGIN_FAIL = "Acceso denegado. Credenciales incorrectas.";
    
    // Mensajes de Interfaz (GUI)
    public static final String DL_ERROR_SELECCION = "Por favor, seleccione un Exobot de la lista para continuar.";
    public static final String DL_CONFIRM_EXIT    = "¿Está seguro que desea salir del sistema ExoTrooper?";

    /**
     * Método de utilidad para mostrar mensajes rápidos en consola (Opcional)
     */
    public static void show(String msg) {
        System.out.println(DL_MSG_GOOD + msg);
    }
}