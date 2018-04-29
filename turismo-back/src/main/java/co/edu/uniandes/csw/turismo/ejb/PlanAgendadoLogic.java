/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.GuiaPersistence;
import co.edu.uniandes.csw.turismo.persistence.PlanAgendadoPersistence;
import co.edu.uniandes.csw.turismo.persistence.PlanPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author dl.avendano
 */
@Stateless
public class PlanAgendadoLogic {
    private static final Logger LOGGER = Logger.getLogger(PlanAgendadoLogic.class.getName());

    
    Date fechaActual = new Date();
    
    @Inject
    private PlanAgendadoPersistence persistence;
    
    @Inject
    private PlanPersistence persistencePlan;
    
    @Inject
    private GuiaPersistence persistenceGuia;
    
    public PlanAgendadoEntity getPlanAgendado(Long id) {
        return persistence.find(id);
    }
     public PlanAgendadoEntity createPlanAgendado(PlanAgendadoEntity entity) throws BusinessLogicException{
        
         if(entity.getGuia()!=null ){
        GuiaEntity guia = persistenceGuia.find(entity.getGuia().getId());
       
        
        entity.setGuia(guia);
        
         }
         
         if(entity.getPlan()!=null ){
        PlanEntity plan = persistencePlan.find(entity.getPlan().getId());
        entity.setPlan(plan);
         }
        if (entity.getFecha().after(fechaActual))
            throw new BusinessLogicException("la fecha es invalida \"");
         
        return persistence.create(entity);
    }
     public List<PlanAgendadoEntity> getPlanesAgendados() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los planes");
        return persistence.findAll();
    }
     
    public PlanAgendadoEntity updatePlanAgendado(PlanAgendadoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un plan ");
        return persistence.update(entity);
    }
    public void deletePlanAgendado(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un plan ");
        persistence.delete(id);
    }
}
