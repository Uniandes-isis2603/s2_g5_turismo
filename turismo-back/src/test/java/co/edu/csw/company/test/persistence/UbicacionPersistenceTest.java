/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.persistence;

import co.edu.uniandes.csw.turismo.entities.UbicacionEntity;
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
public class UbicacionPersistenceTest {
    
    public UbicacionPersistenceTest() {
    }
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UbicacionEntity.class.getPackage())
                .addPackage(UbicacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private UbicacionPersistence UbicacionPersistence;
    
    /**
     * Contexto de Persistencia que se va autilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se
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
     private void clearData() {
        em.createQuery("delete from UbicacionEntity").executeUpdate();
    }

    /**
     *
     */
    private List<UbicacionEntity> data = new ArrayList<UbicacionEntity>();
    
   
    
    /*
    *     
    */
 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UbicacionEntity entity = factory.manufacturePojo(UbicacionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /* 
    * Prueba de creacion de un Ubicacion     
    */
 @Test
    public void createUbicacionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        UbicacionEntity newEntity = factory.manufacturePojo(UbicacionEntity.class);
        UbicacionEntity result = UbicacionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        UbicacionEntity entity = em.find(UbicacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
       Assert.assertEquals(newEntity.getPais(), entity.getPais());
       Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
       Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
       Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
       
    }
    
      /*
     * Prueba para consultar la lista de Ubicacions.
     */
    @Test
    public void getTUbicacionsTest() {
        List<UbicacionEntity> list = UbicacionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (UbicacionEntity ent : list) {
            boolean found = false;
            for (UbicacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /*
    * Prueba para consultar un Ubicacion    
    */
    
     @Test
    public void updateUbicacionTest() {
        UbicacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UbicacionEntity newEntity = factory.manufacturePojo(UbicacionEntity.class);

        newEntity.setId(entity.getId());

        UbicacionPersistence.update(newEntity);

        UbicacionEntity resp = em.find(UbicacionEntity.class, entity.getId());

         Assert.assertEquals(newEntity.getId(), resp.getId());
       Assert.assertEquals(newEntity.getPais(), resp.getPais());
       Assert.assertEquals(newEntity.getCiudad(), resp.getCiudad());
       Assert.assertEquals(newEntity.getLatitud(), resp.getLatitud());
       Assert.assertEquals(newEntity.getLongitud(), resp.getLongitud());
    }
    /*
    * Prueba para elimminar un Ubicacion    
    */
  @Test
    public void deleteUbicacionTest() {
        UbicacionEntity entity = data.get(0);
        UbicacionPersistence.delete(entity.getId());
        UbicacionEntity deleted = em.find(UbicacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /*
    * Prueba para Consultar un Ubicacion    
    */
       @Test
    public void getUbicacionTest() {
        UbicacionEntity entity = data.get(0);
        UbicacionEntity newEntity = UbicacionPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
      Assert.assertEquals(newEntity.getId(), entity.getId());
       Assert.assertEquals(newEntity.getPais(), entity.getPais());
       Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
       Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
       Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
       
       
    }    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
