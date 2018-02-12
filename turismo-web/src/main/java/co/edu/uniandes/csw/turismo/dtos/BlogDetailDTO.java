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
public class BlogDetailDTO  extends BlogDTO
{
    
    public BlogDetailDTO(String tema, String descripcion, int likes, T2 archivos) {
        super(tema, descripcion, likes, archivos);
    }
    
}
