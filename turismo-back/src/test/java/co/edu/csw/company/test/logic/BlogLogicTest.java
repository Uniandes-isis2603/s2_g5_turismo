/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.BlogLogic;
import co.edu.uniandes.csw.turismo.entities.BlogEntity;
import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.BlogPersistence;
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
public class BlogLogicTest 
{

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private BlogLogic BlogLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<BlogEntity> BlogData = new ArrayList<BlogEntity>();
    private List<ComentarioEntity> ComentariosData = new ArrayList<ComentarioEntity>();



    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BlogEntity.class.getPackage())
                .addPackage(BlogLogic.class.getPackage())
                .addPackage(BlogPersistence.class.getPackage())
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
        em.createQuery("delete from BlogEntity").executeUpdate();
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            BlogEntity Blog = factory.manufacturePojo(BlogEntity.class);
            em.persist(Blog);
            BlogData.add(Blog);
        }
        for (int i = 0; i < 3; i++) {
            ComentarioEntity Comentario = factory.manufacturePojo(ComentarioEntity.class);
            em.persist(Comentario);
            ComentariosData.add(Comentario);
        }
        
    }
    
    
    @Test
    public void createBlogTest() throws BusinessLogicException 
    {
       BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);       
        BlogEntity result = BlogLogic.createBlog(newEntity);
        
      

        Assert.assertNotNull(result);
        BlogEntity entity = em.find(BlogEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertNotNull(entity.getDescripcion());
        Assert.assertNotNull(entity.getTema());
    }
    
    @Test
    public void getBlogTest() throws BusinessLogicException 
    {
        List<BlogEntity> list = BlogLogic.getBlogs();
        Assert.assertEquals(BlogData.size(), list.size());
        for (BlogEntity entity : list) 
        {
            boolean found = false;
            for (BlogEntity storedEntity : BlogData) 
            {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void deleteBlogTest() throws BusinessLogicException
    {
         BlogEntity entity = BlogData.get(0);
         BlogLogic.deleteBlog(entity);
         BlogEntity deleted = em.find( BlogEntity.class, entity.getId());
         Assert.assertNull(deleted);
    }
    
     @Test
    public void updateBlogTest() throws BusinessLogicException
    {
         BlogEntity entity = BlogData.get(0);
         BlogEntity pojoEntity = factory.manufacturePojo( BlogEntity.class);

        pojoEntity.setId(entity.getId());

         BlogLogic.updateBlog(pojoEntity);

         BlogEntity resp = em.find( BlogEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getTema(), resp.getTema());
    }
}