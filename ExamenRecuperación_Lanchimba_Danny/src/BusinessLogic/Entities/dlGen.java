package BusinessLogic.Entities;

public class dlGen {
    private String dlTipo;

    public dlGen() {
        this.dlTipo = "XY"; // Requisito 3c: usted es hombre
    }

    public String getDlTipo() {
        return dlTipo;
    }

    public void setDlTipo(String dlTipo) {
        this.dlTipo = dlTipo;
    }
}