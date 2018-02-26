/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author lf.rivera10
 */
@Entity
public class BlogEntity extends BaseEntity implements Serializable {
    
    private String tema;
    private String descripcion;
    private int likes; 
    
    @OneToMany
    @PodamExclude
    private List<ComentarioEntity> comentarios;
    
    @OneToMany
    @PodamExclude
    private List<PlanEntity> planes;
    
    
     /**
     * @return del numero el tema
     */
    
    public String getTema() {
        return tema;
    }
/**
     * @param tema
     */
    public void setTema(String tema) {
        this.tema = tema;
    }
 /**
     * @return la descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
/**
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
/**
     * @return del numero de likes
     */
    public int getLikes() {
        return likes;
    }
/**
     * @param likes
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    
    /**
     * @return los comentarios asociados
     */
    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios
     */
    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }
/**
     * @return los planes asociados
     */
    public List<PlanEntity> getPlanes() {
        return planes;
    }

     /**
     * @param planes
     */
    public void setPlanes(List<PlanEntity> planes) {
        this.planes = planes;
    }
    
    
    
}
