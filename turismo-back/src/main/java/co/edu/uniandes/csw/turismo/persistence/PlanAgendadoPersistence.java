/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
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
public class PlanAgendadoPersistence {
     private static final Logger LOGGER = Logger.getLogger(PlanAgendadoPersistence.class.getName());

    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;

    public PlanAgendadoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando plan agendado con id={0}", id);
        return em.find(PlanAgendadoEntity.class, id);
    }

    public List<PlanAgendadoEntity> findAll() {
        LOGGER.info("Consultando todos los plan agendado");
        TypedQuery q = em.createQuery("select u from PlanAgendadoEntity u",PlanAgendadoEntity.class);
        return q.getResultList();
    }

    public PlanAgendadoEntity create(PlanAgendadoEntity entity) {
        LOGGER.info("Creando un plan agendado nuevo");
        em.persist(entity);
        LOGGER.info("PlanAgendado creado");
        return entity;
    }

    public PlanAgendadoEntity update(PlanAgendadoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando plan agendado con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando plan agendado con id={0}", id);
        PlanAgendadoEntity entity = em.find(PlanAgendadoEntity.class, id);
        em.remove(entity);
    }
}
