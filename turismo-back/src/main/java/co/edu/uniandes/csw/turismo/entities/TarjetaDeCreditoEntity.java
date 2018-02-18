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
public class TarjetaDeCreditoEntity extends BaseEntity implements Serializable
{
    
    private Integer numero;
    private String name;
    private Integer CDV;

     /**
     * @return del numero de la tarjete
     */
    public Integer getNumero() {
        return numero;
    }
    /**
     * @Param numero
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    /**
     * @return del nombre de la tarjeta
     */
    public String getName() {
        return name;
    }
    /**
     * @Param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return del numero de CDV
     */
    public Integer getCDV() {
        return CDV;
    }
    /**
     * @Param CDV
     */
    public void setCDV(Integer CDV) {
        this.CDV = CDV;
    }
    
    
    
}
