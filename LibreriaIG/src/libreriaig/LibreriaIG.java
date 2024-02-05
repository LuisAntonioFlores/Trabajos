/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package libreriaig;

/**
 *
 * @author moyme
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibreriaIG extends JFrame {

    public LibreriaIG() {
        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnConsultar = new JButton("Consultar");
        JButton btnVender = new JButton("Vender");
        JButton btnSalir = new JButton("Salir");

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agregar.agregarLibro();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón Modificar
                Modificar.mostrarVentanaModificar();
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón Consultar
                Consultar.mostrarVentanaConsulta();
            }
        });

        btnVender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón Vender
                JOptionPane.showMessageDialog(null, "Vender libro");
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        getContentPane().setLayout(new java.awt.FlowLayout());
        getContentPane().add(btnAgregar);
        getContentPane().add(btnModificar);
        getContentPane().add(btnConsultar);
        getContentPane().add(btnVender);
        getContentPane().add(btnSalir);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibreriaIG ventanaPrincipal = new LibreriaIG();
            ventanaPrincipal.setSize(400, 300);
            ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaPrincipal.setVisible(true);
        });
    }
}
