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
    
    private Long id;
    private Long Costo;
/*
    Constructor por defecto
    */
    public FacturaDTO() {
        
    }
    
    public FacturaDTO(FacturaEntity entity) 
    {
        if(entity != null)
        {
        this.Costo = entity.getCosto();
        this.id = entity.getId();
        }
        
    }
     public FacturaEntity toEntity() {
        FacturaEntity entity = new FacturaEntity();
        entity.setCosto(this.getCosto());
        entity.setId(this.getId());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
/*
     Retorna el Costo de la factura
     */
    public long getCosto() {
        return Costo;
    }
    /*
     modifica el costo de la factura
     */

    public void setCosto(long Costo) {
        this.Costo = Costo;
    }
     
   
   
    
}
