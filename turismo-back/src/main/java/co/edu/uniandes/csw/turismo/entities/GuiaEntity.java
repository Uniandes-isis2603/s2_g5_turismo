/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.montoyar
 */
@Entity
public class GuiaEntity extends BaseEntity implements Serializable 
{
    //ATRIBUTOS
    
    /**
     * Modela los guias del plan
     */
    @PodamExclude
    @ManyToOne 
    private PlanEntity planGuia; 

    /**
     * @return plan asociado al guia 
     */
    public PlanEntity getPlanGuia() 
    {
        return planGuia;
    }

    /**
     * Cambia el plan asociado al guia por el dado por parametro
     * @param planGuia 
     */
    public void setPlanGuia(PlanEntity planGuia) 
    {
        this.planGuia = planGuia;
    }
    
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
