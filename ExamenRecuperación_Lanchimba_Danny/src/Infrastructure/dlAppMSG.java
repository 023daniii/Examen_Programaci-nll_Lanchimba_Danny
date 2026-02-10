package Infrastructure;

import javax.swing.JOptionPane;

public class dlAppMSG {
    public static final String dlMSG_LOGIN_EXITO = "Acceso concedido.";
    public static final String dlMSG_LOGIN_FALLO = "Credenciales incorrectas.";

    public static void dlMostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Lanchimba MutaGeno", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void dlMostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}