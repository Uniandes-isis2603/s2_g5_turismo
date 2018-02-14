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
 * <pre>Clase que implementa el recurso "paqueteTuristico".
 * URL: /api/paquete
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "paquete".</i>
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
@Path("paquete")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PaqueteTuristicoResource {
    /**
     * <h1>POST /api/paquete : Crear un nuevo paquete.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link PaqueteTuristicoDetailDTO}.
     * 
     * Crea un nuevo paquete con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo paquete turistico .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el paquete turistico.
     * </code>
     * </pre>
     * @param paquete {@link PaqueteTuristicoDetailDTO} - El paquete que se desea guardar.
     * @return JSON {@link PaqueteTuristicoDetailDTO}  - El paquete guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el paquete.
     */
    @POST
    public PaqueteTuristicoDetailDTO createPaquete(PaqueteTuristicoDetailDTO paquete) throws BusinessLogicException {
        return paquete;
    }

    /**
     * <h1>GET /api/paquete : Obtener todos los paquetes.</h1>
     * 
     * <pre>Busca y devuelve todos los paquetes que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los paquetes de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link PaqueteTuristicoDetailDTO} - Los paquetes turisticos encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PaqueteTuristicoDetailDTO> getPaqueteTuristicos() {
        return new ArrayList<>();
    }

    /**
     * <h1>GET /api/paquete/{id} : Obtener paquete por id.</h1>
     * 
     * <pre>Busca el paquete con el id asociado recibido en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve lel paquete turistico correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un paquete con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del paquete que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@linkpaquetesDetailDTO} - El paquete turistico buscado.
     */
    @GET
    @Path("{id: \\d+}")
    public PaqueteTuristicoDetailDTO getPaqueteTuristico(@PathParam("id") Long id) {
        return null;
    }
    
    
    /**
     * <h1>PUT /api/paquete/{id} : Actualizar el paquete con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PaqueteTuristicoDetailDTO}.
     * 
     * Actualiza un paquete con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el paquete con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un paquete con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del paquete que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param paquete {@link PaqueteTuristicoDetailDTO} El paquerte que se desea guardar.
     * @return JSON {@link PaqueteTuristicoDetailDTO} - El paquete guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la ciudad porque ya existe un paquete con ese identificador.     */
    @PUT
    @Path("{id: \\d+}")
    public PaqueteTuristicoDetailDTO updatePaqueteTuristico(@PathParam("id") Long id, PaqueteTuristicoDetailDTO paquete) throws BusinessLogicException {
        return paquete;
    }
    
    /**
     * <h1>DELETE /api/paquete/{id} : Borrar paquete por id.</h1>
     * 
     * <pre>Borra el paquete con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el paquete turistico correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un paquete con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del paquete que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE 
    @Path("{id: \\d+}")
     public void deletePaqueteTuristico(@PathParam("id") Long id) {
        // Void
    }
}
