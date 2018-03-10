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

    
    private Double costo;
    
   

    /**
     * @return costo
     */
    public Double getCosto() {
        return costo;
    }
    /**
     * @param costo
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
    
    
}
