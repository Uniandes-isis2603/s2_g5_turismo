/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jc.montoyar
 */
@Stateless
public class PreferenciasPersistence
{
    /**
     * Modela el logger
     */
    private static final Logger LOGGER = Logger.getLogger(PreferenciasPersistence.class.getName());

    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Preferencias que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PreferenciasEntity create(PreferenciasEntity entity)
    {
        LOGGER.info("Creando unas Preferencias nuevas");
        em.persist(entity);
        LOGGER.info("Creando unas Preferencias nuevas");
        return entity;
    }

    /**
     * Busca si hay algun Preferencias con el nombre que se envía de argumento
     *
     * @param name: Nombre del Preferencias que se está buscando
     * @return null si no existe ningun Preferencias con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public PreferenciasEntity findByName(String name) 
    {
        LOGGER.log(Level.INFO, "Consultando Preferencias por nombre ", name);

        // Se crea un query para buscar Preferencias con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From PreferenciasEntity e where e.tipoPlan = :name", PreferenciasEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<PreferenciasEntity> sameName = query.getResultList();
        if (sameName.isEmpty())
        {
            return null;
        } 
        else 
        {
            return sameName.get(0);
        }
    }

    /**
     * @return lista con todas las Preferencias 
     */
    public List<PreferenciasEntity> findAll()
    {
        LOGGER.info("Consultando todas las Preferencias");
        TypedQuery query = em.createQuery("select u from PreferenciasEntity u", PreferenciasEntity.class);
        return query.getResultList();
    }

    /**
     * Halla las Preferencias dado el id
     * @param id del Preferencias
     * @return PreferenciastEntity hallada
     */
    public PreferenciasEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando Preferencias con id={0}", id);
        return em.find(PreferenciasEntity.class, id);
    }

    /**
     * Actualiza las Preferencias dado su entity
     * @param entity del Preferencias
     * @return Preferencias actualizado
     */
    public PreferenciasEntity update(PreferenciasEntity entity) 
    {
        LOGGER.log(Level.INFO, "Actualizando preferencias con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Borra las Preferencias con id dado por parametro
     * @param id del Preferencias
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando preferencias con id={0}", id);
        PreferenciasEntity entity = em.find(PreferenciasEntity.class, id);
        em.remove(entity);
    }
    
}
