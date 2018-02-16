
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

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

    
    private List<ComentariosDTO> comentarios;


    /**
     * Constructor por defecto
     */
    public BlogDetailDTO() {
        super();
    }
    
    /**
     * @return La lista de comentarios del blog
     */
    public List<ComentariosDTO> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios La lista de comentarios del blog actualizada
     */
    public void setComentarios(List<ComentariosDTO> comentarios) {
        this.comentarios = comentarios;
    }
    
}
