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
 * @author jf.gutierrez13
 */
@Entity
public class ValoracionesEntity extends BaseEntity implements Serializable
{
    private Double calificacion;
    private String comentario;

    /**
     * @return Retorna la calificacion de la valoracion
     */
    public Double getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion La nueva calificacion de la valoracion
     */
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return Retorna el comentario de la valoracion
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario El nuevo comentario de la valoracion
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
