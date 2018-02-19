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
import javax.persistence.Query;


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
    
    public ValoracionesEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando valoracion con id={0}", id);
        return em.find(ValoracionesEntity.class, id);
    }
    
    public List<ValoracionesEntity> findAll()
    {
        LOGGER.info("Consultando todas las valoraciones");
        Query q = em.createQuery("select u from ValoracionesEntity u");
        return q.getResultList();
    }
    
    public ValoracionesEntity create(ValoracionesEntity entity)
    {
        LOGGER.info("Consultando todas las valoraciones");
        em.persist(entity);
        LOGGER.info("Usuario creado");
        return entity;
    }
    
    public ValoracionesEntity update(ValoracionesEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando valoracion con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Eliminando valoracion con id=(0)", id);
        ValoracionesEntity entity = em.find(ValoracionesEntity.class, id);
        em.remove(entity);                        
    }
}
