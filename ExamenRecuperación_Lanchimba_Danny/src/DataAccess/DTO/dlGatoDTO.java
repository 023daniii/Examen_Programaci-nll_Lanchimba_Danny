package DataAccess.DTO;

public class dlGatoDTO {
    private int dlId;
    private String dlNombre;
    private String dlEstado;

    public dlGatoDTO(int dlId, String dlNombre, String dlEstado) {
        this.dlId = dlId;
        this.dlNombre = dlNombre;
        this.dlEstado = dlEstado;
    }

    // Getters y Setters
    public int getDlId() { return dlId; }
    public void setDlId(int dlId) { this.dlId = dlId; }
    public String getDlNombre() { return dlNombre; }
    public void setDlNombre(String dlNombre) { this.dlNombre = dlNombre; }
    public String getDlEstado() { return dlEstado; }
    public void setDlEstado(String dlEstado) { this.dlEstado = dlEstado; }
}