/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.UbicacionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.UbicacionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.benitez10
 */
@Stateless
public class UbicacionLogic 
{
    private static final Logger LOGGER = Logger.getLogger(UbicacionLogic.class.getName());
     
    @Inject
    private UbicacionPersistence persistence;
    
    //@Inject
    //private PlanPersistence persistence;
    
    public UbicacionEntity createUbicacion(UbicacionEntity entity) throws BusinessLogicException 
    {
        LOGGER.info("Inicia proceso de creación de Ubicacion");
     
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Ubicacion");
        return entity;
    }
    public List<UbicacionEntity> getUbicacions()
    {
        LOGGER.info("Inicia proceso de consultar todas las ubicaciones");
        List<UbicacionEntity> tarjetas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las ubicaciones");
        return tarjetas;
        
    }
     public UbicacionEntity getUbicacion(Long id) 
     {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar ubicacion con id={0}", id);
        UbicacionEntity ubic= persistence.find(id);
        if (ubic == null) {
            LOGGER.log(Level.SEVERE, "ubicacion con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la ubicacion con id={0}", id);
        return ubic;
    }
     
     
     public void deleteUbicacion(Long id)
     {
         UbicacionEntity ubic= persistence.find(id);
        if (ubic == null) {
            LOGGER.log(Level.SEVERE, "ubicacion con el id {0} no existe", id);
        }
  
         LOGGER.log(Level.INFO, "Inicia proceso de Eliminar ubicacion con id", id);
         persistence.delete(id);
         LOGGER.log(Level.INFO, "Termina proceso de Eliminar ubicacion con id", id);
         
     };
     
     public UbicacionEntity updateUbicacion(Long id, UbicacionEntity entity) throws BusinessLogicException 
     {
        entity.setId(id);
        UbicacionEntity newEntity = persistence.update(entity);
        return newEntity;
     }
    
    
}
