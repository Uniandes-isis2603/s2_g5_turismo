/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import java.util.Date;

/**
 *
 * @author dl.avendano
 */
public class PlanAgendadoDTO {
    Date fecha;
    
    Long id;
    
    public PlanAgendadoDTO()
    {
        
    }

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
    public PlanAgendadoDTO(PlanAgendadoEntity entity) 
    {
        if(entity != null)
        {
        this.id = entity.getId();
        this.fecha = entity.getFecha();
        }
        
    }
     public PlanAgendadoEntity toEntity() {
        PlanAgendadoEntity entity = new PlanAgendadoEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        return entity;
    } 
     
     
}
