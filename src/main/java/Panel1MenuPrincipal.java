
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Esther Ferreiro
 */
public class Panel1MenuPrincipal extends JPanel {
    
    public Panel1MenuPrincipal(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JButton("Escribir fichero"));
        add(new JButton("Leer fichero"));
        add(new JButton("Cerrar aplicación"));
        setBounds(1, 1, 200, 100);        
    }
    
    
}
