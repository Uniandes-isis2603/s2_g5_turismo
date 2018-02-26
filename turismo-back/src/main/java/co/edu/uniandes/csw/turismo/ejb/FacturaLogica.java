/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.FacturaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.benitez10
 */
@Stateless
public class FacturaLogica 
{
     private static final Logger LOGGER = Logger.getLogger(FacturaLogica.class.getName());
     
    @Inject
    private FacturaPersistence persistence;
    
     public FacturaEntity createTarjetaDeCredito(FacturaEntity entity) throws BusinessLogicException 
    {
        boolean tarjetasiguales = false;
        LOGGER.info("Inicia proceso de creación de la Factura");
       //verifica la regla de negocio de que no pueden haber 2 facturas con el mismo id
        if (persistence.find(entity.getId())!= null) 
        {
            throw new BusinessLogicException("Ya existe una factura con el id" + entity.getId() + "\"");
        }
        List<TarjetaDeCreditoEntity> TarjetasUsuario = entity.getUsuario().getListaTarjetas();
        for(TarjetaDeCreditoEntity a:TarjetasUsuario)
        {
            if(a.getNumero()==entity.getTarjetadecredito().getNumero() && a.getCDV() == entity.getTarjetadecredito().getCDV() && a.getName().compareToIgnoreCase(entity.getTarjetadecredito().getName())== 0)
            {
                tarjetasiguales = true;
            }
        }
        // verifica la relga de negocio de que la factura sea pagada por una de las tarjetas del usuario
        if(!tarjetasiguales)
        {
            throw new BusinessLogicException("la  factura no se puede pagar porque el usuario no posee esa tarjeta" );
        }
        // Invoca la persistencia para crear la tarjeta
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Factura");
        return entity;
    }
    
}
