
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;


import java.util.List;

/**
 *
 * @author lf.rivera10
 */
public class BlogDTO {
    private String tema;
    private String descripcion;
    private int likes; 
    private List<ComentariosDTO> comentarios;

    public BlogDTO(String tema, String descripcion, int likes, List<ComentariosDTO> comentarios) {
        this.tema = tema;
        this.descripcion = descripcion;
        this.likes = likes;
        this.comentarios = comentarios;
    }

    public List<ComentariosDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentariosDTO> comentarios) {
        this.comentarios = comentarios;
    }
    
    

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    
    
}
