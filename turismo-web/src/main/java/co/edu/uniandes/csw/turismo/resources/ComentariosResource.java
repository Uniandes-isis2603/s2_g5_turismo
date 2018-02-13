/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.ComentariosDTO;
import static com.sun.tools.javac.code.Lint.LintCategory.PATH;
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
 * @author lf.rivera10
 */

@Path("blogs/{id: \\d+}/Comentarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentariosResource {
    
    @GET
    public void GetComentario ()
    {
    
    }
    
    @GET
    @Path("{id2: \\d+}") 
    public void GetComentario (@PathParam("id2") long id)
{

}
    @POST
    public void CrearComentario (ComentariosDTO comentario)
    {
    }
    
    @PUT
    @Path("{id2: \\d+}") 
    public void ActualizarComentario (@PathParam("id2") long id)
    {
    }
    
    @DELETE
    @Path("{id2: \\d+}") 
    
    public void BorrarComentario (@PathParam("id2") long id)
    {
    
    }
    
    
}
