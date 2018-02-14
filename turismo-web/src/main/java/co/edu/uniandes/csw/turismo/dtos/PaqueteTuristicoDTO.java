/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 *
 * @author dl.avendano
 */
public class PaqueteTuristicoDTO {
    private long id;
    
    /**
     * Constructor por defecto
     */
    public PaqueteTuristicoDTO()
    { 
    }
    /**
     * @return El identificador del paquete
     */
    public long getId() {
        return id;
    }

    /**
     * @param id Identificador del paquete
     */
    public void setId(long id) {
        this.id = id;
    }
    
}
