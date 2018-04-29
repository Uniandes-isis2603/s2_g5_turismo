package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import co.edu.uniandes.csw.turismo.entities.UbicacionEntity;
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
    @Inject
    private UbicacionLogic ubicacionLogic;

    /**
     * Se crea un plan en persistencias si cumple con las reglas de negocio
     * @param entity
     * @return el entidad creada
     * @throws BusinessLogicException 
     */
    public PlanEntity createPlan(PlanEntity entity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de creación de Plan");
        if(entity.getName() == null || entity.getName().length() == 0)
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

        if(entity.getUbicacion() == null || entity.getUbicacion().getLatitud() == null || entity.getUbicacion().getLongitud() == null || entity.getUbicacion().getCiudad() == null || entity.getUbicacion().getPais() == null)
        {
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
        List<PlanEntity> plans = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los planes");
        return plans;
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
        if(entity.getName() == null)
        {
            throw new BusinessLogicException("El plan debe tener un nombre");
        }
        if(entity.getUbicacion() == null || entity.getUbicacion().getLatitud() == null || entity.getUbicacion().getLongitud() == null || entity.getUbicacion().getCiudad() == null || entity.getUbicacion().getPais() == null)
        {
            throw new BusinessLogicException("El plan debe tener datos de ubicación (latitud, longitud, ciudad y pais)");
        }
        if (persistence.findByName(entity.getName()) != null) 
        {
            throw new BusinessLogicException("Ya existe un Plan con el nombre \"" + entity.getName() + "\"");
        }
        
        PlanEntity old = getPlan(entity.getId());
        
        entity.setUbicacion(old.getUbicacion());
        entity.setPreferenciasPlan(old.getPreferenciasPlan());
        entity.setGuias(old.getGuias());
        entity.setValoracionesPlan(old.getValoracionesPlan());
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
     * @param guiaId El id guia a guardar
     * @param planId El id de el Plan en la cual se va a guardar el
     * guia
     * @return El guia que fue agregado al Plan.
     */
    public GuiaEntity addGuia(Long guiaId, Long planId)
    {
        PlanEntity planEntity = getPlan(planId);
        GuiaEntity guiaEntity = guiaLogic.getGuia(guiaId);
        planEntity.getGuias().add(guiaEntity);
        return guiaEntity;
    }

    /**
     * Borrar un Guia de un Plan
     *
     * @param guiaId El guia que se desea borrar del plan.
     * @param planId el Plan del cual se desea eliminar.
     */
    public void removeGuia(Long guiaId, Long planId) 
    {
        PlanEntity planEntity = getPlan(planId);
        GuiaEntity guia = guiaLogic.getGuia(guiaId);
        planEntity.getGuias().remove(guia);
    }

    /**
     * Remplazar Guias de un Plan
     *
     * @param guias Lista de guias que serán los del Plan.
     * @param planId El id de el Plan que se quiere actualizar.
     * @return La lista de guias actualizada.
     */
    public List<GuiaEntity> replaceGuias(Long planId, List<GuiaEntity> guias)
    {
        //Se obtiene el plan
        PlanEntity plan = getPlan(planId);
        
        plan.setGuias(guias);
        return guias;
    }

    /**
     * Retorna un Guia asociado a una Plan
     *
     * @param planId El id del Plan a buscar.
     * @param guiaId El id del guia a buscar
     * @return El guia encontrado dentro de la Plan.
     * @throws BusinessLogicException Si el guia no se encuentra en el Plan
     */
    public GuiaEntity getGuia(Long planId, Long guiaId) throws BusinessLogicException
    {
        List<GuiaEntity> guias = getPlan(planId).getGuias();
        GuiaEntity guia = guiaLogic.getGuia(guiaId);
        int index = guias.indexOf(guia);
        if (index >= 0)
        {
            return guias.get(index);
        }
        throw new BusinessLogicException("El guia no está asociado a el Plan");

    }
    
    /**
     * Agregar una valoracion al Plan
     *
     * @param valoracionId la valoracion a guardar
     * @param planId El id de el Plan en la cual se va a guardar la
     * valoracion
     * @return La valoracion que fue agregado al Plan.
     */
    public ValoracionesEntity addValoracion(Long valoracionId, Long planId)
    {
        PlanEntity planEntity = getPlan(planId); 
        ValoracionesEntity valEntity = valoracionLogic.getValoracion(valoracionId);
        planEntity.getValoracionesPlan().add(valEntity);
        return valEntity;
    }

    /**
     * Borrar una valoracion de un Plan
     *
     * @param valoracionId La valoracion que se desea borrar del plan.
     * @param planId el Plan del cual se desea eliminar.
     */
    public void removeValoracion(Long valoracionId, Long planId) 
    {
        PlanEntity planEntity = getPlan(planId);
        ValoracionesEntity val = valoracionLogic.getValoracion(valoracionId);
        planEntity.getValoracionesPlan().remove(val);
    }

    /**
     * Remplazar valoraciones de un Plan
     *
     * @param vals Lista de valoraciones que serán las del Plan.
     * @param planId El id de el Plan que se quiere actualizar.
     * @return La lista de valoraciones actualizada.
     */
    public List<ValoracionesEntity> replaceValoraciones(Long planId, List<ValoracionesEntity> vals)
    {
        //Se obtiene el plan
        PlanEntity plan = getPlan(planId);
        plan.setValoracionesPlan(vals);
        return vals;
    }

    /**
     * Retorna una valoracion asociada a un Plan
     *
     * @param planId El id del Plan a buscar.
     * @param valId El id de la val a buscar
     * @return La val encontrada dentro del Plan.
     * @throws BusinessLogicException Si la val no se encuentra en el Plan
     */
    public ValoracionesEntity getVal(Long planId, Long valId) throws BusinessLogicException
    {
        List<ValoracionesEntity> vals = getPlan(planId).getValoracionesPlan();
        ValoracionesEntity val = valoracionLogic.getValoracion(valId);
        int index = vals.indexOf(val);
        if (index >= 0) 
        {
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
     * @param planId El id de el Plan en la cual se va a guardar el
     * guia
     * @return El guia que fue agregado al Plan.
     */
    public PreferenciasEntity addPreferencia(Long prefId, Long planId)
    {
        PlanEntity planEntity = getPlan(planId);
        PreferenciasEntity prefEntity = preferenciasLogic.getPreferencias(prefId);
        planEntity.getPreferenciasPlan().add(prefEntity);
        return prefEntity;
    }

    /**
     * Borrar unas categorias de un Plan
     *
     * @param prefId Las categorias que se desea borrar del plan.
     * @param planId el Plan del cual se desea eliminar.
     */
    public void removePreferencia(Long prefId, Long planId) 
    {
        PlanEntity planEntity = getPlan(planId);
        PreferenciasEntity pref = preferenciasLogic.getPreferencias(prefId);
        planEntity.getPreferenciasPlan().remove(pref);
    }

    /**
     * Remplazar prefs de un Plan
     *
     * @param prefs Lista de prefs que serán los del Plan.
     * @param planId El id de el Plan que se quiere actualizar.
     * @return La lista de prefs actualizada.
     */
    public List<PreferenciasEntity> replacePreferencias(Long planId, List<PreferenciasEntity> prefs)
    {
        //Se obtiene el plan
        PlanEntity plan = getPlan(planId);
        
        plan.setPreferenciasPlan(prefs);
        return prefs;
    }

    /**
     * Retorna unas preferencias asociadas a un Plan
     *
     * @param planId El id del Plan a buscar.
     * @param preferenciasId El id de las preferencias a buscar
     * @return Las preferencias encontrado dentro de la Plan.
     * @throws BusinessLogicException Si el guia no se encuentran las prefs
     */
    public PreferenciasEntity getPreferencias(Long planId, Long preferenciasId) throws BusinessLogicException
    {
        List<PreferenciasEntity> prefs = getPlan(planId).getPreferenciasPlan();
        PreferenciasEntity pref = preferenciasLogic.getPreferencias(preferenciasId);
        int index = prefs.indexOf(pref);
        if (index >= 0)
        {
            return prefs.get(index);
        }
        
        throw new BusinessLogicException("Las preferencias no están asociadas a el Plan");

    }
    
    /**
     * Retorna el plan que tiene el nombre dado por parametro
     * @param name del plan
     * @return plan dado el nombre
     */
    public PlanEntity getByName(String name)
    {
        return persistence.findByName(name);
    }
    
    /**
     * Retorna la ubicacion dado el id del plan
     * @param idPlan 
     * @return Ubicacion del plan con id dado
     */
    public UbicacionEntity getUbicacionDePlan(Long idPlan)
    {
        return persistence.find(idPlan).getUbicacion();
    }
    
    /**
     * Cambia la ubicación de un plan por otro dado por id
     * @param idPlan id del plan a cambiar la ubicacion
     * @param idUbicacion id de la ubicacion nueva del plan
     * @return  ubicacion que se updateo al plan
     */
    public UbicacionEntity replaceUbicacion(Long idPlan, Long idUbicacion)
    {
        PlanEntity entity = persistence.find(idPlan);
        UbicacionEntity ubic = ubicacionLogic.getUbicacion(idUbicacion);
        entity.setUbicacion(ubic);
        return ubic;  
    }        
}
