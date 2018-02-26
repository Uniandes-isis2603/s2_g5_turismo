/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lf.rivera10
 */
@Stateless
public class ComentarioPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(ComentarioPersistence.class.getName());

    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;

     /**
     *
     * @param entity objeto comentario que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ComentarioEntity create(ComentarioEntity entity) {
        LOGGER.info("Creando un Comentario nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Comentario nuevo");
        return entity;
    }
    
     public List<ComentarioEntity> findAll() {
        LOGGER.info("Consultando todos los Comentario");
        TypedQuery query = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        return query.getResultList();
    }
     
     public ComentarioEntity find(Long id) {
        return em.find(ComentarioEntity.class, id);
    }

    public ComentarioEntity update(ComentarioEntity entity) {
         return em.merge(entity);
    }
    
   public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Comentario con id={0}", id);
        ComentarioEntity entity = em.find(ComentarioEntity.class, id);
        em.remove(entity);
    }
}
