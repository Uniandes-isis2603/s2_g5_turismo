/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;

/**
 *
 * @author s.benitez10
 */
public class FacturaDetailDTO extends FacturaDTO 
{
     private UsuarioDTO usuario;
     private PaqueteTuristicoDTO paquetetur;
     private TarjetaDeCreditoDTO tarjetaCredito;
    //TODO paquete Turistico private PaqueteTuristicoDTO PaqueteTuristico;
    
    public FacturaDetailDTO()    
    {
        
    }
    public FacturaDetailDTO(FacturaEntity entity)
    {   
        super(entity);
        if( entity.getUsuario() != null)
        {
            usuario =new UsuarioDTO(entity.getUsuario());
        }
        if( entity.getPaqueteturistico() != null)
        {
            paquetetur = new PaqueteTuristicoDTO(entity.getPaqueteturistico())
        }
        if(entity.getTarjetadecredito() != null)
        {
            tarjetaCredito= new TarjetaDeCreditoDTO(entity.getTarjetadecredito());
        }
        
    }
    public FacturaEntity toEntity()
    { 
       FacturaEntity entityFactura = super.toEntity();
       if(this.getUsuario() != null)
       {
           entityFactura.setUsuario(this.getUsuario().toEntity());
       }
       if(this.getPaquetetur() != null)
       {
           entityFactura.setPaqueteturistico(this.getPaquetetur().toEntity());
       }
       if (this.getTarjetaCredito() != null)
       {
           entityFactura.setTarjetadecredito(this.getTarjetaCredito().toEntity());
           
       }
     
    }
    
    
    //TODO: getter and Setters

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

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
