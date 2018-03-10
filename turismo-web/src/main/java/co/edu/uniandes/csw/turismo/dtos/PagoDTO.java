/**
 * PagoDTO Objeto de transferencia de datos de pagos. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "idPlan: number,
 *      "costo": number
 *   }
 * </pre>
 * Por ejemplo una ciudad se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 91852,
 *      "idPlan": 503,
 *      "costo": "53.23"
 *   }
 *
 * </pre>
 * @author dl.avendano
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PagoEntity;

/**
 *
 * @author dl.avendano
 */
public class PagoDTO {
    /**
     * Atriburo que modela el id del pago
     */
    private Long id;
    /**
     * Atriburo que modela el nombre del plan
     */
    private String nombrePlan;
    /**
     * Atriburo que modela el el costo del plan 
     */
    private Double costo;
    
    public PagoDTO()
    {
        
    }
    
    public PagoDTO(PagoEntity entity) 
    {
        if(entity != null)
        {
        this.nombrePlan = entity.getName();
        this.costo = entity.getCosto();
        this.id = entity.getId();
        }
        
    }
     public PagoEntity toEntity() {
        PagoEntity entity = new PagoEntity();
        entity.setCosto(this.getCosto());
        entity.setId(this.getId());
        entity.setName(this.getNombrePlan());
        return entity;
    }
    /**
     * @return El identificador del pago
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id Identificador del pago
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    

    /**
     * @return El costo del plan
     */
    public Double getCosto() {
        return costo;
    }
    /**
     * @param costo costo del plan durante el pago
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
}
