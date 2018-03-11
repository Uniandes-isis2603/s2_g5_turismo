/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import javax.persistence.Entity;
import podam.StringSinNumerosStrategy;
import uk.co.jemos.podam.common.PodamStrategyValue;
/**
 *
 * @author jc.montoyar
 */
@Entity
public class GuiaEntity extends BaseEntity 
{
    //ATRIBUTOS
    @PodamStrategyValue(StringSinNumerosStrategy.class)
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
