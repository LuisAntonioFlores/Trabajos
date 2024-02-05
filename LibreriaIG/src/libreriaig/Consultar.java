/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreriaig;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Consultar {

    public static void mostrarVentanaConsulta() {
        JFrame consultaFrame = new JFrame("Consulta de Libros");
        consultaFrame.setSize(800, 600);
        consultaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Obtener datos de la base de datos
        Vector<Vector<String>> data = getDataFromDatabase();
        // Nombres de las columnas
        Vector<String> columnNames = getColumnNames();
        // Crear y configurar la tabla
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(780, 550));

        // Agregar la tabla al marco
        consultaFrame.getContentPane().setLayout(new BorderLayout());
        consultaFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Hacer visible la ventana de consulta
        consultaFrame.setVisible(true);
    }

    private static Vector<Vector<String>> getDataFromDatabase() {
        Vector<Vector<String>> data = new Vector<>();

        try {
            // Conectar a la base de datos
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_libros", "root", "");

            // Consulta para obtener todos los datos de tbl_ope_libro con joins
            String query = "SELECT lib.LibroId AS Id, lib.Libro_Nombre AS Nombre, lib.Libro_Costo AS Costo, lib.Libro_Cantidad AS Cantidad, gen.Genero_Nombre AS Genero, edi.Editorial_Nombre AS Editorial, idi.Idioma_Nombre AS Idioma " +
                    "FROM tbl_ope_libro lib " +
                    "INNER JOIN tbl_cat_editorial edi ON lib.Libro_EditorialId = edi.EditorialId " +
                    "INNER JOIN tbl_cat_genero gen ON lib.Libro_GeneroId = gen.GeneroId " +
                    "INNER JOIN tbl_cat_idioma idi ON lib.Libro_IdiomaId = idi.IdiomaId";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            // Recorrer los resultados y agregarlos a la lista de datos
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("Id"));
                row.add(rs.getString("Nombre"));
                row.add(rs.getString("Costo"));
                row.add(rs.getString("Cantidad"));
                row.add(rs.getString("Genero"));
                row.add(rs.getString("Editorial"));
                row.add(rs.getString("Idioma"));
                data.add(row);
            }

            // Cerrar recursos
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos");
        }

        return data;
    }

    private static Vector<String> getColumnNames() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Id");
        columnNames.add("Nombre");
        columnNames.add("Costo");
        columnNames.add("Cantidad");
        columnNames.add("Genero");
        columnNames.add("Editorial");
        columnNames.add("Idioma");
        return columnNames;
    }
}
