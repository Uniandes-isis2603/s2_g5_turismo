/**
 * GuiaDetailDTO
 * Objeto de transferencia de datos de guias.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
       "idGuia": 1,
       "nombreGuia: David,
       "idiomaGuia": Persa                  
     }
 * Por ejemplo un guia detallado se representa asi:<br>
 * 
 * <pre>
 *   {
      "idGuia": 1,
      "nombreGuia: David,
      "idiomaGuia": Persa                
     }
 * </pre>
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.GuiaEntity;

/**
 * Objeto de transferencia detallado de los guias
 * @author jc.montoyar
 */
public class GuiaDetailDTO extends GuiaDTO
{
    /**
     * Constructor por defecto
     */
    public GuiaDetailDTO()
    {
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de la cual se construye el DTO
     */
    public GuiaDetailDTO(GuiaEntity entity) 
    {
        super(entity);
    }
    
    
    /**
     * Transformar el DTO a una entidad
     * @return La entidad que representa el guia.
     */
    @Override
    public GuiaEntity toEntity() 
    {
        return super.toEntity();
    }
}
