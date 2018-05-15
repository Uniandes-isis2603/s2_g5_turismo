/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;

/**
 * facturaDTO Objeto de transferencia de datos de facturas. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number
 *      "costo": number
 *   }
 * </pre>
 * Por ejemplo una factura se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id" : 1
 *      "costo": 1000000
 *   }
 *
 * </pre>
 * @author s.benitez10
 */

public class FacturaDTO  {
    /**
     * representa el id de la factura
     */
    private Long id;
    /**
     * representa el id del costo
     */
    private Long Costo;
    /**
    *Constructor por defecto
    */
    public FacturaDTO() {
        
    }
    /**
     * constructor a partir de un entityFactura
     * @param entity 
     */
    public FacturaDTO(FacturaEntity entity) 
    {
        if(entity != null)
        {
        this.Costo = entity.getCosto();
        this.id = entity.getId();
        }
        
    }
    /**
     * metodo que se encarga de pasar de DTO a entity
     * @return facturaEntity
     */
     public FacturaEntity toEntity() {
        FacturaEntity entity = new FacturaEntity();
        entity.setCosto(this.getCosto());
        entity.setId(this.getId());
        return entity;
    }
     /**
      * 
      * @return el id del DTO 
      */
    public Long getId() {
        return id;
    }
    /**
     * establece el id del facturaDTO
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     *Retorna el Costo de la factura
     */
    public long getCosto() {
        return Costo;
    }
    /**
     * estbalece el costo de la facturaDTO
     * @param Costo 
     */
    public void setCosto(long Costo) {
        this.Costo = Costo;
    }
     
   
   
    
}
