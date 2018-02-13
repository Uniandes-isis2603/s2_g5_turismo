/*
 * PrenfeciasDTO
 * Objeto de transferencia de datos de preferencias.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   { 
 *      "tiposPlan":[string,string,string]                     
 *   }
 * </pre>
 */
package co.edu.uniandes.csw.turismo.dtos;

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
    private String[] tiposPlan;
    
    //CONSTRUCTOR
    /**
     * Constructor por defecto
     */
    public PreferenciasDTO(){}

    //GETTERS AND SETTERS
    
    /**
     * @return the tiposPlan
     */
    public String[] getTiposPlan() 
    {
        return tiposPlan;
    }

    /**
     * @param tiposPlan the tiposPlan to set
     */
    public void setTiposPlan(String[] tiposPlan)
    {
        this.tiposPlan = tiposPlan;
    }
}
