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
    private Boolean completado;
    
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
        this.completado = entity.getCompletado();
        }
        
    }
     public PaqueteTuristicoEntity toEntity() {
        PaqueteTuristicoEntity entity = new PaqueteTuristicoEntity();
        entity.setId(this.getId());
        entity.setCompletado(this.isCompletado());
        return entity;
    }
    /**
     * @return El identificador del paquete
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id Identificador del paquete
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }
    
}
