/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;


import java.util.List;

/**
 ** BlogDTO Objeto de transferencia de datos de Blogs. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "tema": string,
 *      "descripcion: string,
 *      "likes": number
 *      "comentarios": list
 *   }
 * </pre>
 

 * @author lf.rivera10
 */
public class BlogDTO {
    private String tema;
    private String descripcion;
    private int likes; 
    private List<ComentariosDTO> comentarios;
 /**
     * Constructor por defecto
     */
    public BlogDTO() {
        
    }
 /**
     * @return la lista de comentarios
     */
    public List<ComentariosDTO> getComentarios() {
        return comentarios;
    }
/**
     * @param comentarios comentarios nuevos del blog
     */
    public void setComentarios(List<ComentariosDTO> comentarios) {
        this.comentarios = comentarios;
    }
    
    
 /**
     * @return tema del blog
     */
    public String getTema() {
        return tema;
    }
/**
     * @param tema nuevo tema del blog
     */
    public void setTema(String tema) {
        this.tema = tema;
    }
 /**
     * @return descripcion del blog
     */
    public String getDescripcion() {
        return descripcion;
    }
/**
     * @param descripcion nueva descripcion del blog
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 /**
     * @return likes del blog
     */
    public int getLikes() {
        return likes;
    }
/**
     * @param likes nueva cantidad de likes del blog
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    
    
}