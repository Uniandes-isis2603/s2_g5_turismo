/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author lf.rivera10
 */
@Entity
public class BlogEntity extends BaseEntity implements Serializable {
    
    private String tema;
    private String descripcion;
    private int likes; 

    
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
    
    
    
}
