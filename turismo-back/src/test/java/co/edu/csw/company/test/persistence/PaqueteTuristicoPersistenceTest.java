/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.persistence;

import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
import co.edu.uniandes.csw.turismo.persistence.PaqueteTuristicoPersistence;
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
public class PaqueteTuristicoPersistenceTest {
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de PaqueteTuristico, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaqueteTuristicoEntity.class.getPackage())
                .addPackage(PaqueteTuristicoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase PaqueteTuristicoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PaqueteTuristicoPersistence paqueteTuristicoPersistence;

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
        em.createQuery("delete from PaqueteTuristicoEntity").executeUpdate();
    }

    /**
     *
     */
    private List<PaqueteTuristicoEntity> data = new ArrayList<PaqueteTuristicoEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PaqueteTuristicoEntity entity = factory.manufacturePojo(PaqueteTuristicoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un PaqueteTuristico.
     *
     *
     */
    @Test
    public void createPaqueteTuristicoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PaqueteTuristicoEntity newEntity = factory.manufacturePojo(PaqueteTuristicoEntity.class);
        PaqueteTuristicoEntity result = paqueteTuristicoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PaqueteTuristicoEntity entity = em.find(PaqueteTuristicoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de PaqueteTuristicos.
     *
     *
     */
    @Test
    public void getPaquetesTuristicosTest() {
        List<PaqueteTuristicoEntity> list = paqueteTuristicoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PaqueteTuristicoEntity ent : list) {
            boolean found = false;
            for (PaqueteTuristicoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un PaqueteTuristico.
     *
     *
     */
    @Test
    public void getPaqueteTuristicoTest() {
        PaqueteTuristicoEntity entity = data.get(0);
        PaqueteTuristicoEntity newEntity = paqueteTuristicoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para eliminar un PaqueteTuristico.
     *
     *
     */
    @Test
    public void deletePaqueteTuristicoTest() {
        PaqueteTuristicoEntity entity = data.get(0);
        paqueteTuristicoPersistence.delete(entity.getId());
        PaqueteTuristicoEntity deleted = em.find(PaqueteTuristicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un PaqueteTuristico.
     *
     *
     */
    @Test
    public void updatePaqueteTuristicoTest() {
        PaqueteTuristicoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PaqueteTuristicoEntity newEntity = factory.manufacturePojo(PaqueteTuristicoEntity.class);

        newEntity.setId(entity.getId());

        paqueteTuristicoPersistence.update(newEntity);

        PaqueteTuristicoEntity resp = em.find(PaqueteTuristicoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
