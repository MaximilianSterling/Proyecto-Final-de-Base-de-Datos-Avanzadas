/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entidades.HorarioDisponible;
import conexion.IConexionBD;
import entidades.Cita;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author norma alicia beltran martin
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

    @Override
    public List<HorarioDisponible> obtenerHorariosDisponibles(int idMedico) throws PersistenciaException {
        List<HorarioDisponible> horariosDisponibles = new ArrayList<>();
        String consultaSQL = "SELECT VHM.id_usuario_medico, VHM.nombre_medico, "
                + "VHM.dia, "
                + "VHM.hora_disponible, "
                + "DATE_ADD(CURDATE(), INTERVAL "
                + "    CASE "
                + "        WHEN VHM.dia >= WEEKDAY(CURDATE()) + 1 THEN VHM.dia - (WEEKDAY(CURDATE()) + 1) "
                + "        ELSE 7 - ((WEEKDAY(CURDATE()) + 1) - VHM.dia) "
                + "    END "
                + "DAY) AS fecha "
                + "FROM vista_horarios_medicos VHM "
                + "LEFT JOIN CITAS CI "
                + "    ON VHM.id_usuario_medico = CI.id_usuario_medico "
                + "    AND TIME(CI.fecha_hora) = VHM.hora_disponible "
                + "    AND VHM.dia = DAYOFWEEK(CI.fecha_hora) "
                + "    AND CI.estado != 'Cancelada' "
                + "WHERE VHM.id_usuario_medico = ? "
                + "    AND CI.id_cita IS NULL "
                + "ORDER BY VHM.dia, VHM.hora_disponible";

        try (Connection con = this.conexion.crearConexion(); 
                PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, idMedico);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Date fecha = rs.getDate("fecha");
                    String hora_disponible = rs.getString("hora_disponible");

                    HorarioDisponible horario = new HorarioDisponible((java.sql.Date) fecha, hora_disponible);
                    horariosDisponibles.add(horario);
                }
            }

            return horariosDisponibles;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener los horarios disponibles", e);
            throw new PersistenciaException("Error al obtener horarios disponibles", e);
        }
    }

}
