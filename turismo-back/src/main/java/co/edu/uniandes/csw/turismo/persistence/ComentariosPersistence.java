/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;


import co.edu.uniandes.csw.turismo.entities.ComentariosEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lf.rivera10
 */
@Stateless
public class ComentariosPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ComentariosEntity.class.getName());

    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;

    public ComentariosEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando comentario con id={0}", id);
        return em.find(ComentariosEntity.class, id);
    }
    
      public List<ComentariosEntity> findAll() {
        LOGGER.info("Consultando todas los Comentarios");
        Query q = em.createQuery("select u from ComentariosEntity u");
        return q.getResultList();
    }
    
    public ComentariosEntity create(ComentariosEntity entity) {
        LOGGER.info("Creando un Comentario nuevo");
        em.persist(entity);
        LOGGER.info("Comentario creado");
        return entity;
    }
    
      public ComentariosEntity update(ComentariosEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Comentario con id={0}", entity.getId());
        return em.merge(entity);
    }
      
        public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Comentario con id={0}", id);
        ComentariosEntity entity = em.find(ComentariosEntity.class, id);
        em.remove(entity);
    }

    
}
