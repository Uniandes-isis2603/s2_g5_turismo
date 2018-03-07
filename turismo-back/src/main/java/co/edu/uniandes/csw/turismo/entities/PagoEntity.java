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

    
    private Double costoPlan;
    
   

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
