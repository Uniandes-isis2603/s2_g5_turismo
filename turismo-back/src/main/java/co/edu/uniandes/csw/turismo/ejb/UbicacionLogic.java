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
    /**
     * modela el logger
     */
    private static final Logger LOGGER = Logger.getLogger(UbicacionLogic.class.getName());
     /**
      * llama la capa de persistencia de ubicacion
      */
    @Inject
    private UbicacionPersistence persistence;
    /**
     * le pide a la capa de persistencia guardar una ubicacion
     * @param entity
     * @return ubicacion guardada
     * @throws BusinessLogicException 
     */
    public UbicacionEntity createUbicacion(UbicacionEntity entity) throws BusinessLogicException 
    {
        LOGGER.info("Inicia proceso de creación de Ubicacion");
     
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Ubicacion");
        return entity;
    }
    /**
     * le pide a la capa de ubicaciones que devuelva todas las ubicaciones guardadas
     * @return lista de ubicaciones
     */
    public List<UbicacionEntity> getUbicacions()
    {
        LOGGER.info("Inicia proceso de consultar todas las ubicaciones");
        List<UbicacionEntity> tarjetas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las ubicaciones");
        return tarjetas;
        
    }
    /**
     * le pide a la capa de persistencia que le devuelva una ubicacion
     * @param id
     * @return  ubicacion
     */
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
    /**
     * le pide a la capa de persistencia eliminar una ubicacion
     * @param id 
     */
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
    /**
     * actualiza una ubicacion con el id y datos de la nueva ubicacion pasados por parametro
     * @param id
     * @param entity
     * @return ubicacion modificada
     * @throws BusinessLogicException 
     */ 
    public UbicacionEntity updateUbicacion(Long id, UbicacionEntity entity) throws BusinessLogicException 
     {
        entity.setId(id);
        UbicacionEntity newEntity = persistence.update(entity);
        return newEntity;
     }
    
    
}
