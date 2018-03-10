/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PagoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PagoPersistence;
import co.edu.uniandes.csw.turismo.persistence.PlanPersistence;
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
public class PagoLogic {
     private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());

    @Inject
    private PagoPersistence persistence;
    
    @Inject
    private PlanPersistence persistencePlan;
    
    public PagoEntity createPago(PagoEntity entity) throws BusinessLogicException{
        if (entity.getName()==null)
            throw new BusinessLogicException("El nombre del pago no debe ser nulo \"" + entity.getName()+"\"");
        //if (persistencePlan.findByName(entity.getName())== null)
          //  throw new BusinessLogicException("No existe un plan con el nombre \"" + entity.getName()+"\"");
        if (entity.getCostoPlan()==null || entity.getCostoPlan() <0)
            throw new BusinessLogicException("El costo del plan debe ser valido");
        return persistence.create(entity);
    }
    
    public List<PagoEntity> getPagos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los pagos");
        return persistence.findAll();
    }
    
    public PagoEntity getPago(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un pago con id = {0}", id);
        return persistence.find(id);
    }
    
    public PagoEntity updatePago(PagoEntity entity) throws BusinessLogicException{
        if (entity.getName()==null)
            throw new BusinessLogicException("El nombre del pago no debe ser nulo \"" + entity.getName()+"\"");
        //if (persistencePlan.findByName(entity.getName())== null)
          //  throw new BusinessLogicException("No existe un plan con el nombre \"" + entity.getName()+"\"");
        if (entity.getCostoPlan()==null || entity.getCostoPlan() <0)
            throw new BusinessLogicException("El costo del plan debe ser valido");
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un pago ");
        return persistence.update(entity);
    }
    public void deletePago(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un pago ");
        persistence.delete(id);
    }
}
