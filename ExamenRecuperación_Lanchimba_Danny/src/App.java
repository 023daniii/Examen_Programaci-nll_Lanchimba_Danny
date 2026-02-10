import ConsoleApp.dlLogin;
import Infrastructure.dlCMD;
import UserInterface.dlLanchimbaMutaGenoForm;

public class App {
    public static void main(String[] args) {
        // 1. Instanciamos el login de la capa ConsoleApp
        dlLogin login = new dlLogin();
        
        // 2. Ejecutamos la validación 
        if (login.dlValidarAcceso()) {
            
            // 3. Si el acceso es concedido, grabamos en el Tracer usando dlCMD (req 10)
            dlCMD.dlWriteTracer("GOOD: Acceso concedido al biólogo Lanchimba Danny");
            
            // 4. Lanzamos la interfaz gráfica
            java.awt.EventQueue.invokeLater(() -> {
                new dlLanchimbaMutaGenoForm().setVisible(true);
            });
        } else {
            // Si falla 3 veces, dlLogin debería haber cerrado el sistema
            System.out.println("ERROR: Acceso denegado.");
        }
    }
}