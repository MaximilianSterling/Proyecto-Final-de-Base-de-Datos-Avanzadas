/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import excepciones.PersistenciaException;
import java.sql.Timestamp;

/**
 *
 * @author norma
 */
public interface ICitaDAO {
    
    public boolean agendarCita(Timestamp fechaHora, int idUsuarioPaciente, int idUsuarioMedico) throws PersistenciaException;
}
