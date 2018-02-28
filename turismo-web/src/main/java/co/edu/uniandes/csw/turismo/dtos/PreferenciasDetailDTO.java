/**
 * PrenfeciasDTO
 * Objeto de transferencia de datos de preferencias.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   { 
 *      "tiposPlan": string                    
 *   }
 * Por ejemplo una preferencia detallada se representa asi:<br>
 * 
 * <pre>
 *   {
 *       "id": 1,
 *       "tiposPlan": "mirador"      
 *   }
 * </pre>
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;

/**
 * Objeto de transferencia detallado de las preferencias
 * @author jc.montoyar
 */
public class PreferenciasDetailDTO extends PreferenciasDTO
{
    /**
     * Constructor por defecto
     */
    public PreferenciasDetailDTO()
    {
        super();
    }
    
    public PreferenciasDetailDTO(PreferenciasEntity prefE)
    {
        super(prefE);
    }
    
    @Override
    public PreferenciasEntity toEntity()
    {
        PreferenciasEntity prefE = super.toEntity();
        return prefE;
    }
}
