/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.swing.leerfichero;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setSize(550, 400);
        setLocationRelativeTo(null);
        setTitle("Trabajo con ficheros");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.GRAY);

        add(new Contenido());
        
        setVisible(true);
    }

}
