/**
 * PrenfeciasDTO
 * Objeto de transferencia de datos de preferencias.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   { 
 *      "tiposPlan":[string,string,string]                     
 *   }
 * Por ejemplo una preferencia detallada se representa asi:<br>
 * 
 * <pre>
 *   {
 *       "tiposPlan":[Religion, Mirador, Adultos]       
 *   }
 * </pre>
 */
package co.edu.uniandes.csw.turismo.dtos;

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
        
    }
}
