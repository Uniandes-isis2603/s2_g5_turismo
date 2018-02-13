
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
public class BlogDetailDTO  extends BlogDTO
{
    
    public BlogDetailDTO(String tema, String descripcion, int likes, List<ComentariosDTO> comentarios) {
        super(tema, descripcion, likes, comentarios);
    }
    
  
    
}
