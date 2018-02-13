/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.TarjetaDeCreditoDetailDTO;
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
 * @author s.benitez10
 */
@Path("tarjetas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaDeCreditoResource 
{
    @POST
    public TarjetaDeCreditoDetailDTO createTarjetaDecredito(TarjetaDeCreditoDetailDTO TarjetaDecredito) throws BusinessLogicException {
        return TarjetaDecredito;
    }  
    
    @GET
    public List<TarjetaDeCreditoDetailDTO> geTarjetaDecredito() {
        return new ArrayList<>();
    }
    
    @GET
    @Path("{id: \\d+}")
    public TarjetaDeCreditoDetailDTO getTarjetaDecredito(@PathParam("id") Long id) {
        return null;
    }
    
     @PUT
    @Path("{id: \\d+}")
    public TarjetaDeCreditoDetailDTO updateTarjetaDeCredito(@PathParam("id") Long id, TarjetaDeCreditoDetailDTO TarjetaDeCredito) throws BusinessLogicException {
        return TarjetaDeCredito;
    }
    
    @DELETE
    @Path("{id: \\d+}")
     public void deleteTarjetaDeCredito(@PathParam("id") Long id) {
        // Void
    }
    
}
