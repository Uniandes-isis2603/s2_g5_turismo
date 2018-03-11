package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import co.edu.uniandes.csw.turismo.entities.ValoracionesEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PlanPersistence;
import java.util.ArrayList;
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
    @Inject
    private GuiaLogic guiaLogic;
    @Inject
    private ValoracionesLogic valoracionLogic;
    @Inject
    private PreferenciasLogic preferenciasLogic;

    /**
     * Se crea un plan en persistencias si cumple con las reglas de negocio
     * @param entity
     * @return el entidad creada
     * @throws BusinessLogicException 
     */
    public PlanEntity createPlan(PlanEntity entity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de creación de Plan");
        if(entity.getName() == null)
        {
            throw new BusinessLogicException("El plan debe tener un nombre");
        }
        // Verifica la regla de negocio que dice que no puede haber dos Planes con el mismo nombre
        if (entity.getPreferenciasPlan() == null || entity.getPreferenciasPlan().isEmpty()) 
        {
            throw new BusinessLogicException("El plan debe estar asociado al menos a un tipo o categoria de plan ");
        }
        
        if (persistence.findByName(entity.getName()) != null) 
        {
            throw new BusinessLogicException("Ya existe un Plan con el nombre \"" + entity.getName() + "\"");
        }

        if(entity.getLatitud() == null || entity.getLongitud() == null || entity.getCiudad() == null || entity.getPais() == null)
        {
            //falta cambiar esto entity.getUbicacion().getLatitud...
            throw new BusinessLogicException("El plan debe tener datos de ubicación (latitud, longitud, ciudad y pais)");
        }
        List<PreferenciasEntity> prefs = new ArrayList();
        for(int i = 0; i < entity.getPreferenciasPlan().size(); i++ )
        {
            prefs.add(entity.getPreferenciasPlan().get(i));
        }
        entity.setPreferenciasPlan(new ArrayList());
        
        // Invoca la persistencia para crear la Plan
        persistence.create(entity);
        LOGGER.info("Termina proceso de creacion de Plan");
        
        for(int i = 0; i < prefs.size(); i++ )
        {
            PreferenciasEntity pref = prefs.get(i);
            PreferenciasEntity prefEm = preferenciasLogic.getByName(pref.getTipoPlan());
            if(prefEm != null)
            {
                addPreferencia(prefEm.getId(), entity.getId());
            }
            else
            {
                entity.getPreferenciasPlan().add(pref);
            }
            
        }
        return entity;
    }

    /**
     * Retorna una lista con todos los planes
     * @return lista con todos los planes
     */
    public List<PlanEntity> getPlans() 
    {
        LOGGER.info("Inicia proceso de consultar todos los planes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<PlanEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los planes");
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
        if (entity.getPreferenciasPlan() == null || entity.getPreferenciasPlan().isEmpty()) 
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
        LOGGER.log(Level.INFO, "Termina proceso de borrar plan con id={0}", id);
    }
    
    /**
     * Retorna guias del plan con id dado
     * @param planId
     * @return guias del plan
     */
    public List<GuiaEntity> listGuias (Long planId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los guias del plan con id = {0}", planId);
        return getPlan(planId).getGuias();
    }
    
    
    /**
     * Agregar un Guia al Plan
     *
     * @param GuiaId El id guia a guardar
     * @param PlanId El id de el Plan en la cual se va a guardar el
     * guia
     * @return El guia que fue agregado al Plan.
     */
    public GuiaEntity addGuia(Long GuiaId, Long PlanId)
    {
        PlanEntity planEntity = getPlan(PlanId);
        GuiaEntity guiaEntity = guiaLogic.getGuia(GuiaId);
        planEntity.getGuias().add(guiaEntity);
        return guiaEntity;
    }

    /**
     * Borrar un Guia de un Plan
     *
     * @param GuiaId El guia que se desea borrar del plan.
     * @param PlanId el Plan del cual se desea eliminar.
     */
    public void removeGuia(Long GuiaId, Long PlanId) 
    {
        PlanEntity planEntity = getPlan(PlanId);
        GuiaEntity guia = guiaLogic.getGuia(GuiaId);
        planEntity.getGuias().remove(guia);
    }

    /**
     * Remplazar Guias de un Plan
     *
     * @param Guias Lista de guias que serán los del Plan.
     * @param PlanId El id de el Plan que se quiere actualizar.
     * @return La lista de guias actualizada.
     */
    public List<GuiaEntity> replaceGuias(Long PlanId, List<GuiaEntity> Guias)
    {
        //Se obtiene el plan
        PlanEntity plan = getPlan(PlanId);
        
        plan.setGuias(Guias);
        return Guias;
    }

    /**
     * Retorna un Guia asociado a una Plan
     *
     * @param PlanId El id del Plan a buscar.
     * @param GuiaId El id del guia a buscar
     * @return El guia encontrado dentro de la Plan.
     * @throws BusinessLogicException Si el guia no se encuentra en el Plan
     */
    public GuiaEntity getGuia(Long PlanId, Long GuiaId) throws BusinessLogicException
    {
        List<GuiaEntity> Guias = getPlan(PlanId).getGuias();
        GuiaEntity Guia = guiaLogic.getGuia(GuiaId);
        int index = Guias.indexOf(Guia);
        if (index >= 0) {
            return Guias.get(index);
        }
        throw new BusinessLogicException("El guia no está asociado a el Plan");

    }
    
    /**
     * Agregar una valoracion al Plan
     *
     * @param ValoracionId la valoracion a guardar
     * @param PlanId El id de el Plan en la cual se va a guardar la
     * valoracion
     * @return La valoracion que fue agregado al Plan.
     */
    public ValoracionesEntity addValoracion(Long ValoracionId, Long PlanId)
    {
        PlanEntity planEntity = getPlan(PlanId); 
        ValoracionesEntity valEntity = valoracionLogic.getValoracion(ValoracionId);
        planEntity.getValoracionesPlan().add(valEntity);
        return valEntity;
    }

    /**
     * Borrar una valoracion de un Plan
     *
     * @param valoracionId La valoracion que se desea borrar del plan.
     * @param PlanId el Plan del cual se desea eliminar.
     */
    public void removeValoracion(Long valoracionId, Long PlanId) 
    {
        PlanEntity planEntity = getPlan(PlanId);
        ValoracionesEntity val = valoracionLogic.getValoracion(valoracionId);
        planEntity.getValoracionesPlan().remove(val);
    }

    /**
     * Remplazar valoraciones de un Plan
     *
     * @param vals Lista de valoraciones que serán las del Plan.
     * @param PlanId El id de el Plan que se quiere actualizar.
     * @return La lista de valoraciones actualizada.
     */
    public List<ValoracionesEntity> replaceValoraciones(Long PlanId, List<ValoracionesEntity> vals)
    {
        //Se obtiene el plan
        PlanEntity plan = getPlan(PlanId);
        plan.setValoracionesPlan(vals);
        return vals;
    }

    /**
     * Retorna una valoracion asociada a un Plan
     *
     * @param PlanId El id del Plan a buscar.
     * @param valId El id de la val a buscar
     * @return La val encontrada dentro del Plan.
     * @throws BusinessLogicException Si la val no se encuentra en el Plan
     */
    public ValoracionesEntity getVal(Long PlanId, Long valId) throws BusinessLogicException
    {
        List<ValoracionesEntity> vals = getPlan(PlanId).getValoracionesPlan();
        ValoracionesEntity val = valoracionLogic.getValoracion(valId);
        int index = vals.indexOf(val);
        if (index >= 0) {
            return vals.get(index);
        }
        throw new BusinessLogicException("La valoracion no está asociada a el Plan");

    }
          
    /**
     * Retorna las valoraciones del plan con id dado
     * @param planId
     * @return valoraciones del plan
     */
    public List<ValoracionesEntity> listValoraciones(Long planId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las valoraciones del plan con id = {0}", planId);
        return getPlan(planId).getValoracionesPlan();
    }
    
    /**
     * Retorna las caterogias del plan con id dado
     * @param planId
     * @return categorias del plan
     */
    public List<PreferenciasEntity> listCategorias(Long planId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las categorias del plan con id = {0}", planId);
        return getPlan(planId).getPreferenciasPlan();
    }
    
    /**
     * Agregar un Guia al Plan
     *
     * @param prefId El id guia a guardar
     * @param PlanId El id de el Plan en la cual se va a guardar el
     * guia
     * @return El guia que fue agregado al Plan.
     */
    public PreferenciasEntity addPreferencia(Long prefId, Long PlanId)
    {
        PlanEntity planEntity = getPlan(PlanId);
        PreferenciasEntity prefEntity = preferenciasLogic.getPreferencias(prefId);
        planEntity.getPreferenciasPlan().add(prefEntity);
        return prefEntity;
    }

    /**
     * Borrar unas categorias de un Plan
     *
     * @param prefId Las categorias que se desea borrar del plan.
     * @param PlanId el Plan del cual se desea eliminar.
     */
    public void removePreferencia(Long prefId, Long PlanId) 
    {
        PlanEntity planEntity = getPlan(PlanId);
        PreferenciasEntity pref = preferenciasLogic.getPreferencias(prefId);
        planEntity.getPreferenciasPlan().remove(pref);
    }

    /**
     * Remplazar prefs de un Plan
     *
     * @param prefs Lista de prefs que serán los del Plan.
     * @param PlanId El id de el Plan que se quiere actualizar.
     * @return La lista de prefs actualizada.
     */
    public List<PreferenciasEntity> replacePreferencias(Long PlanId, List<PreferenciasEntity> prefs)
    {
        //Se obtiene el plan
        PlanEntity plan = getPlan(PlanId);
        
        plan.setPreferenciasPlan(prefs);
        return prefs;
    }

    /**
     * Retorna unas preferencias asociadas a un Plan
     *
     * @param PlanId El id del Plan a buscar.
     * @param PreferenciasId El id de las preferencias a buscar
     * @return Las preferencias encontrado dentro de la Plan.
     * @throws BusinessLogicException Si el guia no se encuentran las prefs
     */
    public PreferenciasEntity getPreferencias(Long PlanId, Long PreferenciasId) throws BusinessLogicException
    {
        List<PreferenciasEntity> prefs = getPlan(PlanId).getPreferenciasPlan();
        PreferenciasEntity pref = preferenciasLogic.getPreferencias(PreferenciasId);
        int index = prefs.indexOf(pref);
        if (index >= 0) {
            return prefs.get(index);
        }
        
        throw new BusinessLogicException("Las preferencias no están asociadas a el Plan");

    }
    
    public PlanEntity getByName(String name)
    {
        return persistence.findByName(name);
    }
}
