/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;
import co.edu.uniandes.csw.turismo.entities.BlogEntity;
import co.edu.uniandes.csw.turismo.entities.CityEntity;
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
public class BlogPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(BlogPersistence.class.getName());

    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;

     /**
     *
     * @param entity objeto Blog que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public BlogEntity create(BlogEntity entity) {
        LOGGER.info("Creando un Blog nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Blog nuevo");
        return entity;
    }
    
     public List<BlogEntity> findAll() {
        LOGGER.info("Consultando todos los Blogs");
        TypedQuery query = em.createQuery("select u from BlogEntity u", BlogEntity.class);
        return query.getResultList();
    }
     
     public BlogEntity find(Long id) {
        return em.find(BlogEntity.class, id);
    }

    public BlogEntity update(BlogEntity entity) {
         return em.merge(entity);
    }
    
    public void delete(BlogEntity entity) {
        em.remove(entity);
    }
}
