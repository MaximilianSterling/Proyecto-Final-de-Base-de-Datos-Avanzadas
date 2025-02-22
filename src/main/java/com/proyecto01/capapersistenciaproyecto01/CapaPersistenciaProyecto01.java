/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.proyecto01.capapersistenciaproyecto01;


import DAO.CitaDAO;
import conexion.ConexionBD;
import conexion.IConexionBD;
import entidades.Cita;
import entidades.Direccion;
import entidades.Medico;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
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
        
        //Probando agendarCita() DAO
        Direccion direccionPaciente = new Direccion("Centro", "Obregon", "Pitagoras");
        
        Usuario usuarioPaciente = new Usuario(1,"karlacota", "karlita12");
        
        Paciente paciente = new Paciente(usuarioPaciente, "Karla", "Cota", "Hernandez", "7715289543", LocalDate.of(1990, 12, 12), "karlacotaher@example.com", direccionPaciente, new ArrayList<>());
        
        Usuario usuarioMedico = new Usuario(5,"norma", "norma123");
        
        Medico medico = new Medico(usuarioMedico, "Alicia", "Cano", "Arias", "Activo", "Cardiología", "123456", new ArrayList<>(), new ArrayList<>());
        
        Cita cita = new Cita(Timestamp.valueOf("2025-02-24 10:00:00"), "Agendada", "Cita Agendada", paciente, medico);
        
        CitaDAO citaDAO = new CitaDAO(conexionBD);
        
        try {
            boolean result = citaDAO.agendarCita(cita);
            if (result) {
                System.out.println("La cita fue agendada con éxito.");
            } else {
                System.out.println("Problema al agendar la cita.");
            }
        } catch (PersistenciaException e) {
            System.out.println("Error al agendar la cita: " + e.getMessage());
        }
    }
}
