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
 * @author ISIS2603
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PagoEntity;
import java.util.Date;

/**
 *
 * @author dl.avendano
 */
public class PagoDTO {
    /**
     * Atriburo que modela el id del pago
     */
    private long id;
    /**
     * Atriburo que modela el id del plan
     */
    private long idPlan;
    /**
     * Atriburo que modela el el costo del plan endurante el pago
     */
    private double costo;
    
    private Date fechaPlan;

    public Date getFechaPlan() {
        return fechaPlan;
    }

    public void setFechaPlan(Date fechaPlan) {
        this.fechaPlan = fechaPlan;
    }
    
    /**
     * Constructor por defecto
     */
    public PagoDTO(){
        
    }

    public PagoDTO(PagoEntity entity) 
    {
        if(entity != null)
        {
        this.costo = entity.getCostoPlan();
        this.id = entity.getId();
        this.fechaPlan = entity.getFechaPlan();
        }
        
    }
     public PagoEntity toEntity() {
        PagoEntity entity = new PagoEntity();
        entity.setCostoPlan(this.getCosto());
        entity.setId(this.getId());
        entity.setFechaPlan(this.getFechaPlan());
        return entity;
    }
    /**
     * @return El identificador del pago
     */
    public long getId() {
        return id;
    }

    /**
     * @param id Identificador del pago
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return El identificador del plan
     */
    public long getIdPlan() {
        return idPlan;
    }

    /**
     * @param idPlan Identificador del plan
     */
    public void setIdPlan(long idPlan) {
        this.idPlan = idPlan;
    }

    /**
     * @return El costo del plan
     */
    public double getCosto() {
        return costo;
    }
    /**
     * @param costo costo del plan durante el pago
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }
    
}
