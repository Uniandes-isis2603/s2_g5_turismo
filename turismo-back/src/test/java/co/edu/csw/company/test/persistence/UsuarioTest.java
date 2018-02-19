/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.persistence;

import co.edu.uniandes.csw.turismo.entities.UsuarioEntity;
import co.edu.uniandes.csw.turismo.persistence.UsuarioPersistence;
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
public class UsuarioTest {
    
    public UsuarioTest() {
    }
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase UsuarioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private UsuarioPersistence usuarioPersistence;
    
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
    
    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity nuevo = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity resultado = usuarioPersistence.create(nuevo);
        
        Assert.assertNotNull(resultado);
        
        UsuarioEntity entity = em.find(UsuarioEntity.class, resultado.getId());
        Assert.assertEquals(nuevo.getNombre(), entity.getNombre());
        Assert.assertEquals(nuevo.getApellido(), entity.getApellido());
        Assert.assertEquals(nuevo.getContrasenia(), entity.getContrasenia());
        Assert.assertEquals(nuevo.getCorreo(), entity.getCorreo());
        Assert.assertEquals(nuevo.getTelefono(), entity.getTelefono());
        Assert.assertEquals(nuevo.getEsAdministrador(), entity.getEsAdministrador());
    }
    
    @Test
    public void findAllTest()
    {
        List<UsuarioEntity> list = usuarioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        
        for (UsuarioEntity usuarioEntity : list) 
        {
            boolean encontrado = false;
            for (UsuarioEntity usuarioEntity1 : data) 
            {
                if (usuarioEntity.getId().equals(usuarioEntity1.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void findTest()
    {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity nuevo = usuarioPersistence.find(entity.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(nuevo.getNombre(), entity.getNombre());
        Assert.assertEquals(nuevo.getApellido(), entity.getApellido());
        Assert.assertEquals(nuevo.getContrasenia(), entity.getContrasenia());
        Assert.assertEquals(nuevo.getCorreo(), entity.getCorreo());
        Assert.assertEquals(nuevo.getTelefono(), entity.getTelefono());
        Assert.assertEquals(nuevo.getEsAdministrador(), entity.getEsAdministrador());
    }
    
    @Test
    public void updateTest()
    {
        UsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity nuevo = factory.manufacturePojo(UsuarioEntity.class);
        
        nuevo.setId(entity.getId());
        
        usuarioPersistence.update(nuevo);
        
        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        
        Assert.assertEquals(nuevo.getNombre(), resp.getNombre());
        Assert.assertEquals(nuevo.getApellido(), resp.getApellido());
        Assert.assertEquals(nuevo.getContrasenia(), resp.getContrasenia());
        Assert.assertEquals(nuevo.getCorreo(), resp.getCorreo());
        Assert.assertEquals(nuevo.getTelefono(), resp.getTelefono());
        Assert.assertEquals(nuevo.getEsAdministrador(), resp.getEsAdministrador());
    }
    
    @Test
    public void deleteTest()
    {
        UsuarioEntity entity = data.get(0);
        usuarioPersistence.delete(entity.getId());
        UsuarioEntity borrado = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(borrado);
    }
}
