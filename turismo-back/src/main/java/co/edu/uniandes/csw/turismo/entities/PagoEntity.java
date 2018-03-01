/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author dl.avendano
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable{
    
   
    //@PodamStrategyValue(DateStrategy.class)(No se pa que es, pero lo tendre en cuenta
    @Temporal(TemporalType.DATE)
    
    private String nombrePlan;
    private Double costoPlan;
    

   
    /**
     * @return nombrePlan
     */
    public String getNombrePlan() {
        return nombrePlan;
    }

    /**
     * @param nombrePlan
     */
    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    /**
     * @return costoPlan 
     */
    public Double getCostoPlan() {
        return costoPlan;
    }
    /**
     * @param costoPlan
     */
    public void setCostoPlan(Double costoPlan) {
        this.costoPlan = costoPlan;
    }
    
    
    
}
