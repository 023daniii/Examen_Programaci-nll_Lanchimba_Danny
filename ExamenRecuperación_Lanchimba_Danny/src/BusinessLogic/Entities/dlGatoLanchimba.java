package BusinessLogic.Entities;

public class dlGatoLanchimba extends dlGato {
    private String dlGenero;

    public dlGatoLanchimba(String dlGenero) {
        super();
        this.dlGenero = dlGenero; // Será "Macho" por cédula impar
    }

    @Override
    public void dlShow() {
        System.out.println("Especie: GatoLanchimba | Genero: " + dlGenero + " | Arma: Pata táctica");
    }
}