/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.swing.leerfichero;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import gal.teis.ficheros.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;


/**
 *
 * @author Esther Ferreiro
 */
public class Contenido extends JPanel {

    //Colocar barra en la parte superior de la ventana y centrada
    JMenuBar elMenuBar = new JMenuBar();
    JTextPane elTextoCentral = new JTextPane();
    JMenuItem opArchivoCrear = new JMenuItem("Nuevo");
    JMenuItem opArchivoGuardar = new JMenuItem("Guardar");
    JMenuItem opArchivoGuardarComo = new JMenuItem("Guardar como");
    JMenuItem opArchivoAbrir = new JMenuItem("Abrir");

    JMenuItem opInfoPalabras = new JMenuItem("Ver nº de palabras");
    JMenuItem opInfoCaracteres = new JMenuItem("Ver nº de caracteres");
    JMenuItem opInfoVocales = new JMenuItem("Ver nº de vocales");

    //Crear un JPanel para el menú
    JPanel barraMenu = new JPanel();

    JFileChooser fileChooser;

    Boolean activarGuardar = false;

    //Para detectar si se cambió algo en el JPanel para activar el botón de Guardar
    String textoJPanel = null;

    //Titulo de la ventana cuando el fichero aún no ha sido guardado
    String tituloNoGuardado = "Archivo nuevo";

    //Nombre de fichero actual
    String filename = "";

    public Contenido() {
        //Determino el tipo de Layout
        setLayout(new BorderLayout());
        crearMenu();

        //Agreagar el menú al JPanel
        barraMenu.add(elMenuBar);
        
        //Posicionar el JPanel de la barra de menú en el JPanel Contenido
        add(barraMenu, BorderLayout.NORTH);

        //Cear el panel para el contenido
        add(elTextoCentral, BorderLayout.CENTER);
        elTextoCentral.setVisible(false);

    }

