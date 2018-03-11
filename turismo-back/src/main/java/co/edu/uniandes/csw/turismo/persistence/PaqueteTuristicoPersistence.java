/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
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
 * @author dl.avendano
 */
@Stateless
public class PaqueteTuristicoPersistence {
    private static final Logger LOGGER = Logger.getLogger(PaqueteTuristicoPersistence.class.getName());

    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;

    public PaqueteTuristicoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando paquete turistico con id={0}", id);
        return em.find(PaqueteTuristicoEntity.class, id);
    }


    public List<PaqueteTuristicoEntity> findAll() {
        LOGGER.info("Consultando todos los paquete turisticos");
        TypedQuery q = em.createQuery("select u from PaqueteTuristicoEntity u",PaqueteTuristicoEntity.class);
        return q.getResultList();
    }

    public PaqueteTuristicoEntity create(PaqueteTuristicoEntity entity) {
        LOGGER.info("Creando un paquete turistico nuevo");
        em.persist(entity);
        LOGGER.info("PaqueteTuristico creado");
        return entity;
    }

    public PaqueteTuristicoEntity update(PaqueteTuristicoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando paquete turistico con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando paquete turistico con id={0}", id);
        PaqueteTuristicoEntity entity = em.find(PaqueteTuristicoEntity.class, id);
        em.remove(entity);
    }
}
