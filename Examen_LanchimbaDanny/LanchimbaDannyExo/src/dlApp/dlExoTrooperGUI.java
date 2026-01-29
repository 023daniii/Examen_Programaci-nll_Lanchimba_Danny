package dlApp;

import dlBusinessLogic.*;
import dlInfrastructure.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * © 2K26 ❱──💀──❰ dlExoTrooperGUI
 * Nombre: Lanchimba Danny - Cédula: 1050149515 (Dígito 5)
 * * NOTA DE REFACTORIZACIÓN: 
 * Eliminé la jerarquía de las clases que puse en
 * la arquitectura de las clases para simplificar el diseño, ya que no era algo que pedía en el examen
 * corrigiendo la instanciación de objetos según el diagrama de arquitectura.
 */

public class dlExoTrooperGUI extends JFrame {
    
    private JTable dlTabla;
    private DefaultTableModel dlModelo;
    private ArrayList<dlExobot> dlListaBots;

    public dlExoTrooperGUI() {
        dlListaBots = new ArrayList<>();
        dlConfigurarVentana();
        dlInicializarComponentes();
    }

    private void dlConfigurarVentana() {
        setTitle("ExoTrooper - Sistema de Defensa | Danny Lanchimba");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        setLocationRelativeTo(null);
    }

    private void dlInicializarComponentes() {
        // --- PANEL SUPERIOR: IDENTIDAD ---
        JPanel pnlNorte = new JPanel(new GridLayout(1, 2));
        pnlNorte.setBorder(BorderFactory.createTitledBorder("Estatus de Misión"));
        
        JLabel lblDatos = new JLabel("<html><b>ESTUDIANTE:</b> Lanchimba Danny<br><b>CÉDULA:</b> 1050149515</html>");
        JLabel lblCaso = new JLabel("<html><b style='color:green;'>UNIDAD: INFANTERÍA (DÍGITO 5)</b><br><b>ARMA:</b> MISIL / MORTERO</html>", SwingConstants.RIGHT);
        
        pnlNorte.add(lblDatos);
        pnlNorte.add(lblCaso);
        add(pnlNorte, BorderLayout.NORTH);

        // --- PANEL CENTRAL: TABLA (No editable) ---
        String[] columnas = {"IdExobot", "TipoExobot", "Entreno", "No. Accion"};
        
        // REFACTORIZACIÓN: Bloqueo de edición manual para integridad de datos
        dlModelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } 
        };
        
        dlTabla = new JTable(dlModelo);
        dlTabla.getTableHeader().setBackground(new Color(45, 70, 45));
        dlTabla.getTableHeader().setForeground(Color.WHITE);
        add(new JScrollPane(dlTabla), BorderLayout.CENTER);

        // --- PANEL SUR: COMANDOS ---
        JPanel pnlSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton btnAgregar  = new JButton("➕ Agregar");
        JButton btnBuscar   = new JButton("🔍 Buscar (ID)"); 
        JButton btnEliminar = new JButton("🗑️ Eliminar");
        JButton btnEntrenar = new JButton("🎯 Entrenar");
        JButton btnAccion   = new JButton("🚀 Disparar Misil");

        // --- LÓGICA DE EVENTOS REFACTORIZADA ---

        // 1. AGREGAR (Infantería únicamente por Dígito 5)
        btnAgregar.addActionListener(e -> {
            // REFACTORIZACIÓN: Se eliminó dlExoAsalto por no ser parte del caso de estudio
            dlExobot nuevo = new dlExoInfanteria(dlListaBots.size() + 1);
            dlListaBots.add(nuevo);
            dlModelo.addRow(new Object[]{nuevo.dlGetId(), nuevo.dlGetTipo(), nuevo.dlIsEntrenado(), nuevo.dlGetAcciones()});
            dlCMD.dlImprimir("GOOD: ExoInfanteria desplegado (Módulo Refactorizado).");
        });

        // 2. BUSCAR (Punto Extra)
        btnBuscar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del Robot a buscar:");
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int idBusqueda = Integer.parseInt(idStr);
                    boolean encontrado = false;
                    dlModelo.setRowCount(0); 
                    for (dlExobot bot : dlListaBots) {
                        if (bot.dlGetId() == idBusqueda) {
                            dlModelo.addRow(new Object[]{bot.dlGetId(), bot.dlGetTipo(), bot.dlIsEntrenado(), bot.dlGetAcciones()});
                            encontrado = true;
                        }
                    }
                    if (!encontrado) dlAppMSG.show("Robot con ID " + idBusqueda + " no encontrado.");
                    dlCMD.dlImprimir("GOOD: Búsqueda por ID realizada exitosamente.");
                } catch (NumberFormatException ex) {
                    dlAppMSG.show("Por favor ingrese un número válido.");
                }
            }
        });

        // 3. ELIMINAR
        btnEliminar.addActionListener(e -> {
            int fila = dlTabla.getSelectedRow();
            if (fila != -1) {
                int idAEliminar = (int) dlModelo.getValueAt(fila, 0);
                dlListaBots.removeIf(b -> b.dlGetId() == idAEliminar);
                dlModelo.removeRow(fila);
                dlCMD.dlImprimir("GOOD: Unidad retirada. Memoria refactorizada.");
            }
        });

        // 4. ENTRENAR
        btnEntrenar.addActionListener(e -> {
            int fila = dlTabla.getSelectedRow();
            if (fila != -1) {
                int id = (int) dlModelo.getValueAt(fila, 0);
                for (dlExobot bot : dlListaBots) {
                    if (bot.dlGetId() == id) {
                        bot.dlEntrenar();
                        dlModelo.setValueAt(bot.dlIsEntrenado(), fila, 2);
                        break;
                    }
                }
            }
        });

        // 5. DISPARAR (Dígito 5)
        btnAccion.addActionListener(e -> {
            int fila = dlTabla.getSelectedRow();
            if (fila != -1) {
                int id = (int) dlModelo.getValueAt(fila, 0);
                for (dlExobot bot : dlListaBots) {
                    if (bot.dlGetId() == id) {
                        bot.dlAccionArma(); 
                        dlModelo.setValueAt(bot.dlGetAcciones(), fila, 3);
                        break;
                    }
                }
            }
        });

        pnlSur.add(btnAgregar);
        pnlSur.add(btnBuscar);
        pnlSur.add(btnEliminar);
        pnlSur.add(btnEntrenar);
        pnlSur.add(btnAccion);
        add(pnlSur, BorderLayout.SOUTH);
        
        // Log de inicio para auditoría
        dlCMD.dlImprimir("SISTEMA INICIADO: Aplicada refactorización de la arquitectura hecha en el examen.");
    }
}