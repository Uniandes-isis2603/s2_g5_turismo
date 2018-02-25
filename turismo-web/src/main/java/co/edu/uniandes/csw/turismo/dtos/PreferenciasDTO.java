/**
 * PrenfeciasDTO
 * Objeto de transferencia de datos de preferencias.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   { 
 *      "tiposPlan":[string,string,string]                     
 *   }
 * Por ejemplo una preferencia se representa asi:<br>
 * 
 * <pre>
 *   {
 *       "tiposPlan":[Religion, Mirador, Adultos]       
 *   }
 * </pre>
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import java.util.List;

/**
 * Objeto de transferencia de las preferencias(tipos de plan)
 * @author jc.montoyar
 */
public class PreferenciasDTO 
{
    //ATRIBUTOS
    
    /**
     * Atriburo que modela un arreglo tipo string que contiene tipos de plan
     */
    private List<String> tiposPlan;
    
    //CONSTRUCTOR
    /**
     * Constructor por defecto
     */
    public PreferenciasDTO(){}
    
     /**
     * Constructor a partir de la entidad
     * @param prefE  La entidad del la preferencia
     */
    public PreferenciasDTO(PreferenciasEntity prefE)
    {
        if (prefE != null) 
        {
            this.tiposPlan = prefE.getTiposPlan();
        }
    }
    
    /**
     * Método para transformar el DTO a una entidad.
     * @return La entidad de la preferencia asociado.
     */
    public PreferenciasEntity toEntity() 
    {
        PreferenciasEntity prefE = new PreferenciasEntity();
        prefE.setTiposPlan(this.tiposPlan);
        return prefE;
    }

    //GETTERS AND SETTERS
    
    /**
     * @return the tiposPlan
     */
    public List<String> getTiposPlan() 
    {
        return tiposPlan;
    }

    /**
     * @param tiposPlan the tiposPlan to set
     */
    public void setTiposPlan(List<String> tiposPlan)
    {
        this.tiposPlan = tiposPlan;
    }
}
