/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.persistence;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.persistence.TarjetaDeCreditoPersistence;
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
public class TarjetaDeCreditoTest {
    
    public TarjetaDeCreditoTest() {
    }
    
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaDeCreditoEntity.class.getPackage())
                .addPackage(TarjetaDeCreditoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase TarjetaDeCreditoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private TarjetaDeCreditoPersistence tarjetaDeCreditoPersistence;

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
     private void clearData() {
        em.createQuery("delete from TarjetaDeCreditoEntity").executeUpdate();
    }

    /**
     *
     */
    private List<TarjetaDeCreditoEntity> data = new ArrayList<TarjetaDeCreditoEntity>();
    
   
    
    /*
    *     
    */
 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TarjetaDeCreditoEntity entity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /* 
    * Prueba de creacion de tarjeta De Credito     
    */
 @Test
    public void createTarjetaDeCreditoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        TarjetaDeCreditoEntity result = tarjetaDeCreditoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getCDV(), entity.getCDV());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
        
       
    }
    
      /*
     * Prueba para consultar la lista de TarjetaDeCreditos.
     */
    @Test
    public void getTarjetaDeCreditosTest() {
        List<TarjetaDeCreditoEntity> list = tarjetaDeCreditoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaDeCreditoEntity ent : list) {
            boolean found = false;
            for (TarjetaDeCreditoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /*
    * Prueba para consultar una tarjetaDeCredito    
    */
    
     @Test
    public void updateTarjetaDeCreditoTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);

        newEntity.setId(entity.getId());

        tarjetaDeCreditoPersistence.update(newEntity);

        TarjetaDeCreditoEntity resp = em.find(TarjetaDeCreditoEntity.class, entity.getId());

       Assert.assertEquals(newEntity.getCDV(), resp.getCDV());
        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getNumero(), resp.getNumero());
    }
    /*
    * Prueba para elimminar una tarjetaDeCredito    
    */
    @Test
    public void deleteTarjetaDeCreditoTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        tarjetaDeCreditoPersistence.delete(entity.getId());
        TarjetaDeCreditoEntity deleted = em.find(TarjetaDeCreditoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /*
    * Prueba para Consultar una tarjetaDeCredito    
    */
       @Test
    public void getTarjetaDeCreditoTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity newEntity = tarjetaDeCreditoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getCDV(), entity.getCDV());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
       
    }

}
