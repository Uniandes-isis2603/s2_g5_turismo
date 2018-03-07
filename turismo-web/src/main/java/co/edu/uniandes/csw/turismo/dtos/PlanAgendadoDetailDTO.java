/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dl.avendano
 */
public class PlanAgendadoDetailDTO extends PlanAgendadoDTO {
    private GuiaDTO guia;
    private PlanDTO plan;

    public PlanAgendadoDetailDTO(PlanAgendadoEntity entity) {
        super(entity);
        if (entity != null) {
            if( entity.getGuia() != null)
        {
            guia = new GuiaDTO(entity.getGuia());
        }
            if( entity.getPlan() != null)
        {
            plan = new PlanDTO(entity.getPlan());
        }
        }
    }
    
    @Override
    public PlanAgendadoEntity toEntity() {
        
        PlanAgendadoEntity entity = super.toEntity();
        if (this.getPlan() != null) {
            entity.setPlan(this.getPlan().toEntity());
        }
        if (this.getGuia()!= null) {
            entity.setGuia(this.getGuia().toEntity());
        }

        return entity;
    }
    public GuiaDTO getGuia() {
        return guia;
    }

    public void setGuia(GuiaDTO guia) {
        this.guia = guia;
    }

    public PlanDTO getPlan() {
        return plan;
    }

    public void setPlan(PlanDTO plan) {
        this.plan = plan;
    }
    
}
