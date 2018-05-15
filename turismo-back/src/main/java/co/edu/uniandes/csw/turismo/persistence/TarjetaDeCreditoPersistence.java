/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author s.benitez10
 */
@Stateless
public class TarjetaDeCreditoPersistence 
{   
    /**
     * modela el logger
     */
    private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoEntity.class.getName());
    /**
     * Establece el entity maneger para el manejo de la base de datos
     */
    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;
    /**
     * se encarga de buscar una tarjeta de credito en especifico con el id
     * @param id
     * @return tarjeta de credito con el id pasado de parametro
     */
    public TarjetaDeCreditoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando TarjetaDeCredito con id={0}", id);
        return em.find(TarjetaDeCreditoEntity.class, id);
    }
    
    /**
     * 
     * @return todas las tarjetas existentes 
     */  
    public List<TarjetaDeCreditoEntity> findAll() {
        LOGGER.info("Consultando todas las Tarjeta De Creditos");
        Query q = em.createQuery("select u from TarjetaDeCreditoEntity u");
        return q.getResultList();
    }
    
    /**
     * Encuentra una tarjeta por su id y numero
     * @param id
     * @param numero
     * @return la tarjeta correspondiente al id y numero dados por parametro
     */  
    public TarjetaDeCreditoEntity findByNumber(Long id,Long numero) 
      {
        LOGGER.log(Level.INFO, "Consultando tarjeta de credito por numero ", numero);
      
       
      
        TypedQuery query = em.createQuery("Select e From TarjetaDeCreditoEntity e where e.numero = :numero and e.usuario.id = :usuario", TarjetaDeCreditoEntity.class);
       
        query = query.setParameter("numero", numero);
        query= query.setParameter("usuario", id);

        List<TarjetaDeCreditoEntity> sameName =  query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    /**
     * guarda la tarjeta entity pasada por parametro a la base de datos
     * @param entity
     * @return la tarjeta guardada
     */
    public TarjetaDeCreditoEntity create(TarjetaDeCreditoEntity entity) {
        LOGGER.info("Creando un TarjetaDeCredito nuevo");
        em.persist(entity);
        LOGGER.info("TarjetaDeCredito creada");
        return entity;
    }
    
    /**
     * Modifica la tarjeta entity que tiene el mismo id que la de la que se pasa por parametro
     * @param entity
     * @return tarjeta modificada
     */
    public TarjetaDeCreditoEntity update(TarjetaDeCreditoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando TarjetaDeCredito con id={0}", entity.getId());
        return em.merge(entity);
    }
    /**
     * Elimmina una tarjeta con el mismo id pasado por parametro de la base de datos
     * @param id 
     */  
    public void delete(Long id) 
        {
        LOGGER.log(Level.INFO, "Borrando Tarjeta De Credito con id={0}", id);
        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, id);
        em.remove(entity);
    }
    /***
     * Elimina una tarjeta por el id y numero de esta de la base de datos
     * @param id
     * @param numero 
     */    
    public void deletebynumber(Long id,Long numero) 
    {
        // Se crea un query para buscar tarjetas de credito recibe el m�todo como argumento. ":numero" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From TarjetaDeCreditoEntity e where e.numero = :numero and e.usuario.id = :usuario", TarjetaDeCreditoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("numero", numero);
        query= query.setParameter("usuario", id);
        // Se invoca el query se obtiene la lista resultado
        List<TarjetaDeCreditoEntity> sameName =  query.getResultList();
        
            em.remove(sameName.get(0));
        
       }
}
