/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

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
public class ComentariosDTO {
    private String comentario;
    
      /**
     * Constructor por defecto
     */
    public ComentariosDTO(){
        
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
    
}
