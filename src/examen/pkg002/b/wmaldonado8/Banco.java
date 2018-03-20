/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.pkg002.b.wmaldonado8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge Pucha
 */
public class Banco {

    static ArrayList<Cuenta> listaCuenta = new ArrayList<Cuenta>();
    static String archivo = "";

    public static void agregar(Cuenta cuenta) {
        listaCuenta.add(cuenta);
    }
     public static void seleccionar() {
        javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
        String ruta = "";
        try {
            if (jF1.showSaveDialog(null) == jF1.APPROVE_OPTION) {
                archivo = jF1.getSelectedFile().getAbsolutePath();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void leer() throws IOException {
        String cadena;
        Banco.seleccionar();
        FileReader f = new FileReader(new File(archivo));
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            String l[] = cadena.split(";");
            if (l[1].equalsIgnoreCase("CuentaAhorros")) {
                CuentaAhorro c;
                double lb = Double.parseDouble(l[2]);
                double li = Double.parseDouble(l[3]);
                c = new CuentaAhorro(l[0], l[1], lb, li);
                Banco.agregar(c);
            }
            if (l[1].equalsIgnoreCase("CuentaHipoteca")) {
                CuentaHipoteca cH;
                double lb = Double.parseDouble(l[2]);
                double li = Double.parseDouble(l[3]);
                cH = new CuentaHipoteca(l[0], l[1], lb, li);
                Banco.agregar(cH);
            }
            if (l[1].equalsIgnoreCase("CuentaPrestamo")) {
                CuentaHipoteca cP;
                double lb = Double.parseDouble(l[2]);
                double li = Double.parseDouble(l[3]);
                cP = new CuentaHipoteca(l[0], l[1], lb, li);
                Banco.agregar(cP);
            }
        }
        b.close();
    }

    public static void presentar() {

        String linea = "";
        for (Cuenta cuenta : listaCuenta) {
            if (cuenta instanceof CuentaAhorro) {
                linea += "Cuenta Ahorro" + ";" + cuenta.toString();
            }
            if (cuenta instanceof CuentaHipoteca) {
                linea += "Cuenta Hipoteca" + ";" + cuenta.toString();
            }
            if (cuenta instanceof CuentaPrestamo) {
                linea += "Cuenta Prestamo" + ";" + cuenta.toString();
            }
        }
        JOptionPane.showMessageDialog(null, linea);
    }

    public static void grabar() {
        PrintWriter pw = null;
        try {
	    // Examen002: La ruta y el nombre del 'archivo' debe ser 
	    // establecido dinamicamente por el usuario en el lugar adecuado
            FileWriter fw = new FileWriter(archivo, true);
            pw = new PrintWriter(fw);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (Cuenta cuenta : listaCuenta) {
            String linea = "";
            if (cuenta instanceof CuentaAhorro) {
                linea="Cuenta Ahorro"+";"+cuenta.toString();
            }if (cuenta instanceof CuentaHipoteca) {
                linea="Cuenta Hipoteca"+";"+cuenta.toString();
            }if (cuenta instanceof CuentaPrestamo) {
                linea="Cuenta Prestamo"+";"+cuenta.toString();
            }
            pw.println(linea);
        }
        pw.close();
    }
    
    public static Cuenta buscarCuentaAhorro() {
        String nombre = (JOptionPane.showInputDialog(null, "Ingrese nombre", "Verificar Cuenta", JOptionPane.INFORMATION_MESSAGE));
        CuentaAhorro cuentaAH = new CuentaAhorro(nombre);
        Cuenta c=(Cuenta)cuentaAH;
        if (listaCuenta.contains(c)) {
            return listaCuenta.get(listaCuenta.indexOf(c));
        }
        return null;
    }
    public static Cuenta buscarCuentaHipoteca() {
        String nombre = (JOptionPane.showInputDialog(null, "Ingrese nombre", "Verificar Cuenta", JOptionPane.INFORMATION_MESSAGE));
        CuentaHipoteca cuentaAH = new CuentaHipoteca(nombre);
        Cuenta c=(Cuenta)cuentaAH;
        if (listaCuenta.contains(c)) {
            return listaCuenta.get(listaCuenta.indexOf(c));
        }
        return null;
    }
    

    public static void leerCuentas(){
	/* 
	 * Lee los datos desde un archivo de texto, crea objetos 'Cuenta'
	 * y los almacena en la lista 'listaCuenta'
	 */
	// Examen 002: Completar este metodo

    }
}
