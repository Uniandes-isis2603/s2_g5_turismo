/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 *
 * @author lf.rivera10
 */
public class BlogDTO {
    private String tema;
    private String descripcion;
    private int likes;
    private T2 archivos;
    
      public BlogDTO(String tema, String descripcion, int likes, T2 archivos) {
        this.tema = tema;
        this.descripcion = descripcion;
        this.likes = likes;
        this.archivos = archivos;
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

    public T2 getArchivos() {
        return archivos;
    }

    public void setArchivos(T2 archivos) {
        this.archivos = archivos;
    }
    
    
}
