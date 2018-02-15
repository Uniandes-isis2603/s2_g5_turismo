/**
 * GuiaDetailDTO
 * Objeto de transferencia de datos de guias.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   { 
 *      "idGuia": number,
 *      "nombreGuia: string,
 *      "idiomaGuia": string,                     
 *   }
 * Por ejemplo un guia se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "idGuia": 1,
 *      "nombreGuia: papulinoGuia,
 *      "idiomaGuia": italiano,   
 *   }
 *
 * </pre>
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 * Objeto de transferencia de los guias
 * @author jc.montoyar
 */
public class GuiaDTO
{
    //ATRIBUTOS

    /**
     * Atriburo que modela el nombre del guia
     */
    private String nombreGuia;

    /**
     * Atriburo que modela el idioma del guia
     */
    private String idiomaGuia;

    /**
     * Atriburo que modela el id del guia
     */
    private String idGuia;

    //CONSTRUCTOR
    /**
     * Constructor por defecto
     */
    public GuiaDTO() {}

    //METODOS
    /**
     * @return the nombreGuia
     */
    public String getNombreGuia()
    {
        return nombreGuia;
    }

    /**
     * @param nombreGuia the nombreGuia to set
     */
    public void setNombreGuia(String nombreGuia)
    {
        this.nombreGuia = nombreGuia;
    }

    /**
     * @return the idiomaGuia
     */
    public String getIdiomaGuia() 
    {
        return idiomaGuia;
    }

    /**
     * @param idiomaGuia the idiomaGuia to set
     */
    public void setIdiomaGuia(String idiomaGuia) 
    {
        this.idiomaGuia = idiomaGuia;
    }

    /**
     * @return the idGuia
     */
    public String getIdGuia() 
    {
        return idGuia;
    }

    /**
     * @param idGuia the idGuia to set
     */
    public void setIdGuia(String idGuia)
    {
        this.idGuia = idGuia;
    }
}
