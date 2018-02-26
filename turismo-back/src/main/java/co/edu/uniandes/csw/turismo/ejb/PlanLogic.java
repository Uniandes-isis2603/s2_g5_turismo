package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PlanPersistence;
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
public class PlanLogic
{

    private static final Logger LOGGER = Logger.getLogger(PlanLogic.class.getName());

    @Inject
    private PlanPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es un inyección de dependencias.

    /**
     * Se crea un plan en persistencias si cumple con las reglas de negocio
     * @param entity
     * @return el entidad creada
     * @throws BusinessLogicException 
     */
    public PlanEntity createPlan(PlanEntity entity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de creación de Plan");
        // Verifica la regla de negocio que dice que no puede haber dos Planes con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) 
        {
            throw new BusinessLogicException("Ya existe un Plan con el nombre \"" + entity.getName() + "\"");
        }
        boolean tipoAdulto = false;
        boolean tipoFamiliar = false;
        if (entity.getPreferenciasPlan() != null && !entity.getPreferenciasPlan().isEmpty()) 
        {
            for (PreferenciasEntity prefEntity : entity.getPreferenciasPlan()) 
            {
                for (String cat : prefEntity.getTiposPlan()) 
                {
                    if (cat.equalsIgnoreCase("adulto") || cat.equalsIgnoreCase("romantico"))
                    {
                        tipoAdulto = true;
                    }
                    if (cat.equalsIgnoreCase("familiar") || cat.equalsIgnoreCase("niños")) 
                    {
                        tipoFamiliar = true;
                    }
                    if (tipoFamiliar && tipoAdulto)
                    {
                        throw new BusinessLogicException("Hay conflicto en las categorias del plan ");
                    }                         
                }
            }
        }
        else
        {
            throw new BusinessLogicException("El plan debe estar asociado al menos a un tipo o categoria de plan ");
        }
        // Invoca la persistencia para crear la Plan
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Plan");
        return entity;
    }

    /**
     * Retorna una lista con todos los planes
     * @return lista con todos los planes
     */
    public List<PlanEntity> getPlans() 
    {
        LOGGER.info("Inicia proceso de consultar todas los planes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<PlanEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas los planes");
        return editorials;
    }

    /**
     * Retorna plan dado el id
     * @param id
     * @return plan con id dado
     */
    public PlanEntity getPlan(Long id) 
    {
        return persistence.find(id);
    }

    /**
     * Updatea un plan si cumple con reglas de negocio
     * @param entity
     * @return plan updateado
     * @throws BusinessLogicException si no se cumple las reglas de negocio
     */
    public PlanEntity updatePlan(PlanEntity entity) throws BusinessLogicException 
    {
        if (persistence.findByName(entity.getName()) != null) 
        {
            throw new BusinessLogicException("Ya existe un Plan con el nombre \"" + entity.getName() + "\"");
        }
        boolean tipoAdulto = false;
        boolean tipoFamiliar = false;
        if (entity.getPreferenciasPlan() != null && !entity.getPreferenciasPlan().isEmpty()) 
        {
            for (PreferenciasEntity prefEntity : entity.getPreferenciasPlan()) 
            {
                for (String cat : prefEntity.getTiposPlan()) 
                {
                    if (cat.equalsIgnoreCase("adulto") || cat.equalsIgnoreCase("romantico"))
                    {
                        tipoAdulto = true;
                    }
                    if (cat.equalsIgnoreCase("familiar") || cat.equalsIgnoreCase("niños")) 
                    {
                        tipoFamiliar = true;
                    }
                    if (tipoFamiliar && tipoAdulto)
                    {
                        throw new BusinessLogicException("Hay conflicto en las categorias del plan ");
                    }
                }
            }
        }
        else
        {
            throw new BusinessLogicException("El plan debe estar asociado al menos a un tipo o categoria de plan ");
        }
        return persistence.update(entity);
    }
    
    /**
     * Borra un plan dado su id
     * @param id
     * @throws BusinessLogicException 
     */
    public void deletePlan(Long id) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar Plan con id={0}", id);    
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar libro con id={0}", id);
    }
}
