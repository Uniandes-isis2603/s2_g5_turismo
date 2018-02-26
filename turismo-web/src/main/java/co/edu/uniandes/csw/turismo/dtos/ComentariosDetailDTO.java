
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;

/**
 *
 * Clase que extiende de {@link ComentarioDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos.
 * 
 * @author lf.rivera10
 */
public class ComentariosDetailDTO extends ComentarioDTO {
    /**
     * Constructor por defecto
     */
    public ComentariosDetailDTO() {
        
        super();
    }
  public ComentarioEntity toEntity()
    { 
       ComentarioEntity entity = super.toEntity();
       return entity;
    }
  
  public ComentariosDetailDTO(ComentarioEntity entity)
  {super(entity);
  
  }

          
          
    
}
