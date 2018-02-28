/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import java.util.ArrayList;
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
      private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoEntity.class.getName());

    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;

    public TarjetaDeCreditoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando TarjetaDeCredito con id={0}", id);
        return em.find(TarjetaDeCreditoEntity.class, id);
    }
    
      public List<TarjetaDeCreditoEntity> findAll() {
        LOGGER.info("Consultando todas las Tarjeta De Creditos");
        Query q = em.createQuery("select u from TarjetaDeCreditoEntity u");
        return q.getResultList();
    }
    
      public TarjetaDeCreditoEntity findByNumber(Long numero) 
      {
        LOGGER.log(Level.INFO, "Consultando heroe por nombre ", numero);
      
        // Se crea un query para buscar tarjetas de credito recibe el m�todo como argumento. ":numero" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From TarjetaDeCreditoEntity e where e.numero = :numero", TarjetaDeCreditoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("numero", numero);
        // Se invoca el query se obtiene la lista resultado
        List<TarjetaDeCreditoEntity> sameName =  query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
    public TarjetaDeCreditoEntity create(TarjetaDeCreditoEntity entity) {
        LOGGER.info("Creando un TarjetaDeCredito nuevo");
        em.persist(entity);
        LOGGER.info("TarjetaDeCredito creada");
        return entity;
    }
    
      public TarjetaDeCreditoEntity update(TarjetaDeCreditoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando TarjetaDeCredito con id={0}", entity.getId());
        return em.merge(entity);
    }
      
        public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Tarjeta De Credito con id={0}", id);
        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, id);
        em.remove(entity);
    }
}
