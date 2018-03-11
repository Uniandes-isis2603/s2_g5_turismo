package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PreferenciasPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.montoyar
 */
@Stateless
public class PreferenciasLogic
{

    private static final Logger LOGGER = Logger.getLogger(PreferenciasLogic.class.getName());

    @Inject
    private PreferenciasPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es un inyección de dependencias.

    /**
     * Se crea un Preferencias en persistencias si cumple con las reglas de negocio
     * @param entity
     * @return el entidad creada
     * @throws BusinessLogicException 
     */
    public PreferenciasEntity createPreferencias(PreferenciasEntity entity) throws BusinessLogicException
    {
       LOGGER.info("Inicia proceso de creación de Preferencias");
       if(entity.getTipoPlan() == null)
       {
           throw  new BusinessLogicException("El nombre de la preferencia no puede ser null");
       }
       if(persistence.findByName(entity.getTipoPlan()) != null)
       {
      
            throw  new BusinessLogicException("Ya existe la preferencia \"" + entity.getTipoPlan() + "\"" + " en el sistema");
       }
                   
        // Invoca la persistencia para crear la Preferencias
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Preferencias");
        return entity;
    }
    
    
    /**
     * Retorna una lista con todos los Preferenciass
     * @return lista con todos los Preferenciass
     */
    public List<PreferenciasEntity> getPreferenciass() 
    {
        LOGGER.info("Inicia proceso de consultar todas los Preferenciass");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<PreferenciasEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas los Preferenciass");
        return editorials;
    }

    /**
     * Retorna Preferencias dado el id
     * @param id
     * @return Preferencias con id dado
     */
    public PreferenciasEntity getPreferencias(Long id) 
    {
        return persistence.find(id);
    }

    /**
     * Updatea un Preferencias si cumple con reglas de negocio
     * @param entity
     * @return Preferencias updateado
     * @throws BusinessLogicException si no se cumple las reglas de negocio
     */
    public PreferenciasEntity updatePreferencias(PreferenciasEntity entity) throws BusinessLogicException 
    {
       if(entity.getTipoPlan() == null)
       {
           throw  new BusinessLogicException("El nombre de la preferencia no puede ser null");
       }
       if(persistence.findByName(entity.getTipoPlan()) != null)
       {
      
            throw  new BusinessLogicException("Ya existe la preferencia \"" + entity.getTipoPlan() + "\"" + " en el sistema");
       }
        return persistence.update(entity);
    }
    
    /**
     * Borra un Preferencias dado su id
     * @param id
     * @throws BusinessLogicException 
     */
    public void deletePreferencias(Long id) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar Preferencias con id={0}", id); 
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar libro con id={0}", id);
    }
    
    public PreferenciasEntity getByName(String name)
    {
        return persistence.findByName(name);
    }
}