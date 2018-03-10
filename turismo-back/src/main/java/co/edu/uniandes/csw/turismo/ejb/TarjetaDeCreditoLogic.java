/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.TarjetaDeCreditoPersistence;
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
public class TarjetaDeCreditoLogic 
{
    private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoLogic.class.getName());
     
    @Inject
    private TarjetaDeCreditoPersistence persistence;
    
    public TarjetaDeCreditoEntity createTarjetaDeCredito(TarjetaDeCreditoEntity entity) throws BusinessLogicException 
    {
        LOGGER.info("Inicia proceso de creación de TarjetaDeCredito");
      
        // Verifica la regla de negocio que dice que una tarjeta debe tener un CDV de 3 digitos
        if(Long.toString(entity.getCDV()).length() != 3)
        {
            throw new BusinessLogicException("la tarjeta de credito tiene un CDV mayor o menor a 3 digitos " + entity.getCDV() + "\"");
        }
        // Verifica la regla de negocio que dice que una tarjeta debe tener 16 digitos en su numero
        if(Long.toString(entity.getNumero()).length() != 16)
        {
             throw new BusinessLogicException("la tarjeta de credito tiene un numero mayor o menor a 16 digitos " + entity.getNumero() + "\"");
        }
        // Verifica la regla de negocio que dice que no puede haber dos tarjetas de credito con el mismo numero
//        if (persistence.findByNumber(entity.getNumero()) != null) 
//        {
//            throw new BusinessLogicException("Ya existe una tarjeta de credito con el numero" + entity.getNumero() + "\"");
//        }
        //Invoca la persistencia para crear la tarjeta
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de TarjetaDeCredito");
        return entity;
    }
    public List<TarjetaDeCreditoEntity> getTrajetasDeCredito()
    {
        LOGGER.info("Inicia proceso de consultar todos los libros");
        List<TarjetaDeCreditoEntity> tarjetas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los libros");
        return tarjetas;
        
    }
     public TarjetaDeCreditoEntity getTrajetaDeCredito(Long id) 
     {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar Tarjeta con id={0}", id);
        TarjetaDeCreditoEntity tarjeta= persistence.find(id);
        if (tarjeta == null) {
            LOGGER.log(Level.SEVERE, "tarjeta con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar tarjeta con id={0}", id);
        return tarjeta;
    }
     
     public TarjetaDeCreditoEntity getTrajetaDeCreditoNumero(Long id, Long numero) 
     {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar Tarjeta con numero}", numero);
        TarjetaDeCreditoEntity tarjeta= persistence.findByNumber(id,numero);
        if (tarjeta == null) {
            LOGGER.log(Level.SEVERE, "tarjeta con el id {0} no existe",numero);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar tarjeta con numero}",numero);
        return tarjeta;
    }
     
     public void deleteTarjetaDeCredito(Long id,Long numero)
     {
        TarjetaDeCreditoEntity tarjeta= persistence.findByNumber(id,numero);
        if (tarjeta == null) 
        {
            LOGGER.log(Level.SEVERE, "tarjeta con el id {0} no existe",numero);
        }
        else
        {
         LOGGER.log(Level.INFO, "Inicia proceso de Eliminar Tarjeta con numero", numero);
         persistence.delete(tarjeta.getId());
         LOGGER.log(Level.INFO, "Termina proceso de Eliminar tarjeta con numero", numero);
        }   
     };
     
     public TarjetaDeCreditoEntity updateTarjetaDeCredito(Long id, TarjetaDeCreditoEntity entity) throws BusinessLogicException 
     {
         if(Long.toString(entity.getCDV()).length() != 3)
        {
            throw new BusinessLogicException("la tarjeta de credito tiene un CDV mayor o menor a 3 digitos" + entity.getCDV() + "\"");
        }
        // Verifica la regla de negocio que dice que una tarjeta debe tener 16 digitos en su numero
        if(Long.toString(entity.getNumero()).length() != 16)
        {
             throw new BusinessLogicException("la tarjeta de credito tiene un numero mayor o menor a 16 digitos" + entity.getNumero() + "\"");
        }
        entity.setId(id);
        TarjetaDeCreditoEntity newEntity = persistence.update(entity);
        return newEntity;
     }
    
}
