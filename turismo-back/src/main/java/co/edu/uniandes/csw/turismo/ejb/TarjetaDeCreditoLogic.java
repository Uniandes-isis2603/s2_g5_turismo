/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.TarjetaDeCreditoPersistence;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.benitez10
 */
@Stateless
public class TarjetaDeCreditoLogic 
{
    private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoLogic.class.getName());
     
    @Inject
    private TarjetaDeCreditoPersistence persistence;
    
    public TarjetaDeCreditoEntity createTarjetaDeCredito(TarjetaDeCreditoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de TarjetaDeCredito");
        // Verifica la regla de negocio que dice que no puede haber dos tarjetas de credito con el mismo numero
        if (persistence.find(entity.getId()) != null) 
        {
            throw new BusinessLogicException("Ya existe una tarjeta de credito con el numero" + entity.getNumero() + "\"");
        }
        // Invoca la persistencia para crear la city
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de TarjetaDeCredito");
        return entity;
    }
    
}
