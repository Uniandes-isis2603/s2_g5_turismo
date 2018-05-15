/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;
import co.edu.uniandes.csw.turismo.entities.UsuarioEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;



/**
 *
 * @author jf.gutierrez13
 */
@Stateless 
public class UsuarioPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(UsuarioEntity.class.getName());
    
    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;
    
    /**
     * Busca un usuario con el id dado por parametro
     * @param id id del usuario a encontrar
     * @return El usuario buscado
     */
    public UsuarioEntity find(Long id)
    {
        LOGGER.info("Consultando usuario con id= " + id);
        return em.find(UsuarioEntity.class, id);
    }
    
    /**
     * Busca todos los usuarios de la aplicacion
     * @return La lista de usuarios de la aplicacion
     */
    public List<UsuarioEntity> findAll()
    {
        LOGGER.info("Consultando todos los usuarios");
        Query q = em.createQuery("select u from UsuarioEntity u");
        return q.getResultList();
    }
    
    /**
     * Crea un nuevo usuario con los datos que llegan por parametro
     * @param entity Los datos del usuario a crear
     * @return El usuario ya creado
     */
    public UsuarioEntity create(UsuarioEntity entity)
    {
        LOGGER.info("Consultando todos los usuarios");
        em.persist(entity);
        LOGGER.info("Usuario creado");
        return entity;
    }
    
    /**
     * Actualiza el usuario con la informacion dada por parametro
     * @param entity Informacion del usuario actualizado
     * @return El usuario actualizado
     */
    public UsuarioEntity update(UsuarioEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando usuario con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Elimina un usuario con el id dado por parametro
     * @param id id del usuario a eliminar
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Eliminando usuario con id=(0)", id);
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);        
    }
}
