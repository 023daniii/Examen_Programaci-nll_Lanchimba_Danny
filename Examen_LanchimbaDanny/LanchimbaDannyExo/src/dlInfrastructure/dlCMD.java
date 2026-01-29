package dlInfrastructure;
import java.io.FileWriter;
import java.io.PrintWriter;

public class dlCMD {
    public static void dlImprimir(String msg) {
        System.out.println(msg);
        try (PrintWriter out = new PrintWriter(new FileWriter("storage/DataFiles/ExoTracer.txt", true))) {
            out.println(msg);
        } catch (Exception e) { }
    }
    public static void printlnError(String msg) {
        System.err.println(msg);
        dlImprimir(msg);
    }
}