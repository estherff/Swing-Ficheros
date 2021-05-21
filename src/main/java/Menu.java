/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Esther Ferreiro
 */
public class Menu extends JPanel{
    public Menu(){
        crearMenu();
    }
    
    private void crearMenu(){

        //Colocar barra en la parte superior de la ventana y centrada
        JMenuBar elMenuBar = new JMenuBar();

        //Creamos las opciones del menú
        JMenu opArchivo = new JMenu("Archivo");
        JMenu opEditar = new JMenu("Editar");
        JMenu opInformacion = new JMenu("Información");
        JMenu opCerrar = new JMenu("Salir");

        //Agreagar los elementos de menú a la barra de menú
        elMenuBar.add(opArchivo);
        elMenuBar.add(opEditar);
        elMenuBar.add(opInformacion);
        elMenuBar.add(opCerrar);

        //Agregar la barra de menú al panel
        add(elMenuBar);

        //Agrego las opciones que colgarán del menú Archivo
        JMenuItem opArchivoCrear = new JMenuItem("Nuevo");
        JMenuItem opArchivoGuradar = new JMenuItem("Guardar");
        JMenuItem opArchivoGuradarComo = new JMenuItem("Guardar comom");
        opArchivo.add(opArchivoCrear);
        opArchivo.add(opArchivoGuradar);
        opArchivo.add(opArchivoGuradarComo);

        //Agrego las opciones que colgarán del menú Editar
        JMenuItem opEdicionModificar = new JMenuItem("Modificar");
        JMenuItem opEdicionAgregarFinal = new JMenuItem("Agregar al final");
        opEditar.add(opEdicionModificar);
        opEditar.add(opEdicionAgregarFinal);
        
        //Agrego las opciones que colgarán del menú Información
         JMenuItem opEditarPalabras = new JMenuItem("Ver nº de palabras");
         JMenuItem opEditarCaracteres = new JMenuItem("Ver nº de caracteres");
         JMenuItem opEditarVocales = new JMenuItem("Ver nº de vocales");
         opInformacion.add(opEditarPalabras);
         opInformacion.add(opEditarCaracteres);
         opInformacion.add(opEditarVocales);
    }
    
}
