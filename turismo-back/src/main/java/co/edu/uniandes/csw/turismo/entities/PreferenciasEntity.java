/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.List;
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
    private List<String> tiposPlan;

    //GETTERS y SETTERS
    
    /**
     * @return los tipos plan 
     */
    public List<String> getTiposPlan() 
    {
        return tiposPlan;
    }

    /**
     * Cambia los tipos plan por los dados por parametro
     * @param tiposPlan 
     */
    public void setTiposPlan(List<String> tiposPlan) 
    {
        this.tiposPlan = tiposPlan;
    }
}
