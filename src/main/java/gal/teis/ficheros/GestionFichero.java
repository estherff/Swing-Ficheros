/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 *
 * @author Esther Ferreiro
 */
public class GestionFichero {
    
    public static void guardar_Fichero(String nombreFichero, String texto) throws IOException {
         //Se usa try con recursos para no tener que cerrar los recursos
        try (BufferedWriter salida = new BufferedWriter(new FileWriter(new File(nombreFichero), Charset.forName("UTF-8"), false))) {
            //Escribe el texto en el fichero de salida
            salida.write(texto);
            //Para garantizar que el buffer vacía todo en el fichero
            salida.flush();
        }
    }
    
    public static String leer_Fichero(String nombreFichero) throws IOException {
        int dato;
        StringBuilder contenidoFichero = new StringBuilder();
        //Creo un FileReader a partir de un fichero de texto y codifico cada carazter con UTF-8
        try (BufferedReader mBR = new BufferedReader(new FileReader(new File(nombreFichero), Charset.forName("UTF-8")))) {
            //Voy leyendo el flujo de entrada de datos y guardo cada lectura en bufI
            String linea = mBR.readLine();
            while (linea != null) {
                contenidoFichero.append(linea+"\n");
                linea = mBR.readLine();    
            }
        }
        return contenidoFichero.toString();
    }
}
