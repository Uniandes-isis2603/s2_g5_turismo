/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.BlogEntity;
import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
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
public class ComentarioLogic {
    private static final Logger LOGGER = Logger.getLogger(ComentarioLogic.class.getName());
     
    
     @Inject
    BlogLogic BlogLogic;
    
     @Inject
    private ComentarioPersistence persistence;
    
     
     public ComentarioEntity createComentario(ComentarioEntity entity,long BlogId) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Comentario");
       if (entity.getComentario() != null && !entity.getComentario().isEmpty()){
           BlogEntity blog = BlogLogic.getBlogs(BlogId);
       if (blog != null){
            List<ComentarioEntity> com = blog.getComentarios();
            com.add(entity);
            blog.setComentarios(com);
            BlogLogic.updateBlog(blog);
        // Invoca la persistencia para crear el Comentario
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación Comentarios");
        return entity;
        }
        else
        {
        throw new BusinessLogicException("No existe el blog al que se desea agregar el Comentario");
        }
       }
       
       else 
       {
       throw new BusinessLogicException(" no puede ser vacio Comentario");
       }
    }
     
      public List<ComentarioEntity> getComentarios(long BlogId) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los Comentarios");
       BlogEntity blog = BlogLogic.getBlogs(BlogId);
        List<ComentarioEntity> com = blog.getComentarios();
        LOGGER.info("Termina proceso de consultar todos los Comentarios");
        return com;
    }
      
        public ComentarioEntity getComentarios(Long id, long BlogId) throws BusinessLogicException 
    {   BlogEntity blog = BlogLogic.getBlogs(BlogId);
        List<ComentarioEntity> com = blog.getComentarios();
        ComentarioEntity encontrado = null;
        Iterator e = com.iterator();
        boolean finalizar = false;
        while(e.hasNext() && !finalizar)
        {
            ComentarioEntity a = (ComentarioEntity) e.next();
        if(a.getId() == id)
        {
            encontrado = a;
            finalizar = true;
        }
        
        }
        if(encontrado == null)
        {
       
            throw new BusinessLogicException("el comentario no existe");

        }
            
            return encontrado;
        
        
        
    }
        
        public ComentarioEntity updateComentario(ComentarioEntity entity) throws BusinessLogicException  {
         if (entity.getComentario() != null && !entity.getComentario().isEmpty()){
        return persistence.update(entity);}
         
         else 
         {
         throw new BusinessLogicException(" no puede ser vacio Comentario");
         }
    }
        
        public void deleteComentario(ComentarioEntity entity, long BlogId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar Comentario con id={0}", entity.getId());
       BlogEntity update = BlogLogic.getBlogs(BlogId);
       List<ComentarioEntity> comen = update.getComentarios();
       comen.remove(entity);
       update.setComentarios(comen);
       BlogLogic.updateBlog(update);
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar Comentario con id={0}", entity.getId());
    }
}
