package Infrastructure;

public class dlAppException extends Exception {

    public dlAppException(String message) {
        super(message);
        // Opcional: Registrar el error automáticamente en consola al crearse
        System.out.println("[ERROR para el log]: " + message);
    }

    public dlAppException(Throwable cause, String message) {
        super(message, cause);
    }
}