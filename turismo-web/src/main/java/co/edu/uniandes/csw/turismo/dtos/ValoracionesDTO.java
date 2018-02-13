/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 *
 * @author jf.gutierrez13
 */
public class ValoracionesDTO 
{
    private double calificacion;
    private String comentario;
    
    /**
     * Constructor por defecto
     */
    public ValoracionesDTO()
    {
        
    }
    
    public ValoracionesDTO(double calificacion, String comentario)
    {
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
