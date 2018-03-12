/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.PlanAgendadoLogic;
import co.edu.uniandes.csw.turismo.ejb.PlanAgendadoLogic;
import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
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
public class PlanAgendadoLogicTest {
   
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PlanAgendadoLogic planLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PlanAgendadoEntity> data = new ArrayList<>();
    
    private List<GuiaEntity> guiasData = new ArrayList<>();
    
    private List<PlanEntity> planesData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlanAgendadoEntity.class.getPackage())
                .addPackage(PlanAgendadoLogic.class.getPackage())
                .addPackage(PlanAgendadoPersistence.class.getPackage())
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
        em.createQuery("delete from PlanAgendadoEntity").executeUpdate();
        em.createQuery("delete from PlanEntity").executeUpdate();
        em.createQuery("delete from GuiaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            GuiaEntity guia = factory.manufacturePojo(GuiaEntity.class);
            em.persist(guia);
            guiasData.add(guia);
        }
        for (int i = 0; i < 3; i++) {
            PlanEntity planes = factory.manufacturePojo(PlanEntity.class);
            em.persist(planes);
            planesData.add(planes);
        }
        for (int i = 0; i < 3; i++) {
            PlanAgendadoEntity entity = factory.manufacturePojo(PlanAgendadoEntity.class);
            entity.setGuia(guiasData.get(i));
            entity.setPlan(planesData.get(i));
            em.persist(entity);
            data.add(entity);
         
        }

    }

    /**
     * Prueba para crear un PlanAgendado
     */
    @Test
    public void createPlanAgendadoTest() throws BusinessLogicException {
        PlanAgendadoEntity newEntity = factory.manufacturePojo(PlanAgendadoEntity.class);
        newEntity.setGuia(guiasData.get(0));
        newEntity.setPlan(planesData.get(0));
        PlanAgendadoEntity result = planLogic.createPlanAgendado(newEntity);
        Assert.assertNotNull(result);
        PlanAgendadoEntity entity = em.find(PlanAgendadoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        
    }
    
   @Test
    public void getPlanesTest() {
        List<PlanAgendadoEntity> list = planLogic.getPlanesAgendados();
        Assert.assertEquals(data.size(), list.size());
        for (PlanAgendadoEntity entity : list) {
            boolean found = false;
            for (PlanAgendadoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getPlanTest() {
        PlanAgendadoEntity entity = data.get(0);
        PlanAgendadoEntity resultEntity = planLogic.getPlanAgendado(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }
    
    @Test
    public void deletePlanTest() {
        PlanAgendadoEntity entity = data.get(0);
        planLogic.deletePlanAgendado(entity.getId());
        PlanAgendadoEntity deleted = em.find(PlanAgendadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    @Test
    public void updatePlanTest() {
        PlanAgendadoEntity entity = data.get(0);
        PlanAgendadoEntity pojoEntity = factory.manufacturePojo(PlanAgendadoEntity.class);

        pojoEntity.setId(entity.getId());

        planLogic.updatePlanAgendado(pojoEntity);

        PlanAgendadoEntity resp = em.find(PlanAgendadoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}
