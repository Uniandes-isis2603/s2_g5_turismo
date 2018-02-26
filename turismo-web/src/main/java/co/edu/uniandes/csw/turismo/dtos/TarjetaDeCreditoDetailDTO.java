/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s.benitez10
 */
public class TarjetaDeCreditoDetailDTO extends TarjetaDeCreditoDTO
{
    private List<FacturaDTO> facturas; 
    private UsuarioDTO usuario;

    public TarjetaDeCreditoDetailDTO() 
    {
        super();   
    }

   
    public TarjetaDeCreditoDetailDTO(TarjetaDeCreditoEntity entity) 
    {
        super(entity);
        if (entity != null)
        {
           facturas = new ArrayList<>();
            for (FacturaEntity entityFacturas : entity.getFacturas())
            {
                facturas.add(new FacturaDTO(entityFacturas));
                
            }
        }
        if (entity.getUsuario() != null)
        {
            this.usuario = new UsuarioDTO(entity.getUsuario());
        }

    }
    
     @Override
    public TarjetaDeCreditoEntity toEntity()
    {
        TarjetaDeCreditoEntity entity = super.toEntity();
        if (facturas != null) {
            List<FacturaEntity> facturaEntity = new ArrayList<>();
            for (FacturaDTO dtofactura : facturas) {
                facturaEntity.add(dtofactura.toEntity());
            }
            entity.setFacturas(facturaEntity);
        }

        return entity;
    }

    
}
