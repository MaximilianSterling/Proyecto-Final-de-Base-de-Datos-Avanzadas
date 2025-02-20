/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.CitaDTO;
import entidades.Cita;
import java.time.LocalDateTime;

/**
 *
 * @author Maximiliano
 */
public class CitaMapper 
{
    /**
     * Convierte un ActivistaNuevoDTO a una entidad Activista
     *
     * @param cita activistaNuevo que se quiere convertir
     * @return cita entidad
     */
    public Cita toEntity(CitaDTO cita) {
        if (cita == null) {
            return null;
        }
        return new Cita(
                cita.getId_cita(),
                cita.getFecha_hora(),
                cita.getEstado(),
                cita.getTipo(),
                cita.getPaciente(),
                cita.getMedico()
        );
    }

    /**
     * Convierte una entidad Activista a un ActivistaNuevoDTO
     *
     * @param activista activista entiedad que se quiere convertir
     * @return activistaNuevo
     */
    public ActivistaNuevoDTO toNuevoDTO(Activista activista) {
        if (activista == null) {
            return null;
        }
        return new ActivistaNuevoDTO(
                activista.getNombre(),
                activista.getApellidoPaterno(),
                activista.getApellidoMaterno(),
                activista.getTelefono(),
                activista.getFechaInicio()
        );
    }
}
