/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;

/**
 *
 * @author dl.avendano
 */
public class PagoEntity extends BaseEntity implements Serializable{
    
    private String nombrePlan;
    private Integer costoPlan;

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public Integer getCostoPlan() {
        return costoPlan;
    }

    public void setCostoPlan(Integer costoPlan) {
        this.costoPlan = costoPlan;
    }
    
    
    
}
