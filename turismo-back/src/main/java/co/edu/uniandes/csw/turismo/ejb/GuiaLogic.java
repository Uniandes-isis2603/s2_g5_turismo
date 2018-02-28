package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.GuiaPersistence;
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
public class GuiaLogic
{

    private static final Logger LOGGER = Logger.getLogger(GuiaLogic.class.getName());

    @Inject
    private GuiaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es un inyección de dependencias.

    /**
     * Se crea un Guia en persistencias si cumple con las reglas de negocio
     * @param entity
     * @return el entidad creada
     * @throws BusinessLogicException 
     */
    public GuiaEntity createGuia(GuiaEntity entity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de creación de Guia");
        //Un guia debe tener nombre,ni debe tener numeros 
        if(entity.getName()== null || entity.getName().length()==0)
        {
            throw new BusinessLogicException("El guia debe tener un nombre");
        }
        else if(entity.getName().matches(".*\\d+.*"))
        {
           throw new BusinessLogicException("El nombre del guia no debe contener numeros"); 
        }
        if(entity.getIdiomaGuia() == null || entity.getIdiomaGuia().length()==0)
        {
            throw new BusinessLogicException("El guia debe tener un idioma no null"); 
        }
        // Invoca la persistencia para crear la Guia
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Guia");
        return entity;
    }
    
    /**
     * Retorna una lista con todos los Guias
     * @return lista con todos los Guias
     */
    public List<GuiaEntity> getGuias() 
    {
        LOGGER.info("Inicia proceso de consultar todas los Guias");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<GuiaEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas los Guias");
        return editorials;
    }

    /**
     * Retorna Guia dado el id
     * @param id
     * @return Guia con id dado
     */
    public GuiaEntity getGuia(Long id) 
    {
        return persistence.find(id);
    }

    /**
     * Updatea un Guia si cumple con reglas de negocio
     * @param entity
     * @return Guia updateado
     * @throws BusinessLogicException si no se cumple las reglas de negocio
     */
    public GuiaEntity updateGuia(GuiaEntity entity) throws BusinessLogicException 
    {
        LOGGER.info("Inicia proceso de creación de Guia");
        //Un guia debe tener nombre,ni debe tener numeros 
        if(entity.getName()== null || entity.getName().length()==0)
        {
            throw new BusinessLogicException("El guia debe tener un nombre");
        }
        else if(entity.getName().matches(".*\\d+.*"))
        {
           throw new BusinessLogicException("El nombre del guia no debe contener numeros"); 
        }
        if(entity.getIdiomaGuia() == null || entity.getIdiomaGuia().length()==0)
        {
            throw new BusinessLogicException("El guia debe tener un idioma no null"); 
        }
        return persistence.update(entity);
    }
    
    /**
     * Borra un Guia dado su id
     * @param id
     * @throws BusinessLogicException 
     */
    public void deleteGuia(Long id) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar Guia con id={0}", id);    
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar libro con id={0}", id);
    }
}