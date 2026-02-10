package UserInterface;

import BusinessLogic.Entities.*;
import DataAccess.DAO.dlGatoDAO;
import DataAccess.DTO.dlDataFileHelperDTO;
import Infrastructure.dlAppMSG;
import Infrastructure.dlCMD;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class dlLanchimbaMutaGenoForm extends JFrame {
    private JComboBox<String> dlCbxAnimales;
    private JTable dlTable;
    private DefaultTableModel dlTableModel;
    private JButton dlBtnAgregar5, dlBtnMutar, dlBtnEntrenar, dlBtnSelector, dlBtnDisparar;
    private String dlArmaSeleccionada = "Arma 1"; 
    private final dlGatoDAO dlDao = new dlGatoDAO();

    private final String dlBiologo = "Lanchimba Danny";
    private final String dlExtremidad = "pata"; 

    public dlLanchimbaMutaGenoForm() {
        setTitle("Examen Parcial - Lanchimba Danny");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        dlConfigurarPanelNorte();
        dlConfigurarPanelCentral();
        dlConfigurarPanelSur();
        dlConfigurarEventos();
    }

    private void dlConfigurarPanelNorte() {
        JPanel dlTopPanel = new JPanel();
        dlTopPanel.setLayout(new BoxLayout(dlTopPanel, BoxLayout.Y_AXIS));
        dlTopPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JLabel dlLblAlumno = new JLabel("Alumno:");
        dlLblAlumno.setFont(new Font("Arial", Font.BOLD, 14));
        
        JPanel dlInfoCuadro = new JPanel(new GridLayout(2, 2));
        dlInfoCuadro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dlInfoCuadro.setBackground(Color.WHITE);
        dlInfoCuadro.add(new JLabel("  1050149515"));
        dlInfoCuadro.add(new JLabel("  " + dlBiologo));
        dlInfoCuadro.add(new JLabel("  Cédula"));
        dlInfoCuadro.add(new JLabel("  Nombre"));

        dlTopPanel.add(dlLblAlumno);
        dlTopPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dlTopPanel.add(dlInfoCuadro);
        add(dlTopPanel, BorderLayout.NORTH);
    }

    private void dlConfigurarPanelCentral() {
        JPanel dlCenterPanel = new JPanel(new BorderLayout(5, 5));
        dlCenterPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JPanel dlAddPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dlCbxAnimales = new JComboBox<>(new String[]{"Gato"}); 
        dlBtnAgregar5 = new JButton("Agregar 5 Animales");
        dlAddPanel.add(dlCbxAnimales);
        dlAddPanel.add(dlBtnAgregar5);

        String[] dlColumnas = {"ID", "Animal", "Estado", "Entrenado", "Disparos"};
        dlTableModel = new DefaultTableModel(dlColumnas, 0); 
        dlTable = new JTable(dlTableModel);
        
        JTableHeader header = dlTable.getTableHeader();
        header.setBackground(new Color(2, 48, 32));
        header.setForeground(Color.WHITE);

        dlCenterPanel.add(dlAddPanel, BorderLayout.NORTH);
        dlCenterPanel.add(new JScrollPane(dlTable), BorderLayout.CENTER);
        add(dlCenterPanel, BorderLayout.CENTER);
    }

    private void dlConfigurarPanelSur() {
        JPanel dlBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        dlBtnMutar = new JButton("Mutar");
        dlBtnDisparar = new JButton("Accion \"Disparar\"");

        JPanel dlPanelSplit = new JPanel(new BorderLayout(0, 0));
        dlBtnEntrenar = new JButton("Entrenar " + dlArmaSeleccionada);
        dlBtnEntrenar.setPreferredSize(new Dimension(160, 35));
        dlBtnSelector = new JButton("▼"); 
        dlBtnSelector.setPreferredSize(new Dimension(30, 35));

        dlPanelSplit.add(dlBtnEntrenar, BorderLayout.CENTER);
        dlPanelSplit.add(dlBtnSelector, BorderLayout.EAST);

        dlBottomPanel.add(dlBtnMutar);
        dlBottomPanel.add(dlPanelSplit);
        dlBottomPanel.add(dlBtnDisparar);
        add(dlBottomPanel, BorderLayout.SOUTH);
    }

    private void dlConfigurarEventos() {
        JPopupMenu dlMenu = new JPopupMenu();
        JMenuItem m1 = new JMenuItem("Arma 1 (Laser)");
        JMenuItem m2 = new JMenuItem("Arma 2 (Bala)");
        dlMenu.add(m1); dlMenu.add(m2);

        dlBtnSelector.addActionListener(e -> dlMenu.show(dlBtnSelector, 0, dlBtnSelector.getHeight()));
        m1.addActionListener(e -> { dlArmaSeleccionada = "Arma 1"; dlBtnEntrenar.setText("Entrenar Arma 1"); });
        m2.addActionListener(e -> { dlArmaSeleccionada = "Arma 2"; dlBtnEntrenar.setText("Entrenar Arma 2"); });

        // AGREGAR 5 (Notificación solo en CONSOLA vía dlCMD)
        dlBtnAgregar5.addActionListener(e -> {
            int dlIdActual = dlTableModel.getRowCount();
            for (int i = 1; i <= 5; i++) {
                dlTableModel.addRow(new Object[]{dlIdActual + i, "Gato", "Normal", "No", "0"});
            }
            dlCMD.dlWriteTracer("GOOD: Se han inyectado 5 especímenes al laboratorio por " + dlBiologo); // Punto 10
            dlDao.dlRegistrarActividad("Biologo Danny agregó 5 especímenes.");
        });

        // MUTAR (Con validación de Gen XY y Cédula Impar)
        dlBtnMutar.addActionListener(e -> {
            int f = dlTable.getSelectedRow();
            if (f != -1) {
                Object dlId = dlTableModel.getValueAt(f, 0);
                if (dlTableModel.getValueAt(f, 2).equals("GatoLanchimba")) {
                    dlManejarFallo("El gato con ID " + dlId + " ya está mutado.");
                } else {
                    // --- INTEGRACIÓN LÓGICA DE NEGOCIO ---
                    dlMoleGen dlMolecula = new dlMoleGen(new dlChip("CHIP-" + dlId), new dlGen()); // Punto 3 (Gen XY)
                    dlGatoLanchimba dlMutante = dlTanqueMutacion.dlGetInstance().dlMutar(new dlGato(), dlMolecula); // Punto 4
                    
                    if (dlMutante != null) {
                        dlTableModel.setValueAt("GatoLanchimba", f, 2);
                        dlCMD.dlWriteTracer("GOOD: Gato ID " + dlId + " mutado a Macho exitosamente."); // Punto 10
                        dlDao.dlRegistrarActividad("GOOD: Mutación ID " + dlId + " completada.");
                    }
                }
            } else {
                dlManejarFallo("Error: Debe seleccionar un espécimen de la tabla.");
            }
        });

        // ENTRENAR (Punto 8: Doble entrenamiento por nombre Danny)
        dlBtnEntrenar.addActionListener(e -> {
            int f = dlTable.getSelectedRow();
            if (f != -1) {
                Object dlId = dlTableModel.getValueAt(f, 0);
                dlTableModel.setValueAt("SI (" + dlArmaSeleccionada + ")", f, 3);
                dlTableModel.setValueAt("0", f, 4); // Reinicia contador
                
                dlCMD.dlWriteTracer("GOOD: Gato ID " + dlId + " entrenado en " + dlArmaSeleccionada); // Punto 8k
                dlDao.dlRegistrarActividad("GOOD: Entrenamiento ID " + dlId + " completado.");
            } else {
                dlManejarFallo("Error: Seleccione un ID para entrenar la " + dlExtremidad + ".");
            }
        });

        // DISPARAR (Punto 6: Dos armas y Punto 7: Contador)
        dlBtnDisparar.addActionListener(e -> {
            int f = dlTable.getSelectedRow();
            if (f == -1) { dlManejarFallo("Error: Seleccione un gato antes de disparar."); return; }
            
            Object dlId = dlTableModel.getValueAt(f, 0);
            String estado = dlTableModel.getValueAt(f, 2).toString();
            String entreno = dlTableModel.getValueAt(f, 3).toString();

            if (estado.equals("GatoLanchimba") && entreno.startsWith("SI")) {
                // --- INTEGRACIÓN ARCHIVO MUNICIÓN ---
                String dlMunicionActual = dlDataFileHelperDTO.dlGetNextMunicion(); // Punto 6
                
                // Punto 6j: Cédula empieza en 1 (impar) -> Dispara dos armas
                String dlLogMsg = "GOOD: Gato ID " + dlId + " disparo Arma 1 y Arma 2 con " + dlMunicionActual;
                dlCMD.dlWriteTracer(dlLogMsg); // Punto 7 y 10
                
                int n = Integer.parseInt(dlTableModel.getValueAt(f, 4).toString());
                dlTableModel.setValueAt(String.valueOf(n + 1), f, 4);
                dlDao.dlRegistrarActividad(dlLogMsg);
            } else {
                dlManejarFallo("ERROR: El gato con ID " + dlId + " requiere Mutación y Entrenamiento.");
            }
        });
    }

    private void dlManejarFallo(String msg) {
        dlCMD.dlWriteTracer("FAIL: " + msg); // Punto 10
        dlAppMSG.dlMostrarError(msg);
        dlDao.dlRegistrarActividad("FAIL: " + msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new dlLanchimbaMutaGenoForm().setVisible(true));
    }
}