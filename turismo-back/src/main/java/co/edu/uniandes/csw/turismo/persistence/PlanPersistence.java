/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.PlanEntity;
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
public class PlanPersistence
{
    /**
     * Modela el logger
     */
    private static final Logger LOGGER = Logger.getLogger(PlanPersistence.class.getName());

    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Plan que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PlanEntity create(PlanEntity entity)
    {
        LOGGER.info("Creando un plan nuevo");
        em.persist(entity);
        LOGGER.info("plan creado");
        return entity;
    }

    /**
     * Busca si hay algun Plan con el nombre que se envía de argumento
     *
     * @param name: Nombre del Plan que se está buscando
     * @return null si no existe ningun Plan con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public PlanEntity findByName(String name) 
    {
        LOGGER.log(Level.INFO, "Consultando plan por nombre ", name);

        // Se crea un query para buscar Planes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From PlanEntity e where e.name = :name", PlanEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<PlanEntity> sameName = query.getResultList();

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
     * @return lista con todos los planes 
     */
    public List<PlanEntity> findAll()
    {
        LOGGER.info("Consultando todos los planes");
        TypedQuery query = em.createQuery("select u from PlanEntity u", PlanEntity.class);
        return query.getResultList();
    }

    /**
     * Halla el plan dado el id
     * @param id del plan
     * @return PlantEntity hallada
     */
    public PlanEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando plan con id={0}", id);
        return em.find(PlanEntity.class, id);
    }

    /**
     * Actualiza el plan dado su entity
     * @param entity del plan
     * @return plan actualizado
     */
    public PlanEntity update(PlanEntity entity) 
    {
        LOGGER.log(Level.INFO, "Actualizando plan con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Borra el plan con id dado por parametro
     * @param id del plan
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando plan con id={0}", id);
        PlanEntity entity = em.find(PlanEntity.class, id);
        em.remove(entity);
    }
    
}
