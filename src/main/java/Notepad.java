/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Created with IntelliJ IDEA.
 * User: victorelci
 * Date: 10/2/13
 * Time: 8:32 AM
 * A simple Notepad GUI application
 */
public class Notepad extends JFrame {

    /**
     * Holds the name of the file
     * See {@api http://docs.oracle.com/javase/7/docs/api/javax/swing/JTextField.html}
     */
    private JTextField fileNameTextField;

    /**
     * Holds the text of the file
     * See {@api http://docs.oracle.com/javase/7/docs/api/javax/swing/JTextArea.html}
     */
    private JTextArea textArea;

    /**
     * Our application's buttons
     * See {@api http://docs.oracle.com/javase/7/docs/api/javax/swing/JButton.html}
     */
    private JButton saveButton, loadButton, clearButton;

    /**
     * default constructor
     */
    public Notepad() {

        fileNameTextField = new JTextField();
        textArea = new JTextArea();

        /**
         * Create a panel for the buttons to reside.
         * The default layout for containers is FlowLayout
         *
         * See {@link http://docs.oracle.com/javase/7/docs/api/java/awt/FlowLayout.html}
         */
        JPanel buttonPanel = new JPanel();

        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        clearButton = new JButton("Clear");

        // Instantiate an action listener to listen for button click events
        NotepadButtonListener buttonListener = new NotepadButtonListener();

        // attach our action listener to the buttons
        saveButton.addActionListener(buttonListener);
        loadButton.addActionListener(buttonListener);
        clearButton.addActionListener(buttonListener);

        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(clearButton);

        // set the layout of the JFrame
        this.setLayout(new BorderLayout());

        /**
         * Add the elements to the panel.
         * BorderLayouts have 5 positions that you can add Objects:
         * NORTH, SOUTH, EAST, WEST, and CENTER
         *
         * See {@api http://docs.oracle.com/javase/7/docs/api/java/awt/BorderLayout.html}
         */

        add(fileNameTextField, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // show the frame
        setPreferredSize(new Dimension(400, 300));
        pack();
        setVisible(true);
    }

    /**
     *
     * @return the name of the file
     */
    private String getFileName() {
        return fileNameTextField.getText();
    }

    /**
     * Read from the specified file
     * @param fileName the name of the file to read from
     */
    private void readFile(String fileName) {
        Scanner inFile = null;

        try {
            // file reader
            inFile = new Scanner(new FileReader(fileName));

            // clear the text area
            textArea.setText("");

            // copy file
            while (inFile.hasNextLine()) {
                textArea.append(inFile.nextLine());
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("File not found");
        } finally {
            if (inFile != null) {
                inFile.close();
            }
        }
    }

    /**
     * Write to the specified file
     * @param fileName the name of the file to write to
     */
    private void writeFile(String fileName) {
        PrintWriter outFile = null;

        try {
            // file writer
            outFile = new PrintWriter(new FileWriter(fileName));

            outFile.print(textArea.getText());

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("File not found");
        } finally {
            if (outFile != null) {
                outFile.close();
            }
        }
    }

    /**
     * The action listener for our Notepad application
     * Action listeners must implement the ActionListener interface and define
     * the behavior of the actionPerformed() method.
     *
     * See {@api http://docs.oracle.com/javase/7/docs/api/java/awt/event/ActionListener.html}
     *
     * More info on action listeners {@api http://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
     */
    class NotepadButtonListener implements ActionListener {

        /**
         * This needs to be defined since this class implements the ActionListener interface
         * @param e the event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();

            // Figure out which button was pressed            
            if (sourceButton.equals(loadButton)) {
                System.out.println("Load button pressed");
                readFile(getFileName());
            } else if (sourceButton.equals(saveButton)) {
                System.out.println("Save button pressed");
                writeFile(getFileName());
            } else if (sourceButton.equals(clearButton)) {
                System.out.println("Clear button pressed");

                // clear the text area
                textArea.setText("");
            } else {
                System.out.println("Unknown button pressed");
            }
        }
    }

    /**
     * The application's entry point
     * @param args
     */
    public static void main(String[] args) {
        // Create the notepad instance
        new Notepad();
    }
}
