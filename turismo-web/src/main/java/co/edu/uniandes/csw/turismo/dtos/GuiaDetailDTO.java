/*
 * GuiaDetailDTO
 * Objeto de transferencia de datos de guias.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "idGuia": number,
 *      "nombreGuia: string,
 *      "idiomaGuia": string,
 *      "planesGuia":[{
 *                     "idPlan": number,
 *                     "nombrePlan: string,
 *                     "descripcion": string,
 *                     "pais": string,
 *                     "ciudad": string,
 *                     "longitud": number,
 *                     "latitud": number,
 *                     "duracion":number,
 *                     "restricciones": string,
 *                     "archivo": string,
 *                     "precio": number,
 *                     "cantidadPersonas": number
 *                    }]                     
 *   }
 * </pre>
 */
package co.edu.uniandes.csw.turismo.dtos;

import java.util.ArrayList;

/**
 * Objeto de transferencia detallado de los guias
 * @author jc.montoyar
 */
public class GuiaDetailDTO extends GuiaDTO
{
    
    //ATRIBURTOS

    /**
     * Modela los planes a los cuales el guia esta asociado
     */
    private ArrayList<PlanDTO> planesGuia;

    //Constructor 
    /**
     * Constructor por defecto
     */
    public GuiaDetailDTO(){}

    //METODOS
    /**
     * @return the planesGuia
     */
    public ArrayList<PlanDTO> getPlanesGuia() 
    {
        return planesGuia;
    }

    /**
     * @param planesGuia the planesGuia to set
     */
    public void setPlanesGuia(ArrayList<PlanDTO> planesGuia)
    {
        this.planesGuia = planesGuia;
    }
}
