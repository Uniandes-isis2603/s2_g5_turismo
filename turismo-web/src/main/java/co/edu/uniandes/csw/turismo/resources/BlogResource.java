
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.BlogDTO;
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

@Path("/blogs")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class BlogResource {
    
  @GET
  @Path("{id: \\d+}") 
public void GetBlog (@PathParam("id") long id)  
{
}

  
  @GET
  public void GetBlogs ()
  {
  }
    
    
  @POST
  public void CrearBlog ()
  {
  
  }

  @PUT
  @Path("{id: \\d+}") 
  public void ActualizarBlog (@PathParam("id") long id)
  {}
  
  @DELETE
  @Path("{id: \\d+}") 
  public void BorrarBlog (@PathParam("id") long id)
  {
  
  }
  

}

