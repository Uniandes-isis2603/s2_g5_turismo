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
 * @author s.benitez10
 */
@Entity
public class FacturaEntity extends  BaseEntity implements Serializable
{
    
    private Integer costo; // atributo del costo de la factura

     /**
     * @return the image
     */
    public Integer getCosto() {
        return costo;
    }
     /**
     * @Param costo
     */
    public void setCosto(Integer costo) {
        this.costo = costo;
    }
    
    
}