    private void crearMenu() {

        //Creamos las opciones del menú
        JMenu opArchivo = new JMenu("Archivo");
        JMenu opEditar = new JMenu("Editar");
        JMenu opInformacion = new JMenu("Información");
        //Defino un JMenuItem porque un JMenu no permite agregar un detector de eventos 
        JMenuItem opCerrar = new JMenuItem("Salir");

        //Agreagar los elementos de menú a la barra de menú
        elMenuBar.add(opArchivo);
        elMenuBar.add(opEditar);
        elMenuBar.add(opInformacion);
        elMenuBar.add(opCerrar);


        //Desactivo guardar y guardar como para que solo estén activadas cuando 
        //haya un archivo abierto y se den otras opciones
        desactivarOpcionesIniciales();

        //Agrego las opciones que colgarán del menú Archivo
        opArchivo.add(opArchivoCrear);
        opArchivo.add(opArchivoGuardar);
        opArchivo.add(opArchivoGuardarComo);
        opArchivo.add(opArchivoAbrir);
     
        //Agrego las opciones que colgarán del menú Información
        opInformacion.add(opInfoPalabras);
        opInformacion.add(opInfoCaracteres);
        opInformacion.add(opInfoVocales);

        opCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        opArchivoCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conFicheroAbierto();
            }
        });

        opArchivoGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //https://docs.oracle.com/javase/7/docs/api/javax/swing/JFileChooser.html   
                fileChooser = new JFileChooser();
                if (elTextoCentral.isVisible() && obtenerTituloVentana().equals(tituloNoGuardado)) {
                    //Se muestra la ventana de elegir ruta y dar nombre de fichero para guardar
                    guardarFichero();
                } else if (elTextoCentral.isVisible() && !obtenerTituloVentana().equals(tituloNoGuardado)) {
                    try {
                        GestionFichero.guardar_Fichero(obtenerTituloVentana(), elTextoCentral.getText());
                        //Desactivo el botón guardar  pues se acaba de guardar
                        desactivarGuardar();
                    } catch (IOException ex) {
                        //Muestra un mensaje de error si no se puede almacenar elfichero
                        //Se puede probar sobre una carpeta de solo lectura o una unidad de DVD que no tenga dispositivo
                        JOptionPane.showMessageDialog(fileChooser, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //Para detectar si pulsa una tecla en un fichero que ya se guardó para activar el botón guardar.
        elTextoCentral.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!activarGuardar) {
                    //Al pulsar se activa el botón guardar
                    activarGuardar();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        opArchivoGuardarComo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {             
                    //Se muestra la ventana de elegir ruta y dar nombre de fichero para guardar
                    guardarFichero();
            }
        }
        );

        opArchivoAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
//                Mostrar la ventana para abrir archivo y recoger la respuesta
//                En el parámetro del showOpenDialog se indica la ventana al que estará asociado.Con el valor this se asocia a la
//                ventana que la abre
                int respuesta = fileChooser.showOpenDialog(Contenido.this);
                //Comprobar si se ha pulsado Aceptar
                if (respuesta == JFileChooser.APPROVE_OPTION) {
                    //Crear un objeto File con el archivo elegido
                    File archivoElegido = fileChooser.getSelectedFile();
                    //Mostrar el nombre del archvivo en la barra de título
                    cambiarTituloVentana(archivoElegido.getPath());
                    conFicheroAbierto();
                    try {

                        elTextoCentral.setText(GestionFichero.leer_Fichero(archivoElegido.getPath()));
                        desactivarGuardar();
                        cambiarTituloVentana(archivoElegido.getPath());
                    } catch (IOException ex) {
                        //Muestra un mensaje de error si no se puede almacenar elfichero
                        //Se puede probar sobre una carpeta de solo lectura o una unidad de DVD que no tenga dispositivo
                        JOptionPane.showMessageDialog(fileChooser, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        
        opInfoPalabras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }

    private void cambiarTituloVentana(String titulo) {
        ((JFrame) SwingUtilities.getRoot(Contenido.this)).setTitle(titulo);
    }

    private String obtenerTituloVentana() {
        return ((JFrame) SwingUtilities.getRoot(Contenido.this)).getTitle();
    }

    private void desactivarGuardar() {
        //Desactivo el botón guardar  pues se acaba de guardar
        //Se activará al modificar algo
        opArchivoGuardar.setEnabled(false);
        //Para volverlo a activar cuando se escriba algo (KeyPressed)
        activarGuardar = false;
    }

    private void activarGuardar() {
        opArchivoGuardar.setEnabled(true);
        activarGuardar = true;
    }
    
    private void desactivarOpcionesIniciales(){
        //Desactivo guardar y guardar como para que solo estén activadas cuando 
        //haya un archivo abierto y se den otras opciones
        opArchivoGuardar.setEnabled(false);
        opArchivoGuardarComo.setEnabled(false);
        opInfoPalabras.setEnabled(false);
        opInfoCaracteres.setEnabled(false);
        opInfoVocales.setEnabled(false);
    }
    

    private void guardarFichero() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheros de texto", "txt");
        fileChooser.setFileFilter(filter);
        int option = fileChooser.showSaveDialog(Contenido.this);

        if (option == JFileChooser.APPROVE_OPTION) {
            filename = fileChooser.getSelectedFile().getPath();
            try {
                GestionFichero.guardar_Fichero(filename, elTextoCentral.getText());
                //Pongo el nombre del fichero en la barra de título de 
                cambiarTituloVentana(filename);
                //Desactivo el botón guardar  pues se acaba de guardar
                desactivarGuardar();
            } catch (IOException ex) {
                //Muestra un mensaje de error si no se puede almacenar elfichero
                //Se puede probar sobre una carpeta de solo lectura o una unidad de DVD que no tenga dispositivo
                JOptionPane.showMessageDialog(fileChooser, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    private void conFicheroAbierto() {
        elTextoCentral.setVisible(true);
        opArchivoGuardar.setEnabled(true);
        opArchivoGuardarComo.setEnabled(true);
        opInfoPalabras.setEnabled(true);
        opInfoCaracteres.setEnabled(true);
        opInfoVocales.setEnabled(true);
        
        //Cambiar el título de la ventana JFrame que contiene este JPanel
        cambiarTituloVentana(tituloNoGuardado);
    }

}
