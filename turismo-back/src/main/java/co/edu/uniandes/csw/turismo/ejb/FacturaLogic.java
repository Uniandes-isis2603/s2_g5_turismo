/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.FacturaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.benitez10
 */
@Stateless
public class FacturaLogic 
{
     private static final Logger LOGGER = Logger.getLogger(FacturaLogic.class.getName());
     
    @Inject
    private FacturaPersistence persistence;
   
    @Inject
    private TarjetaDeCreditoLogic TarjetaLogic;
    
     public FacturaEntity createFactura(FacturaEntity entity) throws BusinessLogicException 
    {
       
        LOGGER.info("Inicia proceso de creación de la Factura");
       //verifica la regla de negocio de que no pueden haber 2 facturas con el mismo id
        if (persistence.find(entity.getId())!= null) 
        {
            throw new BusinessLogicException("Ya existe una factura con el id" + entity.getId() + "\"");
        }
        if(entity.getCosto() < 0)
        {
            throw new BusinessLogicException("La factura no puede tener un costo negativo o un costo 0. Costo= " + entity.getCosto() );
            
        }
        
//     
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Factura");
        return entity;
    }
     
    public List<FacturaEntity> getFacturas()
    {
        LOGGER.info("Inicia proceso de consultar todos los facturas");
        List<FacturaEntity> facturas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los facturas");
        return facturas;
        
    }
    public FacturaEntity getFactura(Long id) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar Factura con id={0}", id);
        FacturaEntity factura= persistence.find(id);
        if (factura == null) {
            LOGGER.log(Level.SEVERE, "factura con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar factura con id={0}", id);
        return factura;
    }
     public void deleteFactura(Long id)
     {
         LOGGER.log(Level.INFO, "Inicia proceso de Eliminar Factura con id={0}", id);
         persistence.delete(id);
         LOGGER.log(Level.INFO, "Termina proceso de Eliminar factura con id={0}", id);
     };
     public FacturaEntity updateFactura(Long id, FacturaEntity entity) throws BusinessLogicException 
     {
      
        // verifica que la factura no tenga un costo negativo
        if(entity.getCosto() < 0)
        {
            throw new BusinessLogicException("La factura no puede tener un costo negativo o un costo 0. Costo= " + entity.getCosto() );
            
        }
        FacturaEntity newEntity = persistence.update(entity);
        return newEntity;
     }
    
}
