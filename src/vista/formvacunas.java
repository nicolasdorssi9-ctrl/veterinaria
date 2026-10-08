package vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.List;
import com.formdev.flatlaf.FlatDarkLaf;
import dominio.SistemaVacunas;
import dominio.vacunas;

public class formvacunas extends JFrame {

    private final SistemaVacunas sistema = new SistemaVacunas();

    private JLabel labeltipo;
    private JTextField txttipo;
    private JLabel labelfecha;
    private JTextField txtfecha;
    private JLabel labelhora;
    private JTextField txthora;
    private JLabel labelvencimiento;
    private JTextField txtvencimiento;
    private JTable table12;
    private DefaultTableModel modeloTable12;
    private JButton btn13;
    private JButton btn14;
    private JButton btn15;
    private JButton btn16;

// [USER_DECLS_START]
//  DECLARA TUS VARIABLES AQUI (Ej: private int contador;)
    // ----------------------------------------------------
// [USER_DECLS_END]

    public formvacunas() {
    
        setTitle("Formulario de Vacunas");
        setSize(500, 410);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        initComponents();
        registrarEventos();
        cargarTabla();

    }

    private void initComponents() {
        labeltipo = new JLabel("Tipo");
        labeltipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labeltipo.setBounds(17, 7, 80, 20);
        txttipo = new JTextField();
        txttipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txttipo.setBounds(100, 6, 380, 22);
        labelfecha = new JLabel("Fecha");
        labelfecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelfecha.setBounds(17, 37, 80, 20);
        txtfecha = new JTextField();
        txtfecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtfecha.setBounds(100, 36, 380, 22);
        labelhora = new JLabel("Hora");
        labelhora.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelhora.setBounds(17, 67, 80, 20);
        txthora = new JTextField();
        txthora.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txthora.setBounds(100, 66, 380, 22);
        labelvencimiento = new JLabel("Vencimiento");
        labelvencimiento.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelvencimiento.setBounds(17, 97, 85, 20);
        txtvencimiento = new JTextField();
        txtvencimiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtvencimiento.setBounds(100, 96, 380, 22);

        modeloTable12 = new DefaultTableModel(
            new String[]{"ID", "Tipo", "Fecha", "Hora", "Vencimiento"}, 0
        ) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table12 = new JTable(modeloTable12);
        table12.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table12.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll_table12 = new JScrollPane(table12);
        scroll_table12.setBounds(13, 180, 471, 190);
        btn13 = new JButton("Agregar");
        btn13.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btn13.setBounds(35, 137, 100, 25);
        btn14 = new JButton("Modificar");
        btn14.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btn14.setBounds(145, 137, 100, 25);
        btn15 = new JButton("Eliminar");
        btn15.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btn15.setBounds(255, 137, 100, 25);
        btn16 = new JButton("Limpiar");
        btn16.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btn16.setBounds(365, 137, 100, 25);
        getContentPane().add(labeltipo);
        getContentPane().add(txttipo);
        getContentPane().add(labelfecha);
        getContentPane().add(txtfecha);
        getContentPane().add(labelhora);
        getContentPane().add(txthora);
        getContentPane().add(labelvencimiento);
        getContentPane().add(txtvencimiento);
        getContentPane().add(scroll_table12);
        getContentPane().add(btn13);
        getContentPane().add(btn14);
        getContentPane().add(btn15);
        getContentPane().add(btn16);
    }

// ------------------------------------------------------------------
    // Eventos
    // ------------------------------------------------------------------
    private void registrarEventos() {
        table12.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarCamposDesdeFilaSeleccionada();
            }
        });

        btn13.addActionListener(e -> agregarvacuna());
        btn14.addActionListener(e -> modificarVacuna());
        btn15.addActionListener(e -> eliminarVacuna());
        btn16.addActionListener(e -> limpiarCampos());
    }

    // ------------------------------------------------------------------
    // Operaciones
    // ------------------------------------------------------------------
    private void agregarvacuna() {
        if (!camposValidos()) {
            return;
        }
        vacunas nueva = leerCampos(0);
        int id = sistema.guardar(nueva);
        if (id == -1) {
            JOptionPane.showMessageDialog(this, "No se pudo agregar la vacuna.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Vacuna agregada con ID " + id);
        cargarTabla();
        limpiarCampos();
    }

    private void modificarVacuna() {
        int fila = table12.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una vacuna de la tabla primero.");
            return;
        }
        if (!camposValidos()) {
            return;
        }
        int id = Integer.parseInt(table12.getValueAt(fila, 0).toString());
        vacunas vacuna = leerCampos(id);

        if (sistema.actualizar(vacuna)) {
            JOptionPane.showMessageDialog(this, "Vacuna modificada correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontro la vacuna con ID " + id);
        }
        cargarTabla();
        limpiarCampos();
    }

    private void eliminarVacuna() {
        int fila = table12.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una vacuna de la tabla primero.");
            return;
        }
        int id = Integer.parseInt(table12.getValueAt(fila, 0).toString());

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres eliminar a la vacuna con ID " + id + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        if (sistema.eliminar(id)) {
            JOptionPane.showMessageDialog(this, "Vacuna eliminada.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontro la vacuna con ID " + id);
        }
        cargarTabla();
        limpiarCampos();
    }

    // ------------------------------------------------------------------
    // Metodos auxiliares
    // ------------------------------------------------------------------
    private void cargarTabla() {
        modeloTable12.setRowCount(0);
        List<vacunas> vacunas = sistema.listar();
        for (vacunas v : vacunas) {
            modeloTable12.addRow(new Object[]{
                v.getId_vacunas(),
                v.getTipo(),
                v.getFecha(),
                v.getHora(),
                v.getVencimiento()
            });
        }
    }

    private void cargarCamposDesdeFilaSeleccionada() {
        int fila = table12.getSelectedRow();
        if (fila < 0) {
            return;
        }
        txttipo.setText(String.valueOf(table12.getValueAt(fila, 1)));
        txtfecha.setText(String.valueOf(table12.getValueAt(fila, 2)));
        txthora.setText(String.valueOf(table12.getValueAt(fila, 3)));
        txtvencimiento.setText(String.valueOf(table12.getValueAt(fila, 4)));
    }

    private vacunas leerCampos(int id) {
        return new vacunas(
                id,
                txttipo.getText().trim(),
                txtfecha.getText().trim(),
                txthora.getText().trim(),
                txtvencimiento.getText().trim());
    }

    private boolean camposValidos() {
        if (txttipo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El tipo es obligatorio.");
            txttipo.requestFocus();
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txttipo.setText("");
        txtfecha.setText("");
        txthora.setText("");
        txtvencimiento.setText("");
        table12.clearSelection();
        txttipo.requestFocus();
    }

    // ------------------------------------------------------------------
    // Punto de entrada de la aplicacion
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(() -> new formvacunas().setVisible(true));
    }


}