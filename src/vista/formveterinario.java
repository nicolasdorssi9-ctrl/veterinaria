package vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.List;
import com.formdev.flatlaf.FlatDarkLaf;
import dominio.SistemaVeterinario;
import dominio.Veterinario;


public class formveterinario extends JFrame {
    private final SistemaVeterinario sistema = new SistemaVeterinario();

    private JLabel labelnombre;
    private JTextField txtnombre;
    private JLabel labelpassword;
    private JTextField txtpassword;
    private JLabel labelespecialidad;
    private JTextField txtespecialidad;
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

    public formveterinario() {
    
        setTitle("Formulario de Veterinarios");
        setSize(500, 410);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        initComponents();
        registrarEventos();
        cargarTabla();

    }

    private void initComponents() {
        labelnombre = new JLabel("Nombre");
        labelnombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelnombre.setBounds(17, 7, 80, 20);
        txtnombre = new JTextField();
        txtnombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtnombre.setBounds(100, 6, 380, 22);
        labelpassword = new JLabel("Password");
        labelpassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelpassword.setBounds(17, 37, 80, 20);
        txtpassword = new JTextField();
        txtpassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtpassword.setBounds(100, 36, 380, 22);
        labelespecialidad = new JLabel("Especialidad");
        labelespecialidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelespecialidad.setBounds(17, 67, 80, 20);
        txtespecialidad = new JTextField();
        txtespecialidad.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtespecialidad.setBounds(100, 66, 380, 22);
       
        modeloTable12 = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Password", "Especialidad"}, 0
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
        getContentPane().add(labelnombre);
        getContentPane().add(txtnombre);
        getContentPane().add(labelpassword);
        getContentPane().add(txtpassword);
        getContentPane().add(labelespecialidad);
        getContentPane().add(txtespecialidad);
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

        btn13.addActionListener(e -> agregarVeterinario());
        btn14.addActionListener(e -> modificarVeterinario());
        btn15.addActionListener(e -> eliminarVeterinario());
        btn16.addActionListener(e -> limpiarCampos());
    }

    // ------------------------------------------------------------------
    // Operaciones
    // ------------------------------------------------------------------
    private void agregarVeterinario() {
        if (!camposValidos()) {
            return;
        }
        Veterinario nuevo = leerCampos(0);
        int id = sistema.guardar(nuevo);
        if (id == -1) {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el veterinario.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Veterinario agregado con ID " + id);
        cargarTabla();
        limpiarCampos();
    }

    private void modificarVeterinario() {
        int fila = table12.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un veterinario de la tabla primero.");
            return;
        }
        if (!camposValidos()) {
            return;
        }
        int id = Integer.parseInt(table12.getValueAt(fila, 0).toString());
        Veterinario veterinario = leerCampos(id);

        if (sistema.actualizar(veterinario)) {
            JOptionPane.showMessageDialog(this, "Veterinario modificado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontro el veterinario con ID " + id);
        }
        cargarTabla();
        limpiarCampos();
    }

    private void eliminarVeterinario() {
        int fila = table12.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un veterinario de la tabla primero.");
            return;
        }
        int id = Integer.parseInt(table12.getValueAt(fila, 0).toString());

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres eliminar al veterinario con ID " + id + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        if (sistema.eliminar(id)) {
            JOptionPane.showMessageDialog(this, "Veterinario eliminado.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontro el veterinario con ID " + id);
        }
        cargarTabla();
        limpiarCampos();
    }

    // ------------------------------------------------------------------
    // Metodos auxiliares
    // ------------------------------------------------------------------
    private void cargarTabla() {
        modeloTable12.setRowCount(0);
        List<Veterinario> veterinarios = sistema.listar();
        for (Veterinario veterinario : veterinarios) {
            modeloTable12.addRow(new Object[]{
                veterinario.getId_veterinario(),
                veterinario.getNombre(),
                veterinario.getPassword(),
                veterinario.getEspecialidad()
            });
        }
    }

    private void cargarCamposDesdeFilaSeleccionada() {
        int fila = table12.getSelectedRow();
        if (fila < 0) {
            return;
        }
        txtnombre.setText(String.valueOf(table12.getValueAt(fila, 1)));
        txtpassword.setText(String.valueOf(table12.getValueAt(fila, 2)));
        txtespecialidad.setText(String.valueOf(table12.getValueAt(fila, 3)));
    }

    private Veterinario leerCampos(int id) {
        return new Veterinario(
                id,
                txtnombre.getText().trim(),
                txtpassword.getText().trim(),
                txtespecialidad.getText().trim());
    }

    private boolean camposValidos() {
        if (txtnombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
            txtnombre.requestFocus();
            return false;
        }
        if (txtpassword.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La contraseña es obligatoria.");
            txtpassword.requestFocus();
            return false;
        }
        if (txtespecialidad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La especialidad es obligatoria.");
            txtespecialidad.requestFocus();
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtnombre.setText("");
        txtpassword.setText("");
        txtespecialidad.setText("");
        table12.clearSelection();
        txtnombre.requestFocus();
    }

    // ------------------------------------------------------------------
    // Punto de entrada de la aplicacion
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(() -> new formveterinario().setVisible(true));
    }




    





















































}
