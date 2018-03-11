/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PagoEntity;

/**
 *
 * Clase que extiende de {@link ComentariosDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos.
 * 
 * @author dl.avendano
 */
public class PagoDetailDTO extends PagoDTO{
    
    public PagoDetailDTO(PagoEntity entity) {
        super();
    }
    public PagoDetailDTO()
    {
        
    }
}
