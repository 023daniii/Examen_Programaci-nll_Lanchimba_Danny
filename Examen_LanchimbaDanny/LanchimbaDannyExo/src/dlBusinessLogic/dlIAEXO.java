package dlBusinessLogic;

/**
 * © 2K26 ❱──💀──❰ dlIAEXO
 * Implementa la lógica de entrenamiento asistida por IABOT.
 */
public abstract class dlIAEXO implements dlIIAEXO {
    protected boolean dlEntreno = false;

    @Override
    public void dlEntrenar() {
        this.dlEntreno = true;
    }

    @Override
    public boolean dlGarantizarAccion(String tipo) {
        // Solo permite la acción si el entrenamiento es exitoso (dlEntreno = true)
        return dlEntreno;
    }
}