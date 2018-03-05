/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.BlogLogic;
import co.edu.uniandes.csw.turismo.ejb.ComentarioLogic;
import co.edu.uniandes.csw.turismo.entities.BlogEntity;

import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;

import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.BlogPersistence;
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
public class ComentarioLogicTest 
{

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ComentarioLogic ComentarioLogic;
    
    @Inject
    private BlogLogic BlogLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ComentarioEntity> ComentarioData = new ArrayList<ComentarioEntity>();
    
     private List<BlogEntity> BlogData = new ArrayList<BlogEntity>();
    



    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioLogic.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addPackage(BlogEntity.class.getPackage())
                .addPackage(BlogLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
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
     */
    private void clearData() {
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        em.createQuery("delete from ComentarioEntity").executeUpdate();
         em.createQuery("delete from BlogEntity").executeUpdate();
        em.createQuery("delete from BlogEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ComentarioEntity Comentario = factory.manufacturePojo(ComentarioEntity.class);
            em.persist(Comentario);
            ComentarioData.add(Comentario);
        }
        
         for (int i = 0; i < 3; i++) {
            BlogEntity Blog = factory.manufacturePojo(BlogEntity.class);
            em.persist(Blog);
            BlogData.add(Blog);
        }
      
        
    }
    
    
    @Test
    public void createComentarioTest() throws BusinessLogicException 
    {
        BlogEntity a = new BlogEntity();
       ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class); 
       List<ComentarioEntity> lista = a.getComentarios();
       lista.add(newEntity);
       a.setComentarios(lista);
        ComentarioEntity result = ComentarioLogic.createComentario(newEntity, a.getId());
        
      

        Assert.assertNotNull(result);
        ComentarioEntity entity = em.find(ComentarioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertNotNull(entity.getComentario());
    }
    
    @Test
    public void getComentarioTest() throws BusinessLogicException 
    {
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class); 
        List<ComentarioEntity> list = ComentarioLogic.getComentarios(newEntity.getId());
        Assert.assertEquals(ComentarioData.size(), list.size());
        for (ComentarioEntity entity : list) 
        {
            boolean found = false;
            for (ComentarioEntity storedEntity : ComentarioData) 
            {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void deleteComentarioTest() throws BusinessLogicException
    {
         ComentarioEntity entity = ComentarioData.get(0);
         ComentarioLogic.deleteComentario(entity);
         ComentarioEntity deleted = em.find( ComentarioEntity.class, entity.getId());
         Assert.assertNull(deleted);
    }
    
     @Test
    public void updateComentarioTest() throws BusinessLogicException
    {
         ComentarioEntity entity = ComentarioData.get(0);
         ComentarioEntity pojoEntity = factory.manufacturePojo( ComentarioEntity.class);

        pojoEntity.setId(entity.getId());

         ComentarioLogic.updateComentario(pojoEntity);

         ComentarioEntity resp = em.find( ComentarioEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getComentario(), resp.getComentario());
    }
}