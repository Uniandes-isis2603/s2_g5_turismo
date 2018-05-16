/**
 * FacturaDetailDTO
 * Objeto de transferencia de datos de planes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "costo": number,
 *      "paquetetur":  {
 *                          "id": number
 *                       },
 *      "tarjetadecredito":{
 *                           "name": String,
 *                           "numero": number,
 *                           "CDV":numero
 *                          }            
 *   }
 * Por ejemplo una plan detallado se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 1,
 *      "costo": 1000,
 *      "paquetetur":  {
 *                          "id":1,
 *                       },
 *      "tarjetaCredito":{
 *                           "name": "sebastian",
 *                           "numero":1234567891011324,
 *                           "CVD":213
 *                          }            
 *   }
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;

/**
 *
 * @author s.benitez10
 */
public class FacturaDetailDTO extends FacturaDTO 
{
    /**
     * relacion que representa la union con el paquete turistico
     */
     private PaqueteTuristicoDTO paquetetur;
     /**
      * relacion que representa la union con la tarjeta
      */
     private TarjetaDeCreditoDTO tarjetaCredito;
    
    /**
     * constructor por defecto
     */
    public FacturaDetailDTO()    
    {
        //no hace nada 
    }
    /**
     * constructor apartir de una facturaEntity
     * @param entity 
     */
    public FacturaDetailDTO(FacturaEntity entity)
    {   
        super(entity);
        if( entity.getPaqueteturistico() != null)
        {
            paquetetur = new PaqueteTuristicoDTO(entity.getPaqueteturistico());
        }
        if(entity.getTarjetadecredito() != null)
        {
            tarjetaCredito= new TarjetaDeCreditoDTO(entity.getTarjetadecredito());
        }
        
    }
    /**
     * metodo que se encarga de pasar de DTO a entity
     * @return facturaEntity
     */
    @Override
    public FacturaEntity toEntity()
    { 
       FacturaEntity entityFactura = super.toEntity();
       
       if(this.getPaquetetur() != null)
       {
           entityFactura.setPaqueteturistico(this.getPaquetetur().toEntity());
       }
       if (this.getTarjetaCredito() != null)
       {
           entityFactura.setTarjetadecredito(this.getTarjetaCredito().toEntity());
           
       }
         return entityFactura;
     
    }
    /**
     * 
     * @return paqueteTuristicoDTO 
     */
    public PaqueteTuristicoDTO getPaquetetur() {
        return paquetetur;
    }
    /**
     * Establece el paqueteTuristicoDTO a la factura
     * @param paquetetur 
     */
    public void setPaquetetur(PaqueteTuristicoDTO paquetetur) {
        this.paquetetur = paquetetur;
    }
    /**
     * 
     * @return tarjetaDeCredito DTO 
     */
    public TarjetaDeCreditoDTO getTarjetaCredito() {
        return tarjetaCredito;
    }
    /**
     * establece la tarjeta de Credito relacionada al DTO
     * @param tarjetaCredito 
     */
    public void setTarjetaCredito(TarjetaDeCreditoDTO tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }
    
}
