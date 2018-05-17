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
    /**
     * Atriburo que modela la fecha del plan
     */
    Date fecha;
    /**
     * Atriburo que modela el id del plan
     */
    Long id;
    
    /**
     * Costructor por defecto
     */
    public PlanAgendadoDTO()
    {
        
    }
    /**
     * 
     * @return id del plan agendado
     */
    public Long getId() {
        return id;
    }
    /**
     * Asigna id del plan agendado
     * @param id nuevo
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 
     * @return fecha del plan agendado
     */
    public Date getFecha() {
        return fecha;
    }
    /**
     * Asigna fecha del plan agendado
     * @param fecha nueva
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /**
     * Convierte a DTO un entity de plan agendado.
     */
    public PlanAgendadoDTO(PlanAgendadoEntity entity) 
    {
        if(entity != null)
        {
        this.id = entity.getId();
        this.fecha = entity.getFecha();
        }
        
    }
    /**
     * Convierte a DTO un entity de plan agendado.
     */
     public PlanAgendadoEntity toEntity() {
        PlanAgendadoEntity entity = new PlanAgendadoEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        return entity;
    } 
     
     
}
