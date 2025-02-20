<<<<<<< HEAD
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
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.CitaDTO;
import entidades.Cita;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public class CitaMapper {

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

    public CitaDTO toDTO(Cita cita) {
        if (cita == null) {
            return null;
        }
        return new CitaDTO(
                cita.getId_cita(),
                cita.getFecha_hora(),
                cita.getEstado(),
                cita.getTipo(),
                cita.getPaciente(),
                cita.getMedico()
        );
    }

    public List<CitaDTO> toDTOList(List<Cita> citas) {
        if (citas == null || citas.isEmpty()) {
            return new ArrayList<>();
        }
        List<CitaDTO> citasDTO = new ArrayList<>();
        for (Cita cita : citas) {
            citasDTO.add(toDTO(cita));
        }
        return citasDTO;
    }

    public List<Cita> toEntityList(List<CitaDTO> citasDTO) {
        if (citasDTO == null || citasDTO.isEmpty()) {
            return new ArrayList<>();
        }
        List<Cita> citas = new ArrayList<>();
        for (CitaDTO citaDTO : citasDTO) {
            citas.add(toEntity(citaDTO));
        }
        return citas;
    }
}
>>>>>>> 905dc38db56c1c5805bbaf1b658e62d4eeddc36c
