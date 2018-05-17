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
 * <pre>Clase que implementa el recurso "planAgendado".
 * URL: /api/miPlan
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "planAgendado".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author dl.avendano 
 * @version 1.0
 */
    @Path("miPlan")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PlanAgendadoResource {

        @Inject 
        private PlanAgendadoLogic planLogic;
        /**
     * Recibe una lista de plan agendaod entities y la convierte a dtos
     * @param entityList
     * @return dtos lista
     */
        private List<PlanAgendadoDetailDTO> listEntityToDTO(List<PlanAgendadoEntity> entityList) {
        List<PlanAgendadoDetailDTO> list = new ArrayList<>();
        for(PlanAgendadoEntity entity : entityList) {
            list.add(new PlanAgendadoDetailDTO(entity));
        }
        return list;
    }
        /**
     * <h1>POST /api/plans : Crear un nuevo plan.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link PagoDetailDTO}.
     * 
     * Crea un nuevo plan con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo plan.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el plan.
     * </code>
     * </pre>
     * @param plan {@link PagoDetailDTO} - El plan que se desea guardar.
     * @return JSON {@link PagoDetailDTO}  - El plan guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el plan.
     */
        @POST
    public PlanAgendadoDetailDTO createPaquete(PlanAgendadoDetailDTO plan) throws BusinessLogicException {
        PlanAgendadoEntity planAgendadoEntity = plan.toEntity();
        planAgendadoEntity = planLogic.createPlanAgendado(planAgendadoEntity);
        return new PlanAgendadoDetailDTO(planAgendadoEntity);
    }
    /**
     * <h1>GET /api/miPlan : Obtener todos los plans.</h1>
     * 
     * <pre>Busca y devuelve todos los plans que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los plans de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link PagoDetailDTO} - Los plans encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PlanAgendadoDetailDTO> getPlanesAgendados() {
        return listEntityToDTO(planLogic.getPlanesAgendados()); 
    }
    /**
     * <h1>GET /api/miPlan/{id} : Obtener plan por id.</h1>
     * 
     * <pre>Busca el plan con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el plan correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una plan con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del plan que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link PagoDetailDTO} - El plan buscado
     */
    @GET
    @Path("{id: \\d+}")
    public PlanAgendadoDetailDTO getPlanAgendado(@PathParam("id") Long id) {
        PlanAgendadoEntity plan = planLogic.getPlanAgendado(id);
        if (plan == null) 
            throw new WebApplicationException("El plan no existe",404);
        return new PlanAgendadoDetailDTO(plan); 
    }
    /**
     * <h1>PUT /api/plans/{id} : Actualizar el plan con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PagoDetailDTO}.
     * 
     * Actualiza el plan con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la ciudad con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un plan con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del plan que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param plan {@link PagoDetailDTO} - El plan que se desea guardar.
     * @return JSON {@link PagoDetailDTO} - El plan guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el plan porque ya existe uno con ese identificador.
     */
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
     /**
     * <h1>DELETE /api/plans/{id} : Borrar plan por id.</h1>
     * 
     * <pre>Borra el plan con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el plan correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un plan con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del plan que se desea borrar. Este debe ser una cadena de dígitos.
     */
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
