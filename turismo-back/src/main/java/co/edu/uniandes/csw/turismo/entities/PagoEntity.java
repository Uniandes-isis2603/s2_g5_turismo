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
 * @author dl.avendano
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable{
    
    private String nombrePlan;
    private Integer costoPlan;
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
    public Integer getCostoPlan() {
        return costoPlan;
    }
    /**
     * @param costoPlan
     */
    public void setCostoPlan(Integer costoPlan) {
        this.costoPlan = costoPlan;
    }
    
    
    
}
