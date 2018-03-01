/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PlanAjendadoEntity;
import java.util.Date;

/**
 *
 * @author dl.avendano
 */
public class PlanAjendadoDTO {
    Date fecha;
    
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public PlanAjendadoDTO(PlanAjendadoEntity entity) 
    {
        if(entity != null)
        {
        this.fecha = entity.getFecha();
        }
        
    }
     public PlanAjendadoEntity toEntity() {
        PlanAjendadoEntity entity = new PlanAjendadoEntity();
        entity.setFecha(fecha);
        return entity;
    } 
}
