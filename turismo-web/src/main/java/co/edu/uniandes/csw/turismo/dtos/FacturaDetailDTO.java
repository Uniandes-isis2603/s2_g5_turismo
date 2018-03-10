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
//     private UsuarioDTO usuario;
     private PaqueteTuristicoDTO paquetetur;
     private TarjetaDeCreditoDTO tarjetaCredito;
    //TODO paquete Turistico private PaqueteTuristicoDTO PaqueteTuristico;
    
    public FacturaDetailDTO()    
    {
        
    }
    public FacturaDetailDTO(FacturaEntity entity)
    {   
        super(entity);
//        if( entity.getUsuario() != null)
//        {
//            usuario =new UsuarioDTO(entity.getUsuario());
//        }
        if( entity.getPaqueteturistico() != null)
        {
            paquetetur = new PaqueteTuristicoDTO(entity.getPaqueteturistico());
        }
        if(entity.getTarjetadecredito() != null)
        {
            tarjetaCredito= new TarjetaDeCreditoDTO(entity.getTarjetadecredito());
        }
        
    }
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
    
    
    //TODO: getter and Setters

//    public UsuarioDTO getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(UsuarioDTO usuario) {
//        this.usuario = usuario;
//    }
//
    public PaqueteTuristicoDTO getPaquetetur() {
        return paquetetur;
    }

    public void setPaquetetur(PaqueteTuristicoDTO paquetetur) {
        this.paquetetur = paquetetur;
    }

    public TarjetaDeCreditoDTO getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(TarjetaDeCreditoDTO tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }
    
}
