/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreriaig;

/**
 *
 * @author moyme
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Agregar {
    public static void agregarLibro() {
        // Crear y mostrar la ventana de agregar libro
        JFrame agregarFrame = new JFrame("Agregar Libro");
        agregarFrame.setSize(400, 300);
        agregarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Etiquetas
        JLabel lblNombre = new JLabel("Nombre del Libro:");
        JLabel lblCosto = new JLabel("Costo del Libro:");
        JLabel lblCantidad = new JLabel("Cantidad del Libro:");
        JLabel lblEditorial = new JLabel("Editorial:");
        JLabel lblGenero = new JLabel("Género:");
        JLabel lblIdioma = new JLabel("Idioma:");

        // Componentes de la ventana
        JTextField txtNombre = new JTextField();
        JTextField txtCosto = new JTextField();
        JTextField txtCantidad = new JTextField();

        // Combo boxes para las tablas relacionadas
        JComboBox<IdNombreComboItem> comboEditorial = new JComboBox<>();
        JComboBox<IdNombreComboItem> comboGenero = new JComboBox<>();
        JComboBox<IdNombreComboItem> comboIdioma = new JComboBox<>();

        JButton btnGuardar = new JButton("Guardar");

        // Layout y ubicación de componentes
        agregarFrame.setLayout(null);

        lblNombre.setBounds(10, 10, 120, 20);
        lblCosto.setBounds(10, 40, 120, 20);
        lblCantidad.setBounds(10, 70, 120, 20);
        lblEditorial.setBounds(10, 100, 120, 20);
        lblGenero.setBounds(10, 130, 120, 20);
        lblIdioma.setBounds(10, 160, 120, 20);

                txtNombre.setBounds(140, 10, 200, 20);
        
                txtCosto.setBounds(140, 40, 200, 20);
        txtCantidad.setBounds(140, 70, 200, 20);

        comboEditorial.setBounds(140, 100, 200, 20);
        comboGenero.setBounds(140, 130, 200, 20);
        comboIdioma.setBounds(140, 160, 200, 20);

        btnGuardar.setBounds(10, 190, 100, 30);

        agregarFrame.add(lblNombre);
        agregarFrame.add(lblCosto);
        agregarFrame.add(lblCantidad);
        agregarFrame.add(lblEditorial);
        agregarFrame.add(lblGenero);
        agregarFrame.add(lblIdioma);

        agregarFrame.add(txtNombre);
        agregarFrame.add(txtCosto);
        agregarFrame.add(txtCantidad);

        agregarFrame.add(comboEditorial);
        agregarFrame.add(comboGenero);
        agregarFrame.add(comboIdioma);

        agregarFrame.add(btnGuardar);

        // Llenar combo boxes con datos de las tablas relacionadas
        fillComboBox(comboEditorial, "tbl_cat_editorial", "EditorialId", "Editorial_Nombre");
        fillComboBox(comboGenero, "tbl_cat_genero", "GeneroId", "Genero_Nombre");
        fillComboBox(comboIdioma, "tbl_cat_idioma", "IdiomaId", "Idioma_Nombre");

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para guardar libro
                guardarLibro(txtNombre, txtCosto, txtCantidad, comboGenero, comboEditorial, comboIdioma);
            }
        });

        // Hacer visible la ventana de agregar libro
        agregarFrame.setVisible(true);
    }

    private static void fillComboBox(JComboBox<IdNombreComboItem> comboBox, String tableName, String idColumnName, String nombreColumnName) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_libros", "root", "");
            String query = "SELECT " + idColumnName + ", " + nombreColumnName + " FROM " + tableName;
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            ArrayList<IdNombreComboItem> items = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(idColumnName);
                String nombre = rs.getString(nombreColumnName);
                IdNombreComboItem item = new IdNombreComboItem(id, nombre);
                items.add(item);
            }

            comboBox.setModel(new DefaultComboBoxModel<>(items.toArray(new IdNombreComboItem[0])));

            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void guardarLibro(JTextField txtNombre, JTextField txtCosto, JTextField txtCantidad,
                                     JComboBox<IdNombreComboItem> comboGenero, JComboBox<IdNombreComboItem> comboEditorial,
                                     JComboBox<IdNombreComboItem> comboIdioma) {
        try {
            // Conectar a la base de datos
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_libros", "root", "");

            // Consulta de inserción
            String query = "INSERT INTO tbl_ope_libro (Libro_Nombre, Libro_Costo, Libro_Cantidad, Libro_GeneroId, Libro_EditorialId, Libro_IdiomaId) VALUES (?, ?, ?, ?, ?, ?)";

            // Validar que los campos numéricos contengan valores válidos
            int costo;
            int cantidad;

            try {
                costo = Integer.parseInt(txtCosto.getText());
                cantidad = Integer.parseInt(txtCantidad.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos para el costo y la cantidad.");
                return; // Salir del ActionListener si hay un error
            }

            // Obtener los IDs seleccionados de los combo boxes
            int generoId = ((IdNombreComboItem) comboGenero.getSelectedItem()).getId();
            int editorialId = ((IdNombreComboItem) comboEditorial.getSelectedItem()).getId();
            int idiomaId = ((IdNombreComboItem) comboIdioma.getSelectedItem()).getId();

            // Crear el PreparedStatement
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, txtNombre.getText());
            pst.setInt(2, costo);
            pst.setInt(3, cantidad);
            pst.setInt(4, generoId);
            pst.setInt(5, editorialId);
            pst.setInt(6, idiomaId);

            // Ejecutar la actualización
            pst.executeUpdate();

            // Cerrar recursos
            pst.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Libro agregado correctamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar el libro");
        }
    }

    // Clase para representar un elemento en el JComboBox con ID y nombre
    private static class IdNombreComboItem {
        private final int id;
        private final String nombre;

        public IdNombreComboItem(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }

        public int getId() {
            return id;
        }
    }
}
