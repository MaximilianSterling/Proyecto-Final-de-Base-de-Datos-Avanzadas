/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.proyecto01.capapersistenciaproyecto01;


import DAO.CitaDAO;
import conexion.ConexionBD;
import conexion.IConexionBD;
import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author norma
 */
public class CapaPersistenciaProyecto01 {

    public static void main(String[] args) throws SQLException {
        
        IConexionBD conexionBD = new ConexionBD();

//        try (Connection conexion = conexionBD.crearConexion();) {
//
//            if (conexion != null && !conexion.isClosed()) {
//                System.out.println("Conexión exitosa a la base de datos.");
//            } else {
//                System.out.println("No se pudo establecer la conexión.");
//            }
//        } catch (PersistenciaException e) {
//            System.err.println("Error en la conexión: " + e.getMessage());
//            e.printStackTrace();
//        }
        
        CitaDAO citaDAO = new CitaDAO(conexionBD);
        String fechaHoraStr = "2025-01-20 05:35:00"; 
        Timestamp fechaHora = Timestamp.valueOf(fechaHoraStr);
        try {
            citaDAO.agendarCita(fechaHora, 2, 5);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CapaPersistenciaProyecto01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
