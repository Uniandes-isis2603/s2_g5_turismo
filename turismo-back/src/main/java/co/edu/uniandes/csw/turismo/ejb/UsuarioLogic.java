/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.BlogEntity;
import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;
import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.entities.UsuarioEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author jf.gutierrez13
 */
@Stateless
public class UsuarioLogic 
{
    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());
    
    @Inject
    private UsuarioPersistence persistence;
    @Inject
    private PaqueteTuristicoLogic paqueteLogic;
    @Inject 
    private TarjetaDeCreditoLogic tarjetaLogic;
    @Inject 
    private BlogLogic blogLogic;
    @Inject
    private ComentarioLogic comentarioLogic;
    @Inject
    private FacturaLogic facturaLogic;
    @Inject
    private PreferenciasLogic preferenciasLogic;
    
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de creación del usuario");
        if(persistence.find(entity.getId()) != null)
        {
            throw new BusinessLogicException("El usuario con id "+entity.getId()+" ya existe");
        }
        if(entity.getName() == null || entity.getName().equals(""))
        {
            throw new BusinessLogicException("El nombre no es valido");
        }
        if(entity.getApellido() == null || entity.getApellido().equals(""))
        {
            throw new BusinessLogicException("El apellido no es valido");
        }
        String correo = entity.getCorreo();
        String[] arroba = correo.split("@");
        String[] punto = arroba[1].split(".");
        if(arroba[0] == null || arroba[1] == null || punto[0] == null || punto[1] == null)
        {
            throw new BusinessLogicException("El correo no es válido");
        }
        if(entity.getContrasenia() == null || entity.getContrasenia().length() < 8)
        {
            throw new BusinessLogicException("La contraseña no es valida");
        }
        List<UsuarioEntity> listaUsuarios = persistence.findAll();
        int cont = 0;
        for(UsuarioEntity usuario : listaUsuarios)
        {
            if(usuario.getEsAdministrador())
            {
                cont ++;
            }
        }
        if(cont > 1)
        {
            throw new BusinessLogicException("Solo esta permitido un usuario administrador");
        }
        if(entity.getListaTarjetas().size() < 1)
        {
            throw new BusinessLogicException("Se debe registrar al menos una tarjeta de credito");
        }
        if(entity.getListaPreferencias().size() < 1)
        {
            throw new BusinessLogicException("Se debe tener al menos una preferencia");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de usuario");
        return entity;
    }
    
    public List<UsuarioEntity> getUsuarios()
    {
        LOGGER.info("Inicia proceso de consultar todos los usuarios");
        List<UsuarioEntity> lista = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los usuarios");
        return lista;
    }
    
    public UsuarioEntity getUsuario(Long id)
    {
        return persistence.find(id);
    }
    
    public UsuarioEntity updateUsuario(UsuarioEntity entity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de actualizar un usuario");
        if(persistence.find(entity.getId()) != null)
        {
            throw new BusinessLogicException("El usuario con id "+entity.getId()+" ya existe");
        }
        if(entity.getName() == null || entity.getName().equals(""))
        {
            throw new BusinessLogicException("El nombre no es valido");
        }
        if(entity.getApellido() == null || entity.getApellido().equals(""))
        {
            throw new BusinessLogicException("El apellido no es valido");
        }
        String correo = entity.getCorreo();
        String[] arroba = correo.split("@");
        String[] punto = arroba[1].split(".");
        if(arroba[0] == null || arroba[1] == null || punto[0] == null || punto[1] == null)
        {
            throw new BusinessLogicException("El correo no es válido");
        }
        if(entity.getContrasenia() == null || entity.getContrasenia().length() < 8)
        {
            throw new BusinessLogicException("La contraseña no es valida");
        }
        List<UsuarioEntity> listaUsuarios = persistence.findAll();
        int cont = 0;
        for(UsuarioEntity usuario : listaUsuarios)
        {
            if(usuario.getEsAdministrador())
            {
                cont ++;
            }
        }
        if(cont > 1)
        {
            throw new BusinessLogicException("Solo esta permitido un usuario administrador");
        }
        if(entity.getListaTarjetas().size() < 1)
        {
            throw new BusinessLogicException("Se debe registrar al menos una tarjeta de credito");
        }
        if(entity.getListaPreferencias().size() < 1)
        {
            throw new BusinessLogicException("Se debe tener al menos una preferencia");
        }
        persistence.update(entity);
        LOGGER.info("Termina proceso de creación de usuario");
        return entity;
        
    }
    
    public void deleteUsuario(Long id)
    {
        LOGGER.info("Inicia proceso de borrar Plan con id={0}");    
        persistence.delete(id);
        LOGGER.info("Termina proceso de borrar libro con id={0}");
    }
    
    public PaqueteTuristicoEntity getPaquete(Long id)
    {
        LOGGER.info("Inicia proceso de consultar todos los guias del plan con id = {0}");
        return getUsuario(id).getPaquete();
    }
    
    public PaqueteTuristicoEntity createPaquete(Long paqId, Long usuId)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        PaqueteTuristicoEntity paquete1 = paqueteLogic.getPaquete(paqId);
        usuario.setPaquete(paquete1);
        
        return paquete1;
    }
    
    public PaqueteTuristicoEntity updatePaquete(Long paqId, Long usuId)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        PaqueteTuristicoEntity paquete1 = paqueteLogic.getPaquete(paqId);
        usuario.setPaquete(paquete1);
        
        return paquete1;       
    }
    
    public void deletePaquete(Long paqId, Long usuId)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        PaqueteTuristicoEntity paquete1 = paqueteLogic.getPaquete(paqId);
        usuario.setPaquete(null);
    }
    
    public List<BlogEntity> getBlogs(Long usuId)
    {
        LOGGER.info("Inicia proceso de consultar todos los blogs del usuario con id = {0}");
        return getUsuario(usuId).getListaBlogs();
    }
    
    public BlogEntity getBlog(Long usuId, Long blogId) throws BusinessLogicException
    {
        List<BlogEntity> lista = getUsuario(usuId).getListaBlogs();
        BlogEntity blog = blogLogic.getBlogs(blogId);
        int index = lista.indexOf(blog);
        if (index >= 0) {
            return lista.get(index);
        }
        throw new BusinessLogicException("El blog no está asociado al usuario");
    }
    
    public BlogEntity createBlog(Long usuId, Long blogId)throws BusinessLogicException
    {
        UsuarioEntity usuario = getUsuario(usuId);
        BlogEntity lista = blogLogic.getBlogs(blogId);
        usuario.getListaBlogs().add(lista);
        
        return lista;
    }
    
    public List<BlogEntity> updateBlog(Long usuId, List<BlogEntity> blogs)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        
        usuario.setListaBlogs(blogs);
        return blogs;
    }
    
    public void deleteBlog(Long usuId, Long blogId)throws BusinessLogicException
    {
        UsuarioEntity usuario = getUsuario(usuId);
        BlogEntity blog = blogLogic.getBlogs(blogId);
        usuario.getListaBlogs().remove(blog);
    }
    
    public List<ComentarioEntity> getComentarios(Long usuId)
    {
        LOGGER.info("Inicia proceso de consultar todos los comentarios del usuario con id = {0}");
        return getUsuario(usuId).getListaComentarios();
    }
    
    public ComentarioEntity getComentario(Long usuId, Long comId) throws BusinessLogicException
    {
        List<ComentarioEntity> lista = getUsuario(usuId).getListaComentarios();
        ComentarioEntity comentario = comentarioLogic.getComentario(comId);
        int index = lista.indexOf(comentario);
        if (index >= 0) {
            return lista.get(index);
        }
        throw new BusinessLogicException("El comentario no está asociado a el usuario");
    }
    
    public ComentarioEntity createComentario(Long usuId, Long comId)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        ComentarioEntity lista = comentarioLogic.getComentario(comId);
        usuario.getListaComentarios().add(lista);
        
        return lista;
    }
    
    public List<ComentarioEntity> updateComentario(Long usuId, List<ComentarioEntity> comentarios)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        
        usuario.setListaComentarios(comentarios);
        return comentarios;
    }
    
    public void deleteComentario(Long usuId, Long comId)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        ComentarioEntity comentario = comentarioLogic.getComentario(comId);
        usuario.getListaComentarios().remove(comentario);
    }
    
    public List<PreferenciasEntity> getPreferencias(Long usuId)
    {
        LOGGER.info("Inicia proceso de consultar todos los comentarios del usuario con id = {0}");
        return getUsuario(usuId).getListaPreferencias();
    }
    
    public PreferenciasEntity getPreferencia(Long usuId, Long prefId) throws BusinessLogicException
    {
        List<PreferenciasEntity> lista = getUsuario(usuId).getListaPreferencias();
        PreferenciasEntity preferencia = preferenciasLogic.getPreferencias(prefId);
        int index = lista.indexOf(preferencia);
        if (index >= 0) {
            return lista.get(index);
        }
        throw new BusinessLogicException("La preferencia no está asociada a el usuario");
    }
    
    public PreferenciasEntity createPreferencia(Long usuId, Long prefId)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        PreferenciasEntity lista = preferenciasLogic.getPreferencias(prefId);
        usuario.getListaPreferencias().add(lista);
        
        return lista;
    }
    
    public List<PreferenciasEntity> updatePreferencia(Long usuId, List<PreferenciasEntity> preferencias)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        
        usuario.setListaPreferencias(preferencias);
        return preferencias;
    }
    
    public void deletePreferencia(Long usuId, Long prefId)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        PreferenciasEntity preferencias = preferenciasLogic.getPreferencias(prefId);
        usuario.getListaPreferencias().remove(preferencias);
    }
    
    public List<FacturaEntity> getFacturas(Long usuId)
    {
        LOGGER.info("Inicia proceso de consultar todos los comentarios del usuario con id = {0}");
        return getUsuario(usuId).getListaFacturas();
    }
    
    public FacturaEntity getFactura(Long usuId, Long factId) throws BusinessLogicException
    {
        List<FacturaEntity> lista = getUsuario(usuId).getListaFacturas();
        FacturaEntity factura = facturaLogic.getTrajetaDeCredito(factId);
        int index = lista.indexOf(factura);
        if (index >= 0) {
            return lista.get(index);
        }
        throw new BusinessLogicException("La factura no está asociada a el usuario");
    }
    
    public FacturaEntity createFactura(Long usuId, Long factId)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        FacturaEntity lista = facturaLogic.getTrajetaDeCredito(factId);
        usuario.getListaFacturas().add(lista);
        
        return lista;
    }
    
    public List<FacturaEntity> updateFactura(Long usuId, List<FacturaEntity> factura)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        
        usuario.setListaFacturas(factura);
        return factura;
    }
    
    public void deleteFactura(Long usuId, Long factId)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        FacturaEntity factura = facturaLogic.getTrajetaDeCredito(factId);
        usuario.getListaFacturas().remove(factura);
    }
    
    public List<TarjetaDeCreditoEntity> getTarjetas(Long usuId)
    {
        LOGGER.info("Inicia proceso de consultar todos los comentarios del usuario con id = {0}");
        return getUsuario(usuId).getListaTarjetas();
    }
    
    public TarjetaDeCreditoEntity getTarjeta(Long usuId, Long tarId) throws BusinessLogicException
    {
        List<TarjetaDeCreditoEntity> lista = getUsuario(usuId).getListaTarjetas();
        TarjetaDeCreditoEntity tarjeta = tarjetaLogic.getTrajetaDeCredito(tarId);
        int index = lista.indexOf(tarjeta);
        if (index >= 0) {
            return lista.get(index);
        }
        throw new BusinessLogicException("La factura no está asociada a el usuario");
    }
    
    public TarjetaDeCreditoEntity createTarjeta(Long usuId, Long tarId)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        TarjetaDeCreditoEntity lista = tarjetaLogic.getTrajetaDeCredito(tarId);
        usuario.getListaTarjetas().add(lista);
        
        return lista;
    }
    
    public List<TarjetaDeCreditoEntity> updateTarjeta(Long usuId, List<TarjetaDeCreditoEntity> tarjeta)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        
        usuario.setListaTarjetas(tarjeta);
        return tarjeta;
    }
    
    public void deleteTarjeta(Long usuId, Long tarId)
    {
        UsuarioEntity usuario = getUsuario(usuId);
        TarjetaDeCreditoEntity tarjeta = tarjetaLogic.getTrajetaDeCredito(tarId);
        usuario.getListaTarjetas().remove(tarjeta);
    }
}
