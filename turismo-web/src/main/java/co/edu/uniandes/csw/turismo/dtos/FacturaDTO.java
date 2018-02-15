/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 * facturaDTO Objeto de transferencia de datos de facturas. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "ID": number,
 *      "costo": number
 *   
 * </pre>
 * Por ejemplo una factura se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "ID": 10000,
 *      "costo": 1000000
 *   }
 *
 * </pre>
 * @author s.benitez10
 */

public class FacturaDTO  {
    
    private long ID;
    private long Costo;
/*
    Constructor por defecto
    */
    public FacturaDTO() {
        
    }
    
     public FacturaDTO(long ID, long Costo) {
        this.ID = ID;
        this.Costo = Costo;
    }
     /*
     Retorna el ID de la factura
     */

    public long getID() {
        return ID;
    }
/*
    modifica el ID de la factura
     */
    public void setID(long ID) {
        this.ID = ID;
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
