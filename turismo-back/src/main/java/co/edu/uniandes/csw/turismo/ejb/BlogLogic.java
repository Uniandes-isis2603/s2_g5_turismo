/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.BlogEntity;
import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.BlogPersistence;
import co.edu.uniandes.csw.turismo.persistence.ComentarioPersistence;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author lf.rivera10
 */

@Stateless
public class BlogLogic {
    private static final Logger LOGGER = Logger.getLogger(BlogLogic.class.getName());
     
    
     
    
    
     @Inject
    private BlogPersistence persistence;
     
     @Inject
    private PlanLogic planLogic;
     
     @Inject
    private ComentarioPersistence persistenceComentario;
    
     
     public BlogEntity createBlog(BlogEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Blog");
        
        if(((entity.getDescripcion() == null || entity.getTema() == null) || entity.getTema().isEmpty())  || entity.getDescripcion().isEmpty() ){
            throw new BusinessLogicException("tema y descripcion no pueden ser nulos");
        }
        else 
        {

            if (persistence.find(entity.getId()) != null)
            {
                throw new BusinessLogicException("Ya existe este blog");
            }
            // Invoca la persistencia para crear el Blog
            if(entity.getPlanes() !=null){
                List<PlanEntity> plan = entity.getPlanes();
                
                for (PlanEntity buscado : plan) {
                    PlanEntity real = planLogic.getPlan(buscado.getId());
                    if(real == null)
                    {
                        throw new BusinessLogicException("No existe el plan");
                    }
                }
}
            persistence.create(entity);
            
            LOGGER.info("Termina proceso de creación blogs");
            return entity;
        }
        
     }
     
      public List<BlogEntity> getBlogs() {
        LOGGER.info("Inicia proceso de consultar todos los blogs");
        List<BlogEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los blogs");
        return editorials;
    }
      
        public BlogEntity getBlogs(Long id) throws BusinessLogicException {
            BlogEntity a = persistence.find(id);
            if (a!= null){
            return a;
            }
            else
            {
             throw new BusinessLogicException("el blog no existe");
            }
    }
        
        public BlogEntity updateBlog(BlogEntity entity) throws BusinessLogicException  {
            if(persistence.find(entity.getId()) == null){ throw new BusinessLogicException("No existe el blog");}
            else if(entity.getDescripcion() == null || entity.getTema() == null || entity.getTema().isEmpty()  || entity.getDescripcion().isEmpty()){ throw new BusinessLogicException("tema y descripcion no pueden ser nulos");}
            if(persistence.find(entity.getId()).getComentarios() != null){
               List<ComentarioEntity> temp = persistence.find(entity.getId()).getComentarios();
               entity.setComentarios(temp);}
               
               if(entity.getPlanes() !=null){ 
                 List<PlanEntity> plan = entity.getPlanes();
      
                    for (PlanEntity buscado : plan) {
                        PlanEntity real = planLogic.getPlan(buscado.getId());
                        if(real == null)
                        {
                            throw new BusinessLogicException("No existe el plan");
                        }   }
}
               
               
               
                return persistence.update(entity);
                
                
            
            
         
        
       
        
            
           
        
    }
        
        public void deleteBlog(BlogEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar blog con id={0}", entity.getId());    
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar libro con id={0}", entity.getId());
    }
}
