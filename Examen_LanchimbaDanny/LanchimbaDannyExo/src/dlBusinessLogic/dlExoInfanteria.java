package dlBusinessLogic;
import dlDataAccess.dlExobotDAC;
import dlInfrastructure.dlCMD;
import java.util.List;

public class dlExoInfanteria extends dlExobot {
    public dlExoInfanteria(int id) { super(id, "ExoInfanteria"); }

    @Override
    public void dlAccionArma() {
        if (!dlGarantizarAccion("Misil/Mortero")) {
            dlCMD.printlnError("ERROR: ExoInfanteria NO ENTRENADO");
            return;
        }
        dlExobotDAC dac = new dlExobotDAC();
        List<String> recursos = dac.dlLeerRecursos("Munición");
        String usado = recursos.isEmpty() ? "Munición_Reserva" : recursos.get(0);
        this.dlNoAccion++;
        dlCMD.dlImprimir("GOOD: Misil/Mortero disparar " + usado + " usando " + this.dlExtremidad);
    }
}