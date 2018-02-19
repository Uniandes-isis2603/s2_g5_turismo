/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.persistence;


import co.edu.uniandes.csw.turismo.entities.ComentariosEntity;
import co.edu.uniandes.csw.turismo.persistence.ComentariosPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.Entity;
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
 * @author lf.rivera10
 */


@RunWith(Arquillian.class)
public class ComentariosPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentariosEntity.class.getPackage())
                .addPackage(ComentariosPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase ComentariosPersistance cuyos métodos
     * se van a probar.
     */
    @Inject
    private ComentariosPersistence ComentariosPersistence;

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
        em.createQuery("delete from ComentariosEntity").executeUpdate();
    }

    /**
     *
     */
    private List<ComentariosEntity> data = new ArrayList<ComentariosEntity>();
    
    public ComentariosPersistenceTest() {
    }
    
    /*
    *     
    */
 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ComentariosEntity entity = factory.manufacturePojo(ComentariosEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /* 
    * Prueba de creacion de un Comentario     
    */
 @Test
    public void createComentarioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ComentariosEntity newEntity = factory.manufacturePojo(ComentariosEntity.class);
        ComentariosEntity result = ComentariosPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ComentariosEntity entity = em.find(ComentariosEntity.class, result.getId());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
       
    }
    
      /*
     * Prueba para consultar la lista de Comentarios.
     */
    @Test
    public void getComentariosTest() {
        List<ComentariosEntity> list = ComentariosPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ComentariosEntity ent : list) {
            boolean found = false;
            for (ComentariosEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /*
    * Prueba para consultar un Comentario    
    */
    
     @Test
    public void updateComentariosTest() {
        ComentariosEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComentariosEntity newEntity = factory.manufacturePojo(ComentariosEntity.class);

        newEntity.setId(entity.getId());

        ComentariosPersistence.update(newEntity);

        ComentariosEntity resp = em.find(ComentariosEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getComentario(), resp.getComentario());
    }
    /*
    * Prueba para elimminar un Comentario    
    */
    @Test
    public void deleteComentariosTest() {
        ComentariosEntity entity = data.get(0);
        ComentariosPersistence.delete(entity.getId());
        ComentariosEntity deleted = em.find(ComentariosEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /*
    * Prueba para Consultar un Comentario    
    */
       @Test
    public void getComentarioTest() {
        ComentariosEntity entity = data.get(0);
        ComentariosEntity newEntity = ComentariosPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getComentario(), newEntity.getComentario());
       
    }
    
}
