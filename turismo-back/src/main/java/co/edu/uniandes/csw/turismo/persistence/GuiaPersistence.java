/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
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
public class GuiaPersistence
{
    /**
     * Modela el logger
     */
    private static final Logger LOGGER = Logger.getLogger(GuiaPersistence.class.getName());

    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Guia que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public GuiaEntity create(GuiaEntity entity)
    {
        LOGGER.info("Creando un guia nuevo");
        em.persist(entity);
        LOGGER.info("Creando un guia nuevo");
        return entity;
    }

    /**
     * @return lista con todos los guias 
     */
    public List<GuiaEntity> findAll()
    {
        LOGGER.info("Consultando todos los Guias");
        TypedQuery query = em.createQuery("select u from GuiaEntity u", GuiaEntity.class);
        return query.getResultList();
    }

    /**
     * Halla el Guia dado el id
     * @param id del Guia
     * @return GuiatEntity hallada
     */
    public GuiaEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando guia con id={0}", id);
        return em.find(GuiaEntity.class, id);
    }
   
    /**
     * Actualiza el Guia dado su entity
     * @param entity del Guia
     * @return Guia actualizado
     */
    public GuiaEntity update(GuiaEntity entity) 
    {
        LOGGER.log(Level.INFO, "Actualizando guia con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Borra el Guia con id dado por parametro
     * @param id del Guia
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando guia con id={0}", id);
        GuiaEntity entity = em.find(GuiaEntity.class, id);
        em.remove(entity);
    }
    
}
