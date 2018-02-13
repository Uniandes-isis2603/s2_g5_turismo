/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PaqueteTuristicoDetailDTO;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
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
 * @author dl.avendano
 */
@Path("paquete")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PaqueteTuristicoResource {
    /**
     * <h1>POST /api/paquetess : Crear un nuevo paquetes.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link PaqueteTuristicoDetailDTO}.
     * 
     * Crea una nueva ciudad con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva ciudad .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la ciudad.
     * </code>
     * </pre>
     * @param PaqueteTuristico {@link PaqueteTuristicoDetailDTO} - El paquetes que se desea guardar.
     * @return JSON {@link PaqueteTuristicoDetailDTO}  - El paquetes guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la ciudad.
     */
    @POST
    public PaqueteTuristicoDetailDTO createPaquete(PaqueteTuristicoDetailDTO paquetes) throws BusinessLogicException {
        return paquetes;
    }

    /**
     * <h1>GET /api/paquetess : Obtener todos los paquetess.</h1>
     * 
     * <pre>Busca y devuelve todas las ciudades que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las ciudades de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link PaqueteTuristicoDetailDTO} - Las ciudades encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<PaqueteTuristicoDetailDTO> getPaqueteTuristicos() {
        return new ArrayList<>();
    }

    /**
     * <h1>GET /api/paquetess/{id} : Obtener paquetes por id.</h1>
     * 
     * <pre>Busca el paquetes con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la ciudad correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una ciudad con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del paquetes que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@linkpaquetesDetailDTO} - La ciudad buscada
     */
    @GET
    @Path("{id: \\d+}")
    public PaqueteTuristicoDetailDTO getPaqueteTuristico(@PathParam("id") Long id) {
        return null;
    }
    
    
    /**
     * <h1>PUT /api/paquetess/{id} : Actualizar el paquetes con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PaqueteTuristicoDetailDTO}.
     * 
     * Actualiza la ciudad con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la ciudad con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una ciudad con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la ciudad que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param paquetes {@link PaqueteTuristicoDetailDTO} La ciudad que se desea guardar.
     * @return JSON {@link PaqueteTuristicoDetailDTO} - La ciudad guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la ciudad porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public PaqueteTuristicoDetailDTO updatePaqueteTuristico(@PathParam("id") Long id, PaqueteTuristicoDetailDTO paquetes) throws BusinessLogicException {
        return paquetes;
    }
    
    /**
     * <h1>DELETE /api/paquetess/{id} : Borrar paquetes por id.</h1>
     * 
     * <pre>Borra el paquetes con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la ciudad correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una ciudad con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del paquetes que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE 
    @Path("{id: \\d+}")
     public void deletePaqueteTuristico(@PathParam("id") Long id) {
        // Void
    }
}
