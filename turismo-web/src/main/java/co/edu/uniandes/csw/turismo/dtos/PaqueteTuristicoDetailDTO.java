/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PagoEntity;
import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.PlanAjendadoEntity;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Clase que extiende de {@link ComentariosDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos.
 * 
 * @author dl.avendano
 */
public class PaqueteTuristicoDetailDTO  extends PaqueteTuristicoDTO{
    private List<PagoDTO> pagos; 
    private List<PlanAjendadoDTO> planes; 
    
    public PaqueteTuristicoDetailDTO(PaqueteTuristicoEntity entity) {
        super(entity);
        if (entity != null) {
            pagos = new ArrayList<>();
            planes = new ArrayList<>();
            for (PagoEntity entityPagos : entity.getPagos()) {
                pagos.add(new PagoDTO(entityPagos));
            }
            for (PlanAjendadoEntity entityPlanes : entity.getPlanes()) {
                planes.add(new PlanAjendadoDTO(entityPlanes));
            }
        }

    }

     @Override
    public PaqueteTuristicoEntity toEntity() {
        PaqueteTuristicoEntity entity = super.toEntity();
        if (planes != null) {
            List<PlanAjendadoEntity> planesEntity = new ArrayList<>();
            for (PlanAjendadoDTO dtoPlan : planes) {
                planesEntity.add(dtoPlan.toEntity());
            }
            entity.setPlanes(planesEntity);
        }
        if (pagos != null) {
            List<PagoEntity> pagosEntity = new ArrayList<>();
            for (PagoDTO dtoPago : pagos) {
                pagosEntity.add(dtoPago.toEntity());
            }
            entity.setPagos(pagosEntity);
        }

        return entity;
    }
    
    public List<PagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoDTO> Pagos) {
        this.pagos = Pagos;
    }

    public List<PlanAjendadoDTO> getPlanes() {
        return planes;
    }

    public void setPlanes(List<PlanAjendadoDTO> Planes) {
        this.planes = Planes;
    }
    
}
