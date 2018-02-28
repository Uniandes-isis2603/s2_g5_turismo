/**
 * GuiaDetailDTO
 * Objeto de transferencia de datos de guias.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "idGuia": 1,
 *      "nombreGuia: David,
 *      "idiomaGuia": Persa,
 *      "planGuia":{
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
 *                    }                   
 *   }
 * Por ejemplo un guia detallado se representa asi:<br>
 * 
 * <pre>
 *   {
 *      "idGuia": 1,
 *      "nombreGuia: David,
 *      "idiomaGuia": Persa,
 *      "planGuia":{
 *                     "idPlan": 1,
 *                     "nombrePlan: Visita a Monserrate,
 *                     "descripcion": "Ir a monserrate subiendo por teleferico",
 *                     "pais": "Colombia",
 *                     "ciudad": "Bogota",
 *                     "longitud": -74.057615,
 *                     "latitud": 4.606492,
 *                     "duracion":180,
 *                     "restricciones": Menores deben ir a compañados,
 *                     "archivo": imagenLink,
 *                     "precio": 20.000,
 *                     "cantidadPersonas": 9999
 *                    }                   
 *   }
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
    
    //ATRIBUTOS

    ///**
     //* Modela el plan asociado al guia
     //*/
    //private PlanDTO planGuia;

    //Constructor 
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
    public GuiaDetailDTO(GuiaEntity entity) {
        super(entity);
        //if (entity.getPlanGuia()!= null)
        //{
           // this.planGuia = new PlanDTO(entity.getPlanGuia());
        //} 
        //else
        //{
         //   entity.setPlanGuia(null);
        //}
    }
    
    
    /**
     * Transformar el DTO a una entidad
     * @return La entidad que representa el guia.
     */
    @Override
    public GuiaEntity toEntity() 
    {
        GuiaEntity guiaE = super.toEntity();
        //if (this.getPlanGuia() != null)
        //{
         //   guiaE.setPlanGuia(this.getPlanGuia().toEntity());
        //}
        return guiaE;
    }

    //METODOS
    ///**
     //* @return the planGuia
     //*/
   // public PlanDTO getPlanGuia() 
    //{
     //   return planGuia;
   // }

    ///**
     //* @param planesGuia the planesGuia to set
     //*/
    //public void setPlanGuia(PlanDTO planesGuia)
    //{
     //   this.planGuia = planesGuia;
    //}
}
