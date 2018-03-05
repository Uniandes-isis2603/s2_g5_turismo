/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.PaqueteTuristicoLogic;
import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
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

    private List<PaqueteTuristicoEntity> data = new ArrayList<PaqueteTuristicoEntity>();

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
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            PaqueteTuristicoEntity entity = factory.manufacturePojo(PaqueteTuristicoEntity.class);
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
        Assert.assertEquals(newEntity.getName(), entity.getName());
       
    }

    /**
     * Prueba para consultar la lista de PaqueteTuristicos
     *
     *
     */
    @Test
    public void getPaqueteTuristicosTest() {
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
        Assert.assertEquals(entity.getName(), resultEntity.getName());
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
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
     @Test
    public void getGuiasTest() throws BusinessLogicException 
    {
        PaqueteTuristicoEntityEntity entity = data.get(0);
        GuiaEntity GuiaEntity = GuiasData.get(0);
        GuiaEntity response = PlanLogic.getGuia(entity.getId(), GuiaEntity.getId());

        Assert.assertEquals(GuiaEntity.getId(), response.getId());
        Assert.assertEquals(GuiaEntity.getName(), response.getName());
    }

    /**
     * Prueba para obtener una colección de instancias de Guias asociadas a una
     * instancia Plan
     *
     * 
     */
    @Test
    public void listGuiasTest() {
        List<GuiaEntity> list = PlanLogic.listGuias(data.get(0).getId());
        Assert.assertEquals(3, list.size());
    }

    /**
     * Prueba para asociar un Guias existente a un Plan
     *
     * 
     */
    @Test
    public void addGuiasTest() 
    {
        PlanEntity entity = data.get(1);
        GuiaEntity guiaEntity = GuiasData.get(1);
        GuiaEntity response = PlanLogic.addGuia(guiaEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(guiaEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Guias asociadas a una instancia
     * de Plan
     *
     * 
     */
    @Test
    public void replaceGuiasTest() {
        PlanEntity entity = data.get(0);
        List<GuiaEntity> list = GuiasData.subList(1, 3);
        PlanLogic.replaceGuias(entity.getId(), list);

        entity = PlanLogic.getPlan(entity.getId());
        Assert.assertFalse(entity.getGuias().contains(GuiasData.get(0)));
        Assert.assertTrue(entity.getGuias().contains(GuiasData.get(1)));
        Assert.assertTrue(entity.getGuias().contains(GuiasData.get(2)));
    }

    /**
     * Prueba para desasociar un Guia existente de un Plan existente
     *
     * 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void removeGuiasTest() throws BusinessLogicException {
        boolean estaBien = false;
        try {
            PlanLogic.removeGuia( GuiasData.get(0).getId(),data.get(0).getId());
            GuiaEntity response = PlanLogic.getGuia(data.get(0).getId(), GuiasData.get(0).getId());
            
        } catch (BusinessLogicException e) {
            estaBien = true;
        }
        Assert.assertTrue(estaBien);

    }
}

