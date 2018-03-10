/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.PagoEntity;
import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PagoPersistence;
import co.edu.uniandes.csw.turismo.persistence.PaqueteTuristicoPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author dl.avendano
 */
@Stateless
public class PaqueteTuristicoLogic {
     private static final Logger LOGGER = Logger.getLogger(PaqueteTuristicoLogic.class.getName());

     Date fechaActual = new Date();
    @Inject
    private PagoPersistence persistencePago;
    
    @Inject
    private PaqueteTuristicoPersistence persistence;
    
    public PaqueteTuristicoEntity getPaquete(Long id) {
        return persistence.find(id);
    }
    
    public PaqueteTuristicoEntity createPaqueteTuristico(PaqueteTuristicoEntity entity) throws BusinessLogicException{
        
        List <PagoEntity> pagos = entity.getPagos();
        List <PlanAgendadoEntity> planes = entity.getPlanes();
        for(int i=0; i<pagos.size();i++)
        {
        if (pagos.size()!=planes.size())
            throw new BusinessLogicException("El numero de pagos no coincide con el numero de planes en el paquete \"");
        if (planes.get(i).getFecha().before(fechaActual))
            throw new BusinessLogicException("Un plan no tiene una fecha valida \"");
            for(int j=0;j<pagos.size();j++)
            {
                 if (i != j && planes.get(i).getFecha().equals(planes.get(j).getFecha()))
                     throw new BusinessLogicException("Hay dos planes con la misma fecha en el paquete \"");
            }
        }
        return persistence.create(entity);
    }
    public List<PaqueteTuristicoEntity> getPaquetes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los paquetes");
        return persistence.findAll();
    }
    
    public PaqueteTuristicoEntity updatePaquete(PaqueteTuristicoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un paquete ");
        return persistence.update(entity);
    }
    public void deletePaqueteTuristico(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un paquete ");
        persistence.delete(id);
    }
    
   public List<PagoEntity> listPagos(Long IdPaquete) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los pagos del paquete con id = {0}", IdPaquete);
        return getPaquete(IdPaquete).getPagos();
    }
    
    public PagoEntity getPago(Long IdPaquete, Long IdPago) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un pago del paquete con id = {0}", IdPaquete);
        List<PagoEntity> list = getPaquete(IdPaquete).getPagos();
        PagoEntity pagosEntity = new PagoEntity();
        pagosEntity.setId(IdPago);
        int index = list.indexOf(pagosEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    
    public PagoEntity addPago(Long IdPaquete, Long IdPago) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un pago al paquete con id = {0}", IdPaquete);
        PaqueteTuristicoEntity paqueteEntity = getPaquete(IdPaquete);
        PagoEntity pagosEntity = new PagoEntity();
        pagosEntity.setId(IdPago);
        paqueteEntity.getPagos().add(pagosEntity);
        return getPago(IdPaquete, IdPago);
    }

   
    public List<PagoEntity> replacePagos(Long IdPaquete, List<PagoEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un pago del paquete con id = {0}", IdPaquete);
        PaqueteTuristicoEntity paqueteEntity = getPaquete(IdPaquete);
        paqueteEntity.setPagos(list);
        return paqueteEntity.getPagos();
        }

    
    public void removePago(Long IdPaquete, Long IdPago) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un pago del paquete con id = {0}", IdPaquete);
        PaqueteTuristicoEntity entity = getPaquete(IdPaquete);
        PagoEntity pagosEntity = new PagoEntity();
        pagosEntity.setId(IdPago);
        entity.getPagos().remove(pagosEntity);
    }
    
    public List<PlanAgendadoEntity> listPlanes(Long IdPaquete) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los planes del paquete con id = {0}", IdPaquete);
        return getPaquete(IdPaquete).getPlanes();
    }
    
    
    public PlanAgendadoEntity getPlan(Long IdPaquete, Long IdPlanAgendado) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un plan del paquete con id = {0}", IdPaquete);
        List<PlanAgendadoEntity> list = getPaquete(IdPaquete).getPlanes();
        PlanAgendadoEntity planesEntity = new PlanAgendadoEntity();
        planesEntity.setId(IdPlanAgendado);
        int index = list.indexOf(planesEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    
    public PlanAgendadoEntity addPlan(Long IdPaquete, Long IdPlanAgendado) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un plan al paquete con id = {0}", IdPaquete);
        PaqueteTuristicoEntity paqueteEntity = getPaquete(IdPaquete);
        PlanAgendadoEntity planesEntity = new PlanAgendadoEntity();
        planesEntity.setId(IdPlanAgendado);
        paqueteEntity.getPlanes().add(planesEntity);
        return getPlan(IdPaquete, IdPlanAgendado);
    }

   
    public List<PlanAgendadoEntity> replacePlanes(Long IdPaquete, List<PlanAgendadoEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un plan del paquete con id = {0}", IdPaquete);
        PaqueteTuristicoEntity paqueteEntity = getPaquete(IdPaquete);
        paqueteEntity.setPlanes(list);
        return paqueteEntity.getPlanes();
        }

}
