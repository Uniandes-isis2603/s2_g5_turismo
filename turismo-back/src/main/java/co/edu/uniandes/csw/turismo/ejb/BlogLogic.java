/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.BlogEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.BlogPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import static org.springframework.retry.interceptor.RetryInterceptorBuilder.stateless;

/**
 *
 * @author lf.rivera10
 */

@Stateless
public class BlogLogic {
    private static final Logger LOGGER = Logger.getLogger(BlogLogic.class.getName());
     
    
     @Inject
    private BlogPersistence persistence;
    
     
     public BlogEntity createBlog(BlogEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Blog");
       
        if (persistence.find(entity.getId()) != null) 
        {
            throw new BusinessLogicException("Ya existe este blog");
        }
        // Invoca la persistencia para crear el Blog
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación blogs");
        return entity;
    }
     
      public List<BlogEntity> getBlogs() {
        LOGGER.info("Inicia proceso de consultar todos los blogs");
        List<BlogEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los blogs");
        return editorials;
    }
      
        public BlogEntity getBlogs(Long id) {
        return persistence.find(id);
    }
        
        public BlogEntity updateBlog(BlogEntity entity) throws BusinessLogicException  {
        
        return persistence.update(entity);
    }
        
        public void deleteBlog(BlogEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar blog con id={0}", entity.getId());    
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar libro con id={0}", entity.getId());
    }
}
