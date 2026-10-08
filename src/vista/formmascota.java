package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.*;

import com.formdev.flatlaf.FlatDarkLaf;

// [USER_IMPORTS_START]
//  ESCRIBE TUS IMPORTS AQUI (Ej: import java.io.*;)
    // ----------------------------------------------------
    import dominio.*;
// [USER_IMPORTS_END]

public class formmascota extends JFrame {

    private  SistemaMascota sistema = new SistemaMascota();

    private JLabel label1;
    private JTextField txtNombre;
    private JLabel label3;
    private JTextField txtEdad;
    private JLabel label5;
    private JTextField Especie;
    private JLabel label7;
    private JTextField txtRaza;
    private JLabel label9;
    private JTextField txtGenero;
    private JLabel label11;
    private JTextField txtPeso;
    private JLabel labelDueno;
    private JComboBox<Dueño> comboDuenos;
    private JTable table13;
    private DefaultTableModel modeloTable13;
    private JButton btn14;
    private JButton btn15;
    private JButton btn16;
    private JButton btn17;

// [USER_DECLS_START]
//  DECLARA TUS VARIABLES AQUI (Ej: private int contador;)
    // ----------------------------------------------------
// [USER_DECLS_END]

    public formmascota() {
    
        setTitle("Formulario de Mascotas");
        setSize(500, 410);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        initComponents();
        cargarDuenos();
        registrarEventos();
        cargarTabla();

    }

    private void initComponents() {
        label1 = new JLabel("Nombre");
        label1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label1.setBounds(17, 7, 80, 20);
        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtNombre.setBounds(82, 6, 404, 14);
        label3 = new JLabel("Edad");
        label3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label3.setBounds(20, 37, 80, 20);
        txtEdad = new JTextField();
        txtEdad.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtEdad.setBounds(82, 36, 401, 20);
        label5 = new JLabel("Especie");
        label5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label5.setBounds(16, 65, 80, 20);
        Especie = new JTextField();
        Especie.setFont(new Font("Tahoma", Font.PLAIN, 11));
        Especie.setBounds(82, 66, 397, 21);
        label7 = new JLabel("Raza");
        label7.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label7.setBounds(17, 97, 80, 20);
        txtRaza = new JTextField();
        txtRaza.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtRaza.setBounds(80, 96, 397, 23);
        label9 = new JLabel("Genero");
        label9.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label9.setBounds(16, 125, 80, 20);
        txtGenero = new JTextField();
        txtGenero.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtGenero.setBounds(81, 124, 401, 22);
        label11 = new JLabel("Peso");
        label11.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label11.setBounds(16, 155, 80, 20);
        txtPeso = new JTextField();
        txtPeso.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtPeso.setBounds(81, 153, 400, 24);
        labelDueno = new JLabel("Dueño");
        labelDueno.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelDueno.setBounds(16, 183, 80, 20);
        comboDuenos = new JComboBox<>();
        comboDuenos.setBounds(81, 180, 400, 24);
        comboDuenos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Dueño) {
                    Dueño dueño = (Dueño) value;
                    setText(dueño.getNombre() + " (ID " + dueño.getId_dueño() + ")");
                }
                return this;
            }
        });
        modeloTable13 = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Edad", "Especie", "Raza", "Genero", "Peso", "ID Dueño"}, 0
        ) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table13 = new JTable(modeloTable13);
        table13.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table13.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll_table13 = new JScrollPane(table13);
        scroll_table13.setBounds(13, 258, 471, 112);
        btn14 = new JButton("Agregar");
        btn14.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btn14.setBounds(75, 218, 90, 25);
        btn15 = new JButton("Modificar");
        btn15.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btn15.setBounds(173, 218, 90, 25);
        btn16 = new JButton("Eliminar");
        btn16.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btn16.setBounds(268, 218, 90, 25);
        btn17 = new JButton("Limpiar");
        btn17.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btn17.setBounds(363, 218, 90, 25);
        getContentPane().add(label1);
        getContentPane().add(txtNombre);
        getContentPane().add(label3);
        getContentPane().add(txtEdad);
        getContentPane().add(label5);
        getContentPane().add(Especie);
        getContentPane().add(label7);
        getContentPane().add(txtRaza);
        getContentPane().add(label9);
        getContentPane().add(txtGenero);
        getContentPane().add(label11);
        getContentPane().add(txtPeso);
        getContentPane().add(labelDueno);
        getContentPane().add(comboDuenos);
        getContentPane().add(scroll_table13);
        getContentPane().add(btn14);
        getContentPane().add(btn15);
        getContentPane().add(btn16);
        getContentPane().add(btn17);
    }

