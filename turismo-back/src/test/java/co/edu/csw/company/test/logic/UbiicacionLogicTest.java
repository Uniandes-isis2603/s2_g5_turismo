/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.UbicacionLogic;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import co.edu.uniandes.csw.turismo.entities.UbicacionEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.UbicacionPersistence;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.benitez10
 */
@RunWith(Arquillian.class)
public class UbiicacionLogicTest {
    
    public UbiicacionLogicTest() {
    }
    
   private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UbicacionLogic UbicacionLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<UbicacionEntity> data = new ArrayList<UbicacionEntity>();

    private List<PlanEntity> PlanData = new ArrayList();
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UbicacionEntity.class.getPackage())
                .addPackage(UbicacionLogic.class.getPackage())
                .addPackage(UbicacionPersistence.class.getPackage())
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
    private void clearData() 
    {
   
        em.createQuery("delete from PlanEntity").executeUpdate();
        em.createQuery("delete from UbicacionEntity").executeUpdate();
        
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) {
            PlanEntity user = factory.manufacturePojo(PlanEntity.class);
            em.persist(user);
            PlanData.add(user);
        }
        for (int i = 0; i < 3; i++) {
           UbicacionEntity entity = factory.manufacturePojo( UbicacionEntity.class);
           PlanData.get(i).setUbicacion(entity);

            em.persist(entity);
            data.add(entity);
        }
    }
     /**
     * Prueba para crear una tarjeta
     *
     *
     */
    @Test
    public void createUbicacionTest() throws BusinessLogicException 
    {
        UbicacionEntity newEntity = factory.manufacturePojo(UbicacionEntity.class);
        
        UbicacionEntity result = UbicacionLogic.createUbicacion(newEntity);
        //factory.manufacturePojo: esto crea tarjetas que van en contra de reglas de negocio
       

        Assert.assertNotNull(result);
        UbicacionEntity entity = em.find(UbicacionEntity.class, result.getId());
       Assert.assertEquals(newEntity.getId(), entity.getId());
       Assert.assertEquals(newEntity.getPais(), entity.getPais());
       Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
       Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
       Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
    }
    
    
     /**
     * Prueba para consultar la lista de tarjetas
     *
     *
     */
    @Test
    public void getUbicacionsTest() 
    {
        List<UbicacionEntity> list = UbicacionLogic.getUbicacions();
        Assert.assertEquals(data.size(), list.size());
        for (UbicacionEntity entity : list) 
        {
            boolean found = false;
            for (UbicacionEntity storedEntity : data) 
            {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getUbicacionTest()
    {
       UbicacionEntity entity = data.get(0);
       UbicacionEntity resultEntity = UbicacionLogic.getUbicacion(entity.getId());
       Assert.assertEquals(resultEntity.getId(), entity.getId());
       Assert.assertEquals(resultEntity.getPais(), entity.getPais());
       Assert.assertEquals(resultEntity.getCiudad(), entity.getCiudad());
       Assert.assertEquals(resultEntity.getLatitud(), entity.getLatitud());
       Assert.assertEquals(resultEntity.getLongitud(), entity.getLongitud());
    }


    /**
     * Prueba para actualizar un  Ubicacion
     *
     *
     */
    @Test
    public void updateUbicacionTest() throws BusinessLogicException
    {
         UbicacionEntity entity = data.get(0);
         UbicacionEntity pojoEntity = factory.manufacturePojo( UbicacionEntity.class);

         pojoEntity.setId(entity.getId());

         UbicacionLogic.updateUbicacion(pojoEntity.getId(), pojoEntity);

         UbicacionEntity resp = em.find( UbicacionEntity.class, entity.getId());

       Assert.assertEquals(pojoEntity.getId(), resp.getId());
       Assert.assertEquals(pojoEntity.getPais(), resp.getPais());
       Assert.assertEquals(pojoEntity.getCiudad(),resp.getCiudad());
       Assert.assertEquals(pojoEntity.getLatitud(), resp.getLatitud());
       Assert.assertEquals(pojoEntity.getLongitud(), resp.getLongitud());
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
