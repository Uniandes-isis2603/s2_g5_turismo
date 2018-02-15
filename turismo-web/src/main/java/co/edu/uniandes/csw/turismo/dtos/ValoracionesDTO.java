/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 *Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "calificacion": number,
 *      "comentario": string
 *   }
 * </pre>
 * Por ejemplo un usuario se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 534682,
 *      "calificacion": 5,
 *      "comentario": Muy buena
 *   }
 *
 * </pre>
 * @author jf.gutierrez13
 */
public class ValoracionesDTO 
{
    private long id;

   
    private double calificacion;
    private String comentario;
    
    /**
     * Constructor por defecto
     */
    public ValoracionesDTO()
    {
        
    }
    
    /**
     * Crea una nueva valoración con la calificacion y comentario recibidos por parámetro
     * @param calificacion
     * @param comentario
     */
    public ValoracionesDTO(double calificacion, String comentario)
    {
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    /**
     * @return La calificacion de la valoracion
     */
    public double getCalificacion() {
        return calificacion;
    }

    /**
     *
     * @param calificacion La nueva calificacion de la valoracion
     */
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return El comentario de la valoracion
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
    
     /**
     * @return id de la valoracion
     */
    public long getId() {
        return id;
    }

    /**
     * @param id nuevo id de la valoracion
     */
    public void setId(long id) {
        this.id = id;
    }
}