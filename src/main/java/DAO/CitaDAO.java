/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;
import entidades.Cita;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author norma
 */
public class CitaDAO implements ICitaDAO {

    IConexionBD conexion;

    public CitaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    private static final Logger logger = Logger.getLogger(CitaDAO.class.getName());

    @Override
    public boolean agendarCita(Cita cita) throws PersistenciaException {
        String consultaSQL = "CALL AGREGAR_CITA(?, ?, ?)";
        try (Connection con = this.conexion.crearConexion(); CallableStatement cb = con.prepareCall(consultaSQL)) {

            cb.setTimestamp(1, cita.getFecha_hora());
            cb.setInt(2, cita.getPaciente().getUsuario().getId_usuario()); 
            cb.setInt(3, cita.getMedico().getUsuario().getId_usuario());   

            cb.executeUpdate();

            logger.info("Cita agendada con éxito.");
            return true;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al agendar la cita", e);
            throw new PersistenciaException("Error al agendar cita", e);
        }
    }

}
