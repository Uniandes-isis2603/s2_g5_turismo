/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.UsuarioLogic;
import co.edu.uniandes.csw.turismo.entities.BlogEntity;
import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;
import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.entities.UsuarioEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
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
public class UsuarioLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<UsuarioEntity> listaUsuarios = new ArrayList<>();
    
    private List<FacturaEntity> listaFacturas = new ArrayList<>();
    
    private List<TarjetaDeCreditoEntity> listaTarjetas = new ArrayList<>();
    
    private PaqueteTuristicoEntity paquete;
    
    private List<BlogEntity> listaBlogs = new ArrayList<>();
    
    private List<ComentarioEntity> listaComentarios = new ArrayList<>();
    
    private List<PreferenciasEntity> listaPreferencias  = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
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

    private void insertData() {
        
        
        for (int i = 0; i < 3; i++) {
            FacturaEntity factura = factory.manufacturePojo(FacturaEntity.class);
            em.persist(factura);
            listaFacturas.add(factura);
        }
        for (int i = 0; i < 3; i++) {
            PreferenciasEntity preferencias = factory.manufacturePojo(PreferenciasEntity.class);
            em.persist(preferencias);
            listaPreferencias.add(preferencias);
        }
        for (int i = 0; i < 3; i++) {
            BlogEntity blogs = factory.manufacturePojo(BlogEntity.class);
            em.persist(blogs);
            listaBlogs.add(blogs);
        }
        for (int i = 0; i < 3; i++) {
            ComentarioEntity comentarios = factory.manufacturePojo(ComentarioEntity.class);
            em.persist(comentarios);
            listaComentarios.add(comentarios);
        }
        for (int i = 0; i < 3; i++) {
            TarjetaDeCreditoEntity tarjetas = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
            em.persist(tarjetas);
            listaTarjetas.add(tarjetas);
        }
        PaqueteTuristicoEntity paq = factory.manufacturePojo(PaqueteTuristicoEntity.class);
        em.persist(paq);
        
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            
            entity.setListaBlogs(listaBlogs);
            entity.setListaComentarios(listaComentarios);
            entity.setListaFacturas(listaFacturas);
            entity.setListaPreferencias(listaPreferencias);
            entity.setListaTarjetas(listaTarjetas);
            entity.setPaquete(paquete);
            
            em.persist(entity);
            listaUsuarios.add(entity);
        }
    }
    
    @Test
    public void createUsuarioTest() throws BusinessLogicException
    {
        UsuarioEntity nuevo = factory.manufacturePojo(UsuarioEntity.class);
        nuevo.setListaBlogs(listaBlogs);
        nuevo.setListaComentarios(listaComentarios);
        nuevo.setListaFacturas(listaFacturas);
        nuevo.setListaPreferencias(listaPreferencias);
        nuevo.setListaTarjetas(listaTarjetas);
        nuevo.setPaquete(paquete);
        UsuarioEntity result = usuarioLogic.createUsuario(nuevo);
        Assert.assertNotNull(result);
        
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        Assert.assertEquals(nuevo.getPaquete(), entity.getPaquete());
        Assert.assertEquals(nuevo.getListaTarjetas(), entity.getListaTarjetas());
        Assert.assertEquals(nuevo.getListaFacturas(), entity.getListaFacturas());
        Assert.assertEquals(nuevo.getListaPreferencias(), entity.getListaPreferencias());
        Assert.assertEquals(nuevo.getListaBlogs(), entity.getListaBlogs());
        Assert.assertEquals(nuevo.getListaComentarios(), entity.getListaComentarios());
    }
    
    @Test
    public void getUsuariosTest() throws BusinessLogicException
    {
        List<UsuarioEntity> list = usuarioLogic.getUsuarios();
        Assert.assertEquals(listaUsuarios.size(), list.size());
        
        for (UsuarioEntity usuarioEntity : list) 
        {
            boolean encontrado = false;
            for (UsuarioEntity usuarioEntity1 : listaUsuarios) 
            {
                if (usuarioEntity.getId().equals(usuarioEntity1.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void getUsuarioTest()
    {
        UsuarioEntity entity = listaUsuarios.get(0);
        UsuarioEntity nuevo = usuarioLogic.getUsuario(entity.getId());
        Assert.assertNotNull(entity);
        
        Assert.assertEquals(nuevo.getPaquete(), entity.getPaquete());
        Assert.assertEquals(nuevo.getListaTarjetas(), entity.getListaTarjetas());
        Assert.assertEquals(nuevo.getListaFacturas(), entity.getListaFacturas());
        Assert.assertEquals(nuevo.getListaPreferencias(), entity.getListaPreferencias());
        Assert.assertEquals(nuevo.getListaBlogs(), entity.getListaBlogs());
        Assert.assertEquals(nuevo.getListaComentarios(), entity.getListaComentarios());
    }
    
    @Test
    public void deleteUsuarioTest() throws BusinessLogicException
    {
        UsuarioEntity entity = listaUsuarios.get(0);
        Long idBlog = listaUsuarios.get(0).getListaBlogs().get(0).getId();
        Long idComen =listaUsuarios.get(0).getListaComentarios().get(0).getId();
        Long idPaquete = listaUsuarios.get(0).getPaquete().getId();
        Long idFact = listaUsuarios.get(0).getListaFacturas().get(0).getId();
        Long idPref = listaUsuarios.get(0).getListaPreferencias().get(0).getId();
        Long idTarj = listaUsuarios.get(0).getListaTarjetas().get(0).getId();
        
        
        
        usuarioLogic.deleteUsuario(entity.getId());
        
        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        
        Assert.assertNull(resp);
        
    }
    
    @Test
    public void updateUsuarioTest() throws BusinessLogicException
    {
        UsuarioEntity entity = listaUsuarios.get(0);
        UsuarioEntity pojoEntity = factory.manufacturePojo(UsuarioEntity.class);
        pojoEntity.setId(entity.getId());

        usuarioLogic.updateUsuario(pojoEntity);

        UsuarioEntity nuevo = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertEquals(nuevo.getPaquete(), entity.getPaquete());
        Assert.assertEquals(nuevo.getListaTarjetas(), entity.getListaTarjetas());
        Assert.assertEquals(nuevo.getListaFacturas(), entity.getListaFacturas());
        Assert.assertEquals(nuevo.getListaPreferencias(), entity.getListaPreferencias());
        Assert.assertEquals(nuevo.getListaBlogs(), entity.getListaBlogs());
        Assert.assertEquals(nuevo.getListaComentarios(), entity.getListaComentarios());
    }
}
