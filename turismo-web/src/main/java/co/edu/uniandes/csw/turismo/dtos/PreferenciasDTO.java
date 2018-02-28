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
 * Objeto de transferencia de las preferencias(tipos de plan)
 * @author jc.montoyar
 */
public class PreferenciasDTO 
{
    //ATRIBUTOS
    
    /**
     * Modela el id
     */
    private Long id;
    /**
     * Atriburo que modela un arreglo tipo string que contiene tipos de plan
     */
    private String tipoPlan;
    
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
            this.tipoPlan = prefE.getTipoPlan();
            this.id = prefE.getId();
        }
    }

    /**
     * @return id 
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Cambia el id por el dado por parametro
     * @param id 
     */
    public void setId(Long id) 
    {
        this.id = id;
    }
    
    /**
     * Método para transformar el DTO a una entidad.
     * @return La entidad de la preferencia asociado.
     */
    public PreferenciasEntity toEntity() 
    {
        PreferenciasEntity prefE = new PreferenciasEntity();
        prefE.setTipoPlan(this.tipoPlan);
        prefE.setId(this.id);
        return prefE;
    }

    //GETTERS AND SETTERS
    
    /**
     * @return the tipoPlan
     */
    public String getTipoPlan() 
    {
        return tipoPlan;
    }

    /**
     * @param tipoPlan the tipoPlan to set
     */
    public void setTipoPlan(String tipoPlan)
    {
        this.tipoPlan = tipoPlan;
    }
}
