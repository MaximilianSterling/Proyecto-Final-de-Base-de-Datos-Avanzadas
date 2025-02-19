/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;

/**
 *
 * @author norma
 */
public class CitaSinCitaDAO implements ICitaSinCitaDAO {

    IConexionBD conexion;

    public CitaSinCitaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

}
