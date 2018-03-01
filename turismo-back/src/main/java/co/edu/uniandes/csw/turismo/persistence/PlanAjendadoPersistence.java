/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.PlanAjendadoEntity;
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
public class PlanAjendadoPersistence {
     private static final Logger LOGGER = Logger.getLogger(PlanAjendadoPersistence.class.getName());

    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;

    public PlanAjendadoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando plan ajendado con id={0}", id);
        return em.find(PlanAjendadoEntity.class, id);
    }

    public PlanAjendadoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando plan ajendado con name= ", name);
        TypedQuery<PlanAjendadoEntity> q = em.createQuery("select u from PlanAjendadoEntity u where u.name = :name", PlanAjendadoEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    public List<PlanAjendadoEntity> findAll() {
        LOGGER.info("Consultando todos los plan ajendado");
        Query q = em.createQuery("select u from PlanAjendadoEntity u");
        return q.getResultList();
    }

    public PlanAjendadoEntity create(PlanAjendadoEntity entity) {
        LOGGER.info("Creando un plan ajendado nuevo");
        em.persist(entity);
        LOGGER.info("PlanAjendado creado");
        return entity;
    }

    public PlanAjendadoEntity update(PlanAjendadoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando plan ajendado con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando plan ajendado con id={0}", id);
        PlanAjendadoEntity entity = em.find(PlanAjendadoEntity.class, id);
        em.remove(entity);
    }
}
