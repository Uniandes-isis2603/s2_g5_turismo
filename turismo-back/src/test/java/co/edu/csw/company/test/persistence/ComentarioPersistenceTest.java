/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.persistence;

import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;
import co.edu.uniandes.csw.turismo.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.turismo.persistence.ComentarioPersistence;
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
 * @author lf.rivera10
 */
@RunWith(Arquillian.class)
public class ComentarioPersistenceTest {
    
    
 @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase ComentarioPersistance cuyos métodos
     * se van a probar.
     */
    @Inject
    private ComentarioPersistence ComentarioPersistence;

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
        em.createQuery("delete from ComentarioEntity").executeUpdate();
    }

    /**
     *
     */
    private List<ComentarioEntity> data = new ArrayList<ComentarioEntity>();
    
    public ComentarioPersistenceTest() {
    }
    
    /*
    *     
    */
 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);

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
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        ComentarioEntity result = ComentarioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ComentarioEntity entity = em.find(ComentarioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
       
    }
    
      /*
     * Prueba para consultar la lista de Comentarios.
     */
    @Test
    public void getTComentariosTest() {
        List<ComentarioEntity> list = ComentarioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ComentarioEntity ent : list) {
            boolean found = false;
            for (ComentarioEntity entity : data) {
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
    public void updateComentarioTest() {
        ComentarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);

        newEntity.setId(entity.getId());

        ComentarioPersistence.update(newEntity);

        ComentarioEntity resp = em.find(ComentarioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getComentario(), resp.getComentario());
    }
    /*
    * Prueba para elimminar un Comentario    
    */
  @Test
    public void deleteFacturaTest() {
        ComentarioEntity entity = data.get(0);
        ComentarioPersistence.delete(entity.getId());
        ComentarioEntity deleted = em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /*
    * Prueba para Consultar un Comentario    
    */
       @Test
    public void getComentarioTest() {
        ComentarioEntity entity = data.get(0);
        ComentarioEntity newEntity = ComentarioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getComentario(), newEntity.getComentario());
       
    }    
}
