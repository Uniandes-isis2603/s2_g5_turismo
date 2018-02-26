
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.BlogEntity;
import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import java.util.Iterator;
import java.util.List;

/**
 *
*
 * Clase que extiende de {@link BlogDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. 
 *
 * @author lf.rivera10
 */
public class BlogDetailDTO  extends BlogDTO
{

    
    private List<ComentarioDTO> comentarios;
    private List<PlanDTO> planes;


    /**
     * Constructor por defecto
     */
    public BlogDetailDTO() {
        super();
    }
    
    /**
     * @return La lista de comentarios del blog
     */
    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios La lista de comentarios del blog actualizada
     */
    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return La lista de planes del blog
     */
    public List<PlanDTO> getPlanes() {
        return planes;
    }

    
    /**
     * @param planes La lista de planes del blog actualizada
     */
    public void setPlanes(List<PlanDTO> planes) {
        this.planes = planes;
    }
    
  public BlogEntity toEntity()
    { 
       BlogEntity entity = super.toEntity();
       
       Iterator e = comentarios.iterator();
       Iterator e2 = planes.iterator();
       
       List <ComentarioEntity> comen = entity.getComentarios();
       comen.clear();
       
       while (e.hasNext())
       {
       ComentarioDTO nuevo = (ComentarioDTO) e.next();
       
       comen.add(nuevo.toEntity());
               
       }
       
       entity.setComentarios(comen);
       
       List <PlanEntity> pla = entity.getPlanes();
       pla.clear();
       
       while (e2.hasNext())
       {
       PlanDTO nuevo = (PlanDTO) e.next();
       
       pla.add(nuevo.toEntity());
               
       }
       
       return entity;
    }
    
    public BlogDetailDTO(BlogEntity entity) {
        
        super(entity);
        
        List<ComentarioEntity> comen =  entity.getComentarios();
        List<PlanEntity> pla = entity.getPlanes();
        
        Iterator e = comen.iterator();
        Iterator e2 = pla.iterator();
        
        while (e.hasNext())
        {
        ComentarioEntity a = (ComentarioEntity) e.next();
        
        ComentarioDTO nuevo = new ComentarioDTO(a);
        
        
          this.comentarios.add(nuevo);
        }
        
        while (e2.hasNext())
        {
        PlanEntity a = (PlanEntity) e.next();
        
        PlanDTO nuevo = new PlanDTO(a);
        
        
          this.planes.add(nuevo);
        }
        
        
    }
    
}
