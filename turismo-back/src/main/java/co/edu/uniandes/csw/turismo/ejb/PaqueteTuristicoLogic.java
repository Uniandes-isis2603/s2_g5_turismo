/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PagoEntity;
import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PagoPersistence;
import co.edu.uniandes.csw.turismo.persistence.PaqueteTuristicoPersistence;
import co.edu.uniandes.csw.turismo.persistence.PlanAgendadoPersistence;
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
public class PaqueteTuristicoLogic {
     private static final Logger LOGGER = Logger.getLogger(PaqueteTuristicoLogic.class.getName());

    @Inject
    private PagoPersistence persistencePago;
    
    @Inject
    private PaqueteTuristicoPersistence persistence;
    @Inject
    private PlanAgendadoPersistence persistencePlan;
    
    public PaqueteTuristicoEntity getPaquete(Long id) {
        return persistence.find(id);
    }
    
    public PaqueteTuristicoEntity createPaqueteTuristico(PaqueteTuristicoEntity entity) throws BusinessLogicException{
        List <PagoEntity> pagos = entity.getPagos();
        List <PlanAgendadoEntity> planes = entity.getPlanes();
        for(int i=0; i<pagos.size();i++)
        {
        if (pagos.size()!=planes.size())
             throw new BusinessLogicException("El numero de pagos no coincide con el numero de planes en el paquete \"");
        }
        return persistence.create(entity);
    }
    public List<PaqueteTuristicoEntity> getPaquetes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los paquetes");
        return persistence.findAll();
    }
    
    public PaqueteTuristicoEntity updatePaquete(PaqueteTuristicoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un paquete ");
        return persistence.update(entity);
    }
    public void deletePaqueteTuristico(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un paquete ");
        persistence.delete(id);
    }
   

}
