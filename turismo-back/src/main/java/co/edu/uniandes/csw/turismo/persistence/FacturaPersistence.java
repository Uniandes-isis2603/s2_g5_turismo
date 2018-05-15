/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
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
public class FacturaPersistence 
{
    /**
     * modela el logger
     */
     private static final Logger LOGGER = Logger.getLogger(FacturaEntity.class.getName());
     /**
      * se encarga de establecer el entityManager para el manejo de la base de datos
      */
    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;
    /**
     * Encuentra una factura en especifica segun el id
     * @param id
     * @return la factura con el id
     */
    public FacturaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Factura con id={0}", id);
        return em.find(FacturaEntity.class, id);
    }
    /**
     * encuentra una factura segun la tarjeta con la que se haya pagado
     * @param numeroTarjeta
     * @return 
     */
     public List<FacturaEntity> findByTarjeta(Long numeroTarjeta) 
     {
        LOGGER.log(Level.INFO, "Consultando Facturas con numero de tarjeta={0}",numeroTarjeta);
        TypedQuery query = em.createQuery("Select e From FacturaEntity e where e.tarjetadecredito.id = :numero", FacturaEntity.class);
        query = query.setParameter("numero", numeroTarjeta);
        List<FacturaEntity> facturas = query.getResultList();
        return facturas;
    }
    /**
     * 
     * @return devuelve todas la facturas 
     */
      public List<FacturaEntity> findAll() {
        LOGGER.info("Consultando todas las facturas");
        Query q = em.createQuery("select u from FacturaEntity u");
        return q.getResultList();
    }
    /**
     * guarda una factura en al base de datos
     * @param entity
     * @return factura guardada
     */
    public FacturaEntity create(FacturaEntity entity) {
        LOGGER.info("Creando un Factura nuevo");
        em.persist(entity);
        LOGGER.info("Factura creada");
        return entity;
    }
    /**
     * modifica una factura ya existente de la base de datos por los datos que le entran por parametro
     * @param entity
     * @return factura modificada
     */
      public FacturaEntity update(FacturaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando factura con id={0}", entity.getId());
        return em.merge(entity);
    }
      
    /**
     * Elimina una factura de la base de datos dado un id
     * @param id 
     */    
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando factura con id={0}", id);
        FacturaEntity entity = em.find(FacturaEntity.class, id);
        em.remove(entity);
    }

    
}
