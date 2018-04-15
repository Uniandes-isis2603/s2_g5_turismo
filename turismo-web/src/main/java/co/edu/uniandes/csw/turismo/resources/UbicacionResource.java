/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.UbicacionDTO;
import co.edu.uniandes.csw.turismo.ejb.PlanLogic;
import co.edu.uniandes.csw.turismo.ejb.UbicacionLogic;
import co.edu.uniandes.csw.turismo.entities.UbicacionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.mappers.BusinessLogicExceptionMapper;
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

/**
 *
 * @author s.benitez10
 */
@Path("Plans/{plansId: \\d+}/ubicaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UbicacionResource 
{
    @Inject
    private UbicacionLogic ubicacionlogic;
    @Inject
    private PlanLogic planLogic;
   
      /**
     * <h1>POST /api/Plans/{plansId: \\d+}/ubicaciones: Crear una ubicacion.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link UbicacionDTO}.
     * 
     * Crea una nueva ubicacion con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva ubicacion.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la ubicacion.
     * </code>
     * </pre>
     * @param ubicacion {@link UbicacionDTO} - La ubicacion que se desea guardar.
     * @return JSON {@link UbicacionDTO}  - La ubicacion guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la ubicacion.
     */
   @POST
    public UbicacionDTO createUbicacion(UbicacionDTO ubicacion,@PathParam("plansId") Long id) throws BusinessLogicException 
    {
        UbicacionEntity UbicEnt = ubicacion.toEntity();
        if(planLogic.getPlan(id) != null)
        {
          planLogic.getPlan(id).setUbicacion(UbicEnt);  ;
        }
        else
        {
            throw new BusinessLogicException(" el plan no existe");
        }
       
        
        return new UbicacionDTO(ubicacionlogic.createUbicacion(UbicEnt));
    }  
    
    /**
     * <h1>GET /api/Plans/{plansId: \\d+}/ubicaciones: Obtener todas las ubicacions.</h1>
     * 
     * <pre>Busca y devuelve todas las ubicacions que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las ubicacions de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link UbicacionDTO} - Las ubicacions encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<UbicacionDTO> getUbicacion() 
    {
        ArrayList<UbicacionDTO> ubicacions = new ArrayList<UbicacionDTO>();
        List<UbicacionEntity> ubicacionEntity = ubicacionlogic.getUbicacions();
        for (UbicacionEntity ubicacionEnt : ubicacionEntity) 
        {
            ubicacions.add(new UbicacionDTO(ubicacionEnt));
            
        }
        return ubicacions;
    }
    
       /**
     * <h1>GET /api/ubicacions/{id} : Obtener ubicacion por id.</h1>
     * 
     * <pre>Busca la ubicacion con el id asociado recibido en la URL y la devuelve.
    * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la ubicacion correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una ubicacion con el id dado.
     * </code> 
    * </pre>
    * @param id Identificador de la ubicacion que se esta buscando. Este debe ser una cadena de dígitos.
   * @return JSON {@link UbicacionDTO} - La ubicacion buscada
    */
 
    @GET
    @Path("{id: \\d+}")
    public UbicacionDTO getUbicacion(@PathParam("id") Long id) 
    {
        return new UbicacionDTO(ubicacionlogic.getUbicacion(id));
    }
    /**
     * <h1>PUT /api/Plans/{plansId: \\d+}/ubicaciones/{id} : Actualizar ubicacion con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link UbicacionDTO}.
     * 
     * Actualiza la ubicacion con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la ubicacion con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una ubicacion con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la ubicacion que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param Ubicacion {@link UbicacionDTO} La ubicacion que se desea guardar.
     * @return JSON {@link UbicacionDTO} - La ubicacion guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la ubicacion porque ya existe una con ese nombre.
     */
    
     @PUT
    @Path("{id: \\d+}")
    public UbicacionDTO updateUbicacion(@PathParam("id") Long id, UbicacionDTO Ubicacion) throws BusinessLogicException 
    {
        return new UbicacionDTO(ubicacionlogic.updateUbicacion(id, Ubicacion.toEntity()));
    }
     /**
     * <h1>DELETE /api/Plans/{plansId: \\d+}/ubicaciones/{id} : Borrar ubicacion por id.</h1>
     * 
     * <pre>Borra la ubicacion con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la ubicacion correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una ubicacion con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la ubicacion que se desea borrar. Este debe ser una cadena de dígitos.
     */
    
    @DELETE
    @Path("{id: \\d+}")
     public void deleteUbicacion(@PathParam("id") Long id) 
    {
        ubicacionlogic.deleteUbicacion(id);
        // Void
    }
    
}
