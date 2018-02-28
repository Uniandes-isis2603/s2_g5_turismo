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
public class PreferenciasEntity extends BaseEntity implements Serializable
{
    //ATRIBUTOS 
    
    /**
     * Modela los tipos de plan (preferencias)
     */
    private String tipoPlan;

    //GETTERS y SETTERS
    
    /**
     * @return el tipo de plan 
     */
    public String getTipoPlan() 
    {
        return tipoPlan;
    }

    /**
     * Cambia el tipo de plan por los dados por parametro
     * @param tipoPlan 
     */
    public void setTipoPlan(String tipoPlan) 
    {
        this.tipoPlan = tipoPlan;
    }
}
