/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.UsuarioEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.slf4j.spi.LocationAwareLogger;

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
    
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de creación del usuario");
        if(persistence.find(entity.getId()) != null)
        {
            throw new BusinessLogicException("El usuario con id "+entity.getId()+" ya existe");
        }
        if(entity.getNombre() == null || entity.getNombre().equals(""))
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
}
