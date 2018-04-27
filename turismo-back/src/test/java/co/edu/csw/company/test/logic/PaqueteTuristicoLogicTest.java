/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.PaqueteTuristicoLogic;
import co.edu.uniandes.csw.turismo.entities.PagoEntity;
import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
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
public class PaqueteTuristicoLogicTest {
    

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PaqueteTuristicoLogic paqueteTuristicoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PaqueteTuristicoEntity> data = new ArrayList<>();
    
    private List<PagoEntity> pagosData = new ArrayList();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaqueteTuristicoEntity.class.getPackage())
                .addPackage(PaqueteTuristicoLogic.class.getPackage())
                .addPackage(PaqueteTuristicoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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
        em.createQuery("delete from PagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            PagoEntity pagos = factory.manufacturePojo(PagoEntity.class);
            em.persist(pagos);
            pagosData.add(pagos);
        }
        for (int i = 0; i < 3; i++) {
            PaqueteTuristicoEntity entity = factory.manufacturePojo(PaqueteTuristicoEntity.class);
            entity.setPagos(pagosData);
            em.persist(entity);
            data.add(entity);
        }
        

    }

    /**
     * Prueba para crear un PaqueteTuristico
     *
     *
     */
    @Test
    public void createPaqueteTuristicoTest() throws BusinessLogicException {
        PaqueteTuristicoEntity newEntity = factory.manufacturePojo(PaqueteTuristicoEntity.class);
        PaqueteTuristicoEntity result = paqueteTuristicoLogic.createPaqueteTuristico(newEntity);
        Assert.assertNotNull(result);
        PaqueteTuristicoEntity entity = em.find(PaqueteTuristicoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
       
    }

    /**
     * Prueba para consultar la lista de PaqueteTuristicos
     *
     *
     */
    @Test
    public void getPaquetesTuristicosTest() {
        List<PaqueteTuristicoEntity> list = paqueteTuristicoLogic.getPaquetes();
        Assert.assertEquals(data.size(), list.size());
        for (PaqueteTuristicoEntity entity : list) {
            boolean found = false;
            for (PaqueteTuristicoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un PaqueteTuristico
     *
     *
     */
    @Test
    public void getPaqueteTuristicoTest() {
        PaqueteTuristicoEntity entity = data.get(0);
        PaqueteTuristicoEntity resultEntity = paqueteTuristicoLogic.getPaquete(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un PaqueteTuristico
     *
     *
     */
    @Test
    public void deletePaqueteTuristicoTest() {
        PaqueteTuristicoEntity entity = data.get(0);
        paqueteTuristicoLogic.deletePaqueteTuristico(entity.getId());
        PaqueteTuristicoEntity deleted = em.find(PaqueteTuristicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un PaqueteTuristico
     *
     *
     */
    @Test
    public void updatePaqueteTuristicoTest() {
        PaqueteTuristicoEntity entity = data.get(0);
        PaqueteTuristicoEntity pojoEntity = factory.manufacturePojo(PaqueteTuristicoEntity.class);

        pojoEntity.setId(entity.getId());

        paqueteTuristicoLogic.updatePaquete(pojoEntity);

        PaqueteTuristicoEntity resp = em.find(PaqueteTuristicoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    

}