// ------------------------------------------------------------------
    // Eventos
    // ------------------------------------------------------------------
    private void registrarEventos() {
        // Al seleccionar una fila de la tabla se cargan los campos.
        table13.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarCamposDesdeFilaSeleccionada();
            }
        });

        btn14.addActionListener(e -> agregarMascota());
        btn15.addActionListener(e -> modificarMascota());
        btn16.addActionListener(e -> eliminarMascota());
        btn17.addActionListener(e -> limpiarCampos());
    }

    // ------------------------------------------------------------------
    // Operaciones
    // ------------------------------------------------------------------
    private void agregarMascota() {
        if (!camposValidos()) {
            return;
        }
        Mascota nueva = leerCampos(0);

        try {
            int id = sistema.guardar(nueva);
            if (id == -1) {
                JOptionPane.showMessageDialog(this, "No se pudo agregar la mascota.");
            } else {
                JOptionPane.showMessageDialog(this, "Mascota agregada con ID " + id);
            }
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
           throw ex;
            //mostrarErrorSql(ex);
        }
    }

    private void modificarMascota() {
        int fila = table13.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una mascota de la tabla primero.");
            return;
        }
        if (!camposValidos()) {
            return;
        }
        int id = Integer.parseInt(table13.getValueAt(fila, 0).toString());
        Mascota mascota = leerCampos(id);

        try {
            if (sistema.actualizar(mascota)) {
                JOptionPane.showMessageDialog(this, "Mascota modificada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontro la mascota con ID " + id);
            }
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
           throw ex;
            //mostrarErrorSql(ex);
        }
    }

    private void eliminarMascota() {
        int fila = table13.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una mascota de la tabla primero.");
            return;
        }
        int id = Integer.parseInt(table13.getValueAt(fila, 0).toString());

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres eliminar a la mascota con ID " + id + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (sistema.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Mascota eliminada.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontro la mascota con ID " + id);
            }
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
           throw ex;
            //mostrarErrorSql(ex);  
        }
    }

    // ------------------------------------------------------------------
    // Metodos auxiliares
    // ------------------------------------------------------------------
    private void cargarTabla() {
        modeloTable13.setRowCount(0);
        try {
            ArrayList<Mascota> mascotas = sistema.listar();
            for (Mascota m : mascotas) {
                modeloTable13.addRow(new Object[]{
                    m.getId_mascota(),
                    m.getNombre(),
                    m.getEdad(),
                    m.getEspecie(),
                    m.getRaza(),
                    m.getGenero(),
                    m.getPeso(),
                    m.getId_dueño(),
                });
            }
        } catch (Exception ex) {
            throw ex;
        }
        //catch (SQLException ex) {
            //mostrarErrorSql(ex);
        //}
    }

    private void cargarCamposDesdeFilaSeleccionada() {
        int fila = table13.getSelectedRow();
        if (fila < 0) {
            return;
        }
        txtNombre.setText(String.valueOf(table13.getValueAt(fila, 1)));
        txtEdad.setText(String.valueOf(table13.getValueAt(fila, 2)));
        Especie.setText(String.valueOf(table13.getValueAt(fila, 3)));
        txtRaza.setText(String.valueOf(table13.getValueAt(fila, 4)));
        txtGenero.setText(String.valueOf(table13.getValueAt(fila, 5)));
        txtPeso.setText(String.valueOf(table13.getValueAt(fila, 6)));
        int idDueño = Integer.parseInt(table13.getValueAt(fila, 7).toString());
        comboDuenos.setSelectedIndex(-1);
        for (int i = 0; i < comboDuenos.getItemCount(); i++) {
            Dueño dueño = comboDuenos.getItemAt(i);
            if (dueño.getId_dueño() == idDueño) {
                comboDuenos.setSelectedIndex(i);
                break;
            }
        }
    }

    private Mascota leerCampos(int id) {
        Mascota mascota = new Mascota(
                id,
                txtNombre.getText().trim(),
                txtEdad.getText().trim(),
                Especie.getText().trim(),
                txtRaza.getText().trim(),
                txtGenero.getText().trim(),
                Integer.parseInt(txtPeso.getText().trim()));
        mascota.setId_dueño(((Dueño) comboDuenos.getSelectedItem()).getId_dueño());
        return mascota;
    }

    private void cargarDuenos() {
        comboDuenos.removeAllItems();
        for (Dueño dueño : new SistemaDueño().listar()) {
            comboDuenos.addItem(dueño);
        }
        if (comboDuenos.getItemCount() > 0) {
            comboDuenos.setSelectedIndex(0);
        }
    }

    private boolean camposValidos() {
        if (comboDuenos.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Registra un dueño antes de agregar una mascota.");
            return false;
        }
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
            txtNombre.requestFocus();
            return false;
        }
        if (txtRaza.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La raza es obligatoria.");
            txtRaza.requestFocus();
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        Especie.setText("");
        txtRaza.setText("");
        txtEdad.setText("");
        txtGenero.setText("");
        txtPeso.setText("");
        if (comboDuenos.getItemCount() > 0) {
            comboDuenos.setSelectedIndex(0);
        }
        table13.clearSelection();
        txtNombre.requestFocus();
    }

    private void mostrarErrorSql(SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(
                this,
                "Error de base de datos: " + ex.getMessage()
                + "\nRevisa que MySQL este iniciado y que la base veterinaria_db exista.");
    }

    // ------------------------------------------------------------------
    // Punto de entrada de la aplicacion
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        // FlatLaf: look and feel moderno (debe aplicarse antes de crear la ventana).
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(() -> new formmascota().setVisible(true));
    }


}