package vista;

import com.formdev.flatlaf.FlatDarkLaf;
import dominio.Dueño;
import dominio.SistemaDueño;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;



/**
 * Formulario (vista) de duenos.
 *
 * - Muestra todos los duenos en un JTable.
 * - Permite AGREGAR un dueno nuevo.
 * - Al seleccionar una fila del JTable carga al dueno en los campos.
 * - Permite MODIFICAR el dueno seleccionado.
 * - Permite ELIMINAR el dueno seleccionado.
 *
 * La vista NO habla con MySQL: todo pasa por SistemaDueño (dominio).
 */
public class formdueño extends JFrame {

    // Capa de dominio que la vista usa para cada operacion.
    private final SistemaDueño sistema = new SistemaDueño();

    // Campos del formulario.
    private final JTextField txtId = new JTextField(6);
    private final JTextField txtNombre = new JTextField(20);
    private final JTextField txtCedula = new JTextField(20);
    private final JTextField txtEmail = new JTextField(20);
    private final JTextField txtTelefono = new JTextField(20);
    private final JTextField txtDireccion = new JTextField(20);

    // Tabla.
    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Cedula", "Email", "Telefono", "Direccion"}, 0);
    private final JTable tabla = new JTable(modelo);

    private final JButton btnAgregar = new JButton("Agregar");
    private final JButton btnModificar = new JButton("Modificar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnLimpiar = new JButton("Limpiar");

    public formdueño() {
        super("Gestion de Dueños - Veterinaria");
        armarVentana();
        registrarEventos();
        cargarTabla();
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ------------------------------------------------------------------
    // Construccion de la interfaz
    // ------------------------------------------------------------------
    private void armarVentana() {
        txtId.setEditable(false);
        txtId.setHorizontalAlignment(JTextField.CENTER);

        // Raiz: deja margenes en todos los limites de la ventana
        // y separacion vertical entre formulario, tabla y botones.
        JPanel raiz = new JPanel(new BorderLayout(0, 10));
        raiz.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Panel superior: formulario con los campos.
        JPanel formulario = new JPanel(new GridLayout(6, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        formulario.add(new JLabel("ID (automatico):"));
        formulario.add(txtId);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Cedula:"));
        formulario.add(txtCedula);
        formulario.add(new JLabel("Email:"));
        formulario.add(txtEmail);
        formulario.add(new JLabel("Telefono:"));
        formulario.add(txtTelefono);
        formulario.add(new JLabel("Direccion:"));
        formulario.add(txtDireccion);

        // Tarjeta del formulario con titulo y aire abajo.
        JPanel tarjetaFormulario = new JPanel(new BorderLayout());
        tarjetaFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(" Datos del dueno "),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        tarjetaFormulario.add(formulario, BorderLayout.CENTER);

        // Panel inferior: botones con margenes.
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        botones.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        botones.add(btnAgregar);
        botones.add(btnModificar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        // Centro: la tabla con barras de desplazamiento.
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tabla);

        raiz.add(tarjetaFormulario, BorderLayout.NORTH);
        raiz.add(scroll, BorderLayout.CENTER);
        raiz.add(botones, BorderLayout.SOUTH);
        setContentPane(raiz);
    }

    // ------------------------------------------------------------------
    // Eventos
    // ------------------------------------------------------------------
    private void registrarEventos() {
        // Al seleccionar una fila de la tabla se cargan los campos.
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarCamposDesdeFilaSeleccionada();
            }
        });

        btnAgregar.addActionListener(e -> agregarDueno());
        btnModificar.addActionListener(e -> modificarDueno());
        btnEliminar.addActionListener(e -> eliminarDueno());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    // ------------------------------------------------------------------
    // Operaciones
    // ------------------------------------------------------------------
    private void agregarDueno() {
        if (!camposValidos()) {
            return;
        }
        Dueño nuevo = leerCampos(0);

        try {
            int id = sistema.guardar(nuevo);
            if (id == -1) {
                JOptionPane.showMessageDialog(this, "No se pudo agregar el dueño.");
            } else {
                JOptionPane.showMessageDialog(this, "Dueño agregado con ID " + id);
            }
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
           throw ex;
            //mostrarErrorSql(ex);
        }
    }

    private void modificarDueno() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un dueño de la tabla primero.");
            return;
        }
        if (!camposValidos()) {
            return;
        }
        int id = Integer.parseInt(txtId.getText().trim());
        Dueño dueño = leerCampos(id);

        try {
            if (sistema.actualizar(dueño)) {
                JOptionPane.showMessageDialog(this, "Dueño modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontro el dueño con ID " + id);
            }
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
           throw ex;
            //mostrarErrorSql(ex);
        }
    }

    private void eliminarDueno() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un dueno de la tabla primero.");
            return;
        }
        int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres eliminar al dueno con ID " + id + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (sistema.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Dueno eliminado.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontro el dueno con ID " + id);
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
        modelo.setRowCount(0);
        try {
            List<Dueño> dueños = sistema.listar();
            for (Dueño d : dueños) {
                modelo.addRow(new Object[]{
                    d.getId_dueño(),
                    d.getNombre(),
                    d.getCedula(),
                    d.getEmail(),
                    d.getTelefono(),
                    d.getDireccion()
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
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            return;
        }
        txtId.setText(String.valueOf(tabla.getValueAt(fila, 0)));
        txtNombre.setText(String.valueOf(tabla.getValueAt(fila, 1)));
        txtCedula.setText(String.valueOf(tabla.getValueAt(fila, 2)));
        txtEmail.setText(String.valueOf(tabla.getValueAt(fila, 3)));
        txtTelefono.setText(String.valueOf(tabla.getValueAt(fila, 4)));
        txtDireccion.setText(String.valueOf(tabla.getValueAt(fila, 5)));
    }

    private Dueño leerCampos(int id) {
        return new Dueño(
                id,
                txtNombre.getText().trim(),
                txtCedula.getText().trim(),
                txtDireccion.getText().trim(),
                txtTelefono.getText().trim(),
                txtEmail.getText().trim());
    }

    private boolean camposValidos() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
            txtNombre.requestFocus();
            return false;
        }
        if (txtCedula.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La cedula es obligatoria.");
            txtCedula.requestFocus();
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCedula.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        tabla.clearSelection();
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
        SwingUtilities.invokeLater(() -> new formdueño().setVisible(true));
    }
}