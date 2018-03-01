/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.ValoracionesLogic;
import co.edu.uniandes.csw.turismo.entities.ValoracionesEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.ValoracionesPersistence;
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
 * @author jf.gutierrez13
 */
@RunWith(Arquillian.class)
public class ValoracionesLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ValoracionesLogic valoracionesLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ValoracionesEntity> listaValoraciones;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ValoracionesEntity.class.getPackage())
                .addPackage(ValoracionesLogic.class.getPackage())
                .addPackage(ValoracionesPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
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

    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    private void insertData()
    {
        for (int i = 0; i < 3; i++) 
        {
            ValoracionesEntity entity = factory.manufacturePojo(ValoracionesEntity.class);
            em.persist(entity);
            listaValoraciones.add(entity);
        }
    }
           
    @Test
    public void createValoracionTest() throws BusinessLogicException
    {
        ValoracionesEntity nuevo = factory.manufacturePojo(ValoracionesEntity.class);
        
        ValoracionesEntity result = valoracionesLogic.createValoracion(nuevo);
        
        Assert.assertNotNull(result);
        
        ValoracionesEntity entity = em.find(ValoracionesEntity.class, result.getId());
        Assert.assertEquals(nuevo.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(nuevo.getComentario(), entity.getComentario());
    }
    
    @Test
    public void findAllTest()
    {
        List<ValoracionesEntity> list = valoracionesLogic.getValoraciones();
        //Assert.assertEquals(data.size(), list.size());
        
        for (ValoracionesEntity entity : list) 
        {
            boolean encontrado = true;
            for (ValoracionesEntity entity1 : listaValoraciones) 
            {
                if (entity.getId().equals(entity1.getId()) && !encontrado) {
                    encontrado = false;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void findTest()
    {
        ValoracionesEntity entity = listaValoraciones.get(0);
        ValoracionesEntity nuevo = valoracionesLogic.getValoracion(entity.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(nuevo.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(nuevo.getComentario(), entity.getComentario());
    }
 
    @Test
    public void updateTest() throws BusinessLogicException
    {
        ValoracionesEntity entity = listaValoraciones.get(0);
        ValoracionesEntity pojoEntity = factory.manufacturePojo(ValoracionesEntity.class);
        pojoEntity.setId(entity.getId());

        valoracionesLogic.updateValoracion(pojoEntity);

        ValoracionesEntity nuevo = em.find(ValoracionesEntity.class, entity.getId());
    }
    
    @Test
    public void deleteTest()
    {
        ValoracionesEntity entity = listaValoraciones.get(0);
        valoracionesLogic.deleteValoracion(entity.getId());
        ValoracionesEntity borrado = em.find(ValoracionesEntity.class, entity.getId());
        Assert.assertNull(borrado);
    }
}
