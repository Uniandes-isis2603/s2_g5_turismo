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
     * Busca si hay algun Guia con el nombre que se envía de argumento
     *
     * @param name: Nombre del Guia que se está buscando
     * @return null si no existe ningun Guia con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public GuiaEntity findByName(String name) 
    {
        LOGGER.log(Level.INFO, "Consultando guia por nombre ", name);

        // Se crea un query para buscar guias con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From GuiaEntity e where e.name = :name", GuiaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<GuiaEntity> sameName = query.getResultList();
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
    
    /* Busca si hay alguna reseña asociada a un libro y con un ID específico
     * @param bookid El ID del libro con respecto al cual se busca
     * @param reviewid El ID de la reseña buscada
     * @return La reseña encontrada o null. Nota: Si existe una o más reseñas 
     * devuelve siempre la primera que encuentra
     */
    public GuiaEntity find(Long planid, Long guiaid)
    {
        TypedQuery<GuiaEntity> q = em.createQuery("select p from GuiaEntity p where (p.planGuia.id = :planid) and (p.id = :guiaid)", GuiaEntity.class);
        q.setParameter("id", planid);
        q.setParameter("reviewid", guiaid);
        List<GuiaEntity> results = q.getResultList();
        GuiaEntity review = null;
        if (results == null) {
            review = null;
        } else if (results.isEmpty()) {
            review = null;
        } else if (results.size() >= 1) {
            review = results.get(0);
        }

        return review;
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
