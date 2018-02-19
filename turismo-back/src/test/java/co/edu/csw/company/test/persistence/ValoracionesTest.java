/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.persistence;

import co.edu.uniandes.csw.turismo.entities.ValoracionesEntity;
import co.edu.uniandes.csw.turismo.persistence.ValoracionesPersistence;
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
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jf.gutierrez13
 */
@RunWith(Arquillian.class)
public class ValoracionesTest {
    
    public ValoracionesTest() {
    }
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ValoracionesEntity.class.getPackage())
                .addPackage(ValoracionesPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase ValoracionesPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ValoracionesPersistence valoracionesPersistence;
    
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
     * Configuracion inicial de la clase.
     */
    @BeforeClass
    public void setUpClass() 
    {
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

    private void clearData() 
    {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    private List<ValoracionesEntity> data = new ArrayList<ValoracionesEntity>();

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ValoracionesEntity entity = factory.manufacturePojo(ValoracionesEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ValoracionesEntity nuevo = factory.manufacturePojo(ValoracionesEntity.class);
        ValoracionesEntity resultado = valoracionesPersistence.create(nuevo);
        
        Assert.assertNotNull(resultado);
        
        ValoracionesEntity entity = em.find(ValoracionesEntity.class, resultado.getId());
        Assert.assertEquals(nuevo.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(nuevo.getComentario(), entity.getComentario());
    }
    
    @Test
    public void findAllTest()
    {
        List<ValoracionesEntity> list = valoracionesPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        
        for (ValoracionesEntity entity : list) 
        {
            boolean encontrado = false;
            for (ValoracionesEntity entity1 : data) 
            {
                if (entity.getId().equals(entity1.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void findTest()
    {
        ValoracionesEntity entity = data.get(0);
        ValoracionesEntity nuevo = valoracionesPersistence.find(entity.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(nuevo.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(nuevo.getComentario(), entity.getComentario());
    }
    
    @Test
    public void updateTest()
    {
        ValoracionesEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ValoracionesEntity nuevo = factory.manufacturePojo(ValoracionesEntity.class);
        
        nuevo.setId(entity.getId());
        
        valoracionesPersistence.update(nuevo);
        
        ValoracionesEntity resp = em.find(ValoracionesEntity.class, entity.getId());
        
        Assert.assertEquals(nuevo.getCalificacion(), resp.getCalificacion());
        Assert.assertEquals(nuevo.getComentario(), resp.getComentario());
    }
    
    @Test
    public void deleteTest()
    {
        ValoracionesEntity entity = data.get(0);
        valoracionesPersistence.delete(entity.getId());
        ValoracionesEntity borrado = em.find(ValoracionesEntity.class, entity.getId());
        Assert.assertNull(borrado);
    }
}
