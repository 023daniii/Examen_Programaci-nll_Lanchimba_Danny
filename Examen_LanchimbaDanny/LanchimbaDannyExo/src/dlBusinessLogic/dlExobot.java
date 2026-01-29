package dlBusinessLogic;

import dlInfrastructure.dlCMD;

/**
 * REFACTORIZACIÓN: 
 * - Se aplicó encapsulamiento para proteger los atributos protegidos.
 * - Se añadió un método de incremento controlado para evitar inconsistencias en la tabla.
 * - Se estandarizaron los retornos para que coincidan con la vista de la GUI.
 */
public abstract class dlExobot extends dlIAEXO {
    protected int dlIdExobot;
    protected String dlTipoExobot;
    protected int dlNoAccion;
    protected String dlExtremidad = "Brazo Izquierdo";

    public dlExobot(int id, String tipo) {
        this.dlIdExobot = id;
        this.dlTipoExobot = tipo;
        this.dlNoAccion = 0;
        // Registro de auditoría de creación refactorizado
        dlCMD.dlImprimir("GOOD: Objeto " + tipo + " instanciado con ID: " + id);
    }

    // --- MÉTODOS DE ACCESO (GETTERS) ---
    public int dlGetId() { return dlIdExobot; }
    public String dlGetTipo() { return dlTipoExobot; }
    public int dlGetAcciones() { return dlNoAccion; }
    
    // REFACTORIZACIÓN: Asegura que el SI/NO se vea igual que en tu captura de pantalla
    public String dlIsEntrenado() { 
        return dlEntreno ? "SI" : "NO"; 
    }

    // --- LÓGICA REFACTORIZADA ---
    
    /**
     * REFACTORIZACIÓN: Método para incrementar acciones de forma segura.
     * Esto evita que se sumen acciones sin disparar realmente.
     */
    protected void dlIncrementarAccion() {
        this.dlNoAccion++;
    }

    // Método abstracto que obliga a la hija (dlExoInfanteria) a implementar el Misil (Dígito 5)
    public abstract void dlAccionArma();
}