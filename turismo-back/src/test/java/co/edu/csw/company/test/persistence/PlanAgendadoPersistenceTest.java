/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.persistence;

import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import co.edu.uniandes.csw.turismo.persistence.PlanAgendadoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author dl.avendano
 */
@RunWith(Arquillian.class)
public class PlanAgendadoPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlanAgendadoEntity.class.getPackage())
                .addPackage(PlanAgendadoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase PlanAgendadoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PlanAgendadoPersistence planAgendadoPersistence;

    /**
     * Contexto de Persostencia que se va autilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from PlanAgendadoEntity").executeUpdate();
    }

    /**
     *
     */
    private List<PlanAgendadoEntity> data = new ArrayList<PlanAgendadoEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PlanAgendadoEntity entity = factory.manufacturePojo(PlanAgendadoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un PlanAgendado.
     *
     *
     */
    @Test
    public void createPlanAgendadoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PlanAgendadoEntity newEntity = factory.manufacturePojo(PlanAgendadoEntity.class);
        PlanAgendadoEntity result = planAgendadoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PlanAgendadoEntity entity = em.find(PlanAgendadoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
    }

    /**
     * Prueba para consultar la lista de PlanAgendados.
     *
     *
     */
    @Test
    public void getPaquetesTuristicosTest() {
        List<PlanAgendadoEntity> list = planAgendadoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PlanAgendadoEntity ent : list) {
            boolean found = false;
            for (PlanAgendadoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un PlanAgendado.
     *
     *
     */
    @Test
    public void getPlanAgendadoTest() {
        PlanAgendadoEntity entity = data.get(0);
        PlanAgendadoEntity newEntity = planAgendadoPersistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
    }

    /**
     * Prueba para eliminar un PlanAgendado.
     *
     *
     */
    @Test
    public void deletePlanAgendadoTest() {
        PlanAgendadoEntity entity = data.get(0);
        planAgendadoPersistence.delete(entity.getId());
        PlanAgendadoEntity deleted = em.find(PlanAgendadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un PlanAgendado.
     *
     *
     */
    @Test
    public void updatePlanAgendadoTest() {
        PlanAgendadoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PlanAgendadoEntity newEntity = factory.manufacturePojo(PlanAgendadoEntity.class);

        newEntity.setId(entity.getId());
        newEntity.setFecha(entity.getFecha());

        planAgendadoPersistence.update(newEntity);

        PlanAgendadoEntity resp = em.find(PlanAgendadoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getFecha(), resp.getFecha());
    }
}
