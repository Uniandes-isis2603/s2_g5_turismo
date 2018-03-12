/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PlanAgendadoDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.PlanAgendadoLogic;
import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author dl.avendano
 */

    @Path("miPlan")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PlanAgendadoResource {

        @Inject 
        private PlanAgendadoLogic planLogic;
        
        private List<PlanAgendadoDetailDTO> listEntityToDTO(List<PlanAgendadoEntity> entityList) {
        List<PlanAgendadoDetailDTO> list = new ArrayList<>();
        for(PlanAgendadoEntity entity : entityList) {
            list.add(new PlanAgendadoDetailDTO(entity));
        }
        return list;
    }
        @POST
    public PlanAgendadoDetailDTO createPaquete(PlanAgendadoDetailDTO paquete) throws BusinessLogicException {
        PlanAgendadoEntity planAgendadoEntity = paquete.toEntity();
        planAgendadoEntity = planLogic.createPlanAgendado(planAgendadoEntity);
        return new PlanAgendadoDetailDTO(planAgendadoEntity);
    }
    @GET
    public List<PlanAgendadoDetailDTO> getPlanesAgendados() {
        return listEntityToDTO(planLogic.getPlanesAgendados()); 
    }
    @GET
    @Path("{id: \\d+}")
    public PlanAgendadoDetailDTO getPlanAgendado(@PathParam("id") Long id) {
        PlanAgendadoEntity plan = planLogic.getPlanAgendado(id);
        if (plan == null) 
            throw new WebApplicationException("El plan no existe",404);
        return new PlanAgendadoDetailDTO(plan); 
    }
    @PUT
    @Path("{id: \\d+}")
    public PlanAgendadoDetailDTO updatePlanAgendado(@PathParam("id") Long id, PlanAgendadoDetailDTO planAgendado) throws BusinessLogicException {
        PlanAgendadoEntity entity = planAgendado.toEntity();
        entity.setId(id);
        PlanAgendadoEntity oldEntity = planLogic.getPlanAgendado(id);
        if (oldEntity == null) {
            throw new WebApplicationException("El plan no existe", 404);
        }
        return new PlanAgendadoDetailDTO(planLogic.updatePlanAgendado(entity));
    }
    @DELETE 
    @Path("{id: \\d+}")
     public void deletePlanAgendado(@PathParam("id") Long id) {
        PlanAgendadoEntity entity = planLogic.getPlanAgendado(id);
        if (entity == null) {
            throw new WebApplicationException("El plan no existe", 404);
        }
        planLogic.deletePlanAgendado(id);
    }
    
    
}
