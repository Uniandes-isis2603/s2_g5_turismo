/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author jc.montoyar
 */
@Entity
public class GuiaEntity extends BaseEntity implements Serializable 
{
    //ATRIBUTOS
    
    /**
     * Modela el idioma del guia
     */
    private String idiomaGuia;

    //GETTERS Y SETTERS
    
    /**
     * @return idioma del guia 
     */
    public String getIdiomaGuia() 
    {
        return idiomaGuia;
    }

    /**
     * Cambia el idioma por el dado por parametro
     * @param idiomaGuia 
     */
    public void setIdiomaGuia(String idiomaGuia) 
    {
        this.idiomaGuia = idiomaGuia;
    }
}
