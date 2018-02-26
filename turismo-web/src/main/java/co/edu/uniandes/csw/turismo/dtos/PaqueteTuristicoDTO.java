/**
 * PaqueteTuristicoDTO Objeto de transferencia de datos del paquete turistico. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *   }
 * </pre>
 * Por ejemplo una ciudad se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 35852
 *   }
 *
 * </pre>
 * @author ISIS2603
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;

/**
 *
 * @author dl.avendano
 */
public class PaqueteTuristicoDTO {
    /**
     * Atriburo que modela el id del paquete turistico
     */
    private Long id;
    
    /**
     * Constructor por defecto
     */
    public PaqueteTuristicoDTO()
    { 
    }
    public PaqueteTuristicoDTO(PaqueteTuristicoEntity entity) 
    {
        if(entity != null)
        {
        this.id = entity.getId();
        }
        
    }
     public PaqueteTuristicoEntity toEntity() {
        PaqueteTuristicoEntity entity = new PaqueteTuristicoEntity();
        entity.setId(this.getId());
        return entity;
    }
    /**
     * @return El identificador del paquete
     */
    public long getId() {
        return id;
    }

    /**
     * @param id Identificador del paquete
     */
    public void setId(long id) {
        this.id = id;
    }
    
}
