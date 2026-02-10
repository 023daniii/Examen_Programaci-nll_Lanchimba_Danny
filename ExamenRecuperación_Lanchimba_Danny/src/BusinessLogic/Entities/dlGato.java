package BusinessLogic.Entities;

public class dlGato implements dlIAnimal {
    protected String dlExtremidad;

    public dlGato() {
        this.dlExtremidad = "pata"; // Requisito 5h: nombre termina en consonante
    }

    @Override
    public void dlShow() {
        System.out.println("Especie: Gato | Extremidad: " + dlExtremidad);
    }

    public String dlGetExtremidad() {
        return dlExtremidad;
    }
}