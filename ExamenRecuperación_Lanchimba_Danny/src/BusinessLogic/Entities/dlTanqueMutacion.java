package BusinessLogic.Entities;

public class dlTanqueMutacion {
    private static dlTanqueMutacion dlInstance;

    private dlTanqueMutacion() {}

    public static dlTanqueMutacion dlGetInstance() {
        if (dlInstance == null) {
            dlInstance = new dlTanqueMutacion();
        }
        return dlInstance;
    }

    public dlGatoLanchimba dlMutar(dlGato gato, dlMoleGen molecula) {
        // Requisito 4f: Si cédula es impar (15) y existe Chip/Gen -> Macho
        if (molecula != null) {
            return new dlGatoLanchimba("Macho");
        }
        return null;
    }
}