/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
    public boolean agendarCita(Timestamp fechaHora, int id_usuario_paciente, int id_usuario_medico) throws PersistenciaException {
        String consultaSQL = "CALL AGREGAR_CITA(?, ?, ?)";
        try (Connection con = this.conexion.crearConexion(); CallableStatement cb = con.prepareCall(consultaSQL)) {

            cb.setTimestamp(1, fechaHora);
            cb.setInt(2, id_usuario_paciente);
            cb.setInt(3, id_usuario_medico);

            cb.executeUpdate();  // Ya no necesitamos almacenar el valor de filas afectadas

            logger.info("Cita agendada con éxito.");
            return true;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al agendar la cita", e);
            throw new PersistenciaException("Error al agendar cita", e);
        }

    }

}
