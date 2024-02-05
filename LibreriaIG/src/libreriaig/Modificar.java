/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreriaig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Modificar {

    public static void mostrarVentanaModificar() {
        JFrame modificarFrame = new JFrame("Modificar Libro");
        modificarFrame.setSize(400, 300);
        modificarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblIdLibro = new JLabel("ID del Libro a modificar:");
        JLabel lblNuevoNombre = new JLabel("Nuevo Nombre:");
        JLabel lblNuevoGenero = new JLabel("Nuevo Género:");
        JLabel lblNuevoCosto = new JLabel("Nuevo Costo:");

        // Campos de texto
        JTextField txtIdLibro = new JTextField();
        JTextField txtNuevoNombre = new JTextField();
        JTextField txtNuevoCosto = new JTextField();

        // Combo box para géneros
        JComboBox<IdNombreComboItem> comboGenero = new JComboBox<>();

        // Botón para realizar la modificación
        JButton btnModificar = new JButton("Modificar");

        // Diseño de la ventana
        modificarFrame.setLayout(null);

        lblIdLibro.setBounds(10, 10, 150, 20);
        lblNuevoNombre.setBounds(10, 40, 150, 20);
        lblNuevoGenero.setBounds(10, 70, 150, 20);
        lblNuevoCosto.setBounds(10, 100, 150, 20);

        txtIdLibro.setBounds(170, 10, 200, 20);
        txtNuevoNombre.setBounds(170, 40, 200, 20);
        txtNuevoCosto.setBounds(170, 100, 200, 20);

        comboGenero.setBounds(170, 70, 200, 20);

        btnModificar.setBounds(10, 130, 100, 30);

        modificarFrame.add(lblIdLibro);
        modificarFrame.add(lblNuevoNombre);
        modificarFrame.add(lblNuevoGenero);
        modificarFrame.add(lblNuevoCosto);

        modificarFrame.add(txtIdLibro);
        modificarFrame.add(txtNuevoNombre);
        modificarFrame.add(txtNuevoCosto);
        modificarFrame.add(comboGenero);

        modificarFrame.add(btnModificar);

        // Llenar combo box con datos de las tablas relacionadas
        fillComboBox(comboGenero, "tbl_cat_genero", "GeneroId", "Genero_Nombre");

        // Acción del botón modificar
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarLibro(
                        txtIdLibro.getText(),
                        txtNuevoNombre.getText(),
                        ((IdNombreComboItem) comboGenero.getSelectedItem()).getId(),
                        txtNuevoCosto.getText()
                );
            }
        });

        // Hacer visible la ventana de modificar libro
        modificarFrame.setVisible(true);
    }

    private static void modificarLibro(String idLibro, String nuevoNombre, int nuevoGeneroId, String nuevoCosto) {
        try {
            // Conectar a la base de datos
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_libros", "root", "");

            // Consulta de actualización
            String query = "UPDATE tbl_ope_libro SET Libro_Nombre=?, Libro_GeneroId=?, Libro_Costo=? WHERE LibroId=?";

            // Validar que el ID del libro sea un número válido
            int libroId;
            try {
                libroId = Integer.parseInt(idLibro);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID de libro válido.");
                return;
            }

            // Validar que el costo sea un número válido
            double costo;
            try {
                costo = Double.parseDouble(nuevoCosto);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un costo válido.");
                return;
            }

            // Crear el PreparedStatement
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, nuevoNombre);
            pst.setInt(2, nuevoGeneroId);
            pst.setDouble(3, costo);
            pst.setInt(4, libroId);

            // Ejecutar la actualización
            int filasAfectadas = pst.executeUpdate();

            // Cerrar recursos
            pst.close();
            con.close();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Libro modificado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un libro con el ID proporcionado");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al modificar el libro");
        }
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
