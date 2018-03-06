/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;

/**
 * * ComentariosDTO Objeto de transferencia de datos de comentarios. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "comentario: string
 *   }
 * </pre>
 * @author lf.rivera10
 */
public class ComentarioDTO {
    private String comentario;
    private long id;
    
      /**
     * Constructor por defecto
     */
    public ComentarioDTO(){
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return El contenido del comentario
     */
    public String getComentario() {
        return comentario;
    }
 /**
     * @param comentario El nuevo contenido del comentario 
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public ComentarioDTO(ComentarioEntity entity) 
    {
       
       this.comentario = entity.getComentario();
       this.id = entity.getId();
       
                
    }
     public ComentarioEntity toEntity() {
        ComentarioEntity entity = new ComentarioEntity();
        entity.setComentario(comentario);
        entity.setId(id);
        return entity;
    } 
    
}
