/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.ValoracionesEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;


/**
 *
 * @author jf.gutierrez13
 */
@Stateless
public class ValoracionesPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(ValoracionesEntity.class.getName());
    
    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;
    
    /**
     * Busca la valoracion con el id dado por parametro
     * @param id id de la valoracion
     * @return La valoracion buscada
     */
    public ValoracionesEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando valoracion con id={0}", id);
        return em.find(ValoracionesEntity.class, id);
    }
    
    /**
     * Crea una lista de todas las valoraciones
     * @return La lista de valoraciones
     */
    public List<ValoracionesEntity> findAll()
    {
        LOGGER.info("Consultando todas las valoraciones");
        TypedQuery q = em.createQuery("select u from ValoracionesEntity u", ValoracionesEntity.class);
        return q.getResultList();
    }
    
    /**
     * Crea una nueva vvaloracion con los datos ingresados por parametro
     * @param entity Los datos de la valoracion nueva
     * @return La valoracion creada
     */
    public ValoracionesEntity create(ValoracionesEntity entity)
    {
        LOGGER.info("Consultando todas las valoraciones");
        em.persist(entity);
        LOGGER.info("Usuario creado");
        return entity;
    }
    
    /**
     * Actualiza una valoracion con los datos ingresados por parametro
     * @param entity Los datos de la valoracion a actualizar
     * @return La valoracion actualizada
     */
    public ValoracionesEntity update(ValoracionesEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando valoracion con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Elimina una valoracion con el id dado por parametro
     * @param id id de la valoracion a eliminar
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Eliminando valoracion con id=(0)", id);
        ValoracionesEntity entity = em.find(ValoracionesEntity.class, id);
        em.remove(entity);                        
    }
}
