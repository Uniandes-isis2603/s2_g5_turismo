/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;


import co.edu.uniandes.csw.turismo.entities.BlogEntity;


/**
 ** BlogDTO Objeto de transferencia de datos de Blogs. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "tema": string,
 *      "descripcion": string,
 *      "likes": number,
 *      "id":numbre
 *
 *   }
 * 
 * ejemplo :
 * 
 * {
 "tema": "prueba",
 "descripcion": "esto es una prueba",
 "likes":100,
 "id":1,
 "comentarios":  {},
 
"planes":{}            
}
 * </pre>
 

 * @author lf.rivera10
 */
public class BlogDTO {
    private String tema;
    private String descripcion;
    private int likes; 
    private long id;
 /**
     * Constructor por defecto
     */
    public BlogDTO() {
        }
    
  public BlogDTO(BlogEntity entity) 
    {
        if(entity != null)
        {
        this.descripcion = entity.getDescripcion();
        this.likes = entity.getLikes();
        this.tema = entity.getTema();
        this.id = entity.getId();
        }
        
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    


     public BlogEntity toEntity() {
        BlogEntity entity = new BlogEntity();
        entity.setDescripcion(descripcion);
        entity.setLikes(likes);
        entity.setTema(tema);
        entity.setId(id);
        return entity;
    }   
    
}
