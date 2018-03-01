/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.ComentarioPersistence;
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
    private ComentarioPersistence persistence;
    
     
     public ComentarioEntity createComentario(ComentarioEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Comentario");
       if (entity.getComentario() != null && !entity.getComentario().isEmpty()){
        if (persistence.find(entity.getId()) != null) 
        {
            throw new BusinessLogicException("Ya existe este Comentario");
        }
        // Invoca la persistencia para crear el Comentario
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación Comentarios");
        return entity;
       }
       
       else 
       {
       throw new BusinessLogicException(" no puede ser vacio Comentario");
       }
    }
     
      public List<ComentarioEntity> getComentarios() {
        LOGGER.info("Inicia proceso de consultar todos los Comentarios");
        List<ComentarioEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los Comentarios");
        return editorials;
    }
      
        public ComentarioEntity getComentarios(Long id) {
        return persistence.find(id);
    }
        
        public ComentarioEntity updateComentario(ComentarioEntity entity) throws BusinessLogicException  {
         if (entity.getComentario() != null && !entity.getComentario().isEmpty()){
        return persistence.update(entity);}
         
         else 
         {
         throw new BusinessLogicException(" no puede ser vacio Comentario");
         }
    }
        
        public void deleteComentario(ComentarioEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar Comentario con id={0}", entity.getId());    
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar Comentario con id={0}", entity.getId());
    }
}
