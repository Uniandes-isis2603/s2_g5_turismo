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
    private List<FacturaEntity> listaFacturas2 = new ArrayList<>();
    
    private List<TarjetaDeCreditoEntity> listaTarjetas = new ArrayList<>();
    
    private PaqueteTuristicoEntity paquete = new PaqueteTuristicoEntity();
    
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
        for (int i = 0; i < 3; i++) {
            FacturaEntity facturas2 = factory.manufacturePojo(FacturaEntity.class);
            em.persist(facturas2);
            listaFacturas2.add(facturas2);
        }
        paquete = factory.manufacturePojo(PaqueteTuristicoEntity.class);
        em.persist(paquete);
        
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            
            entity.setListaBlogs(listaBlogs);
            entity.setListaComentarios(listaComentarios);
            entity.setListaFacturas(listaFacturas);
            entity.setListaPreferencias(listaPreferencias);
            entity.setListaTarjetas(listaTarjetas);
            entity.setPaquete(paquete);
            entity.setEsAdministrador(false);
            
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
        nuevo.setCorreo("usuario@gmail.com");
        UsuarioEntity result = usuarioLogic.createUsuario(nuevo);
        Assert.assertNotNull(result);
        
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
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
        
        Assert.assertEquals(entity, nuevo);
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
        pojoEntity.setListaBlogs(new ArrayList<>());
        pojoEntity.setPaquete(entity.getPaquete());
        pojoEntity.setListaComentarios(new ArrayList<>());
        pojoEntity.setListaTarjetas(new ArrayList<>());
        pojoEntity.setCorreo("usuario@gmail.com");
        pojoEntity.setListaPreferencias(listaPreferencias);
        pojoEntity.setListaTarjetas(listaTarjetas);
        

        usuarioLogic.updateUsuario(pojoEntity);

        UsuarioEntity nuevo = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertEquals(nuevo.getId(), pojoEntity.getId());
    }
    
//    @Test
//    public void getPaqueteTest()
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        PaqueteTuristicoEntity response = usuarioLogic.getPaquete(paquete.getId());
//        
//        Assert.assertEquals(paquete.getId(), response.getId());
//        Assert.assertEquals(paquete.getPagos(), response.getPagos());
//    }
    
//    @Test
//    public void createPaqueteTest()
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        PaqueteTuristicoEntity response = usuarioLogic.createPaquete(paquete.getId(), entity.getId());
//        
//        Assert.assertNotNull(response);
//        Assert.assertEquals(response.getId(), paquete.getId());
//    }
//    
//    @Test
//    public void updatePaqueteTest()
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        PaqueteTuristicoEntity response = usuarioLogic.updatePaquete(paquete.getId(), entity.getId());
//        
//        Assert.assertEquals(response.getId(), paquete.getId());
//    }
//    
//    @Test
//    public void deletePaqueteTest() throws BusinessLogicException
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//
//        usuarioLogic.deletePaquete(paquete.getId(), entity.getId());
//        PaqueteTuristicoEntity response = usuarioLogic.getPaquete(paquete.getId());
//
//        Assert.assertNull(response);
//    }
//    
//    @Test
//    public void getBlogsTest()
//    {
//        List<BlogEntity> list = usuarioLogic.getBlogs(listaUsuarios.get(0).getId());
//        Assert.assertEquals(3, list.size());
//    }
//    
//    @Test
//    public void getBlogTest() throws BusinessLogicException
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        BlogEntity bl = listaBlogs.get(0);
//        BlogEntity response = usuarioLogic.getBlog(entity.getId(), bl.getId());
//        
//        Assert.assertEquals(response, bl);
//    }
//    
//    @Test
//    public void createBlogTest() throws BusinessLogicException
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        BlogEntity bl = listaBlogs.get(0);
//        BlogEntity response = usuarioLogic.createBlog(entity.getId(), bl.getId());
//        
//        Assert.assertNotNull(response);
//        Assert.assertEquals(response, bl);
//    }
//    
//    @Test
//    public void updateBlogTest()
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        List<BlogEntity> list = listaBlogs.subList(1, 3);
//        usuarioLogic.updateBlog(entity.getId(), list);
//
//        entity = usuarioLogic.getUsuario(entity.getId());
//        Assert.assertFalse(entity.getListaBlogs().contains(listaBlogs.get(0)));
//        Assert.assertTrue(entity.getListaBlogs().contains(listaBlogs.get(1)));
//        Assert.assertTrue(entity.getListaBlogs().contains(listaBlogs.get(2)));
//    }
//    
//    @Test
//    public void deleteBlogTest() throws BusinessLogicException
//    {
//        usuarioLogic.deleteBlog(listaUsuarios.get(0).getId(), listaBlogs.get(0).getId());
//        
//        Assert.assertNotNull(em.find(BlogEntity.class, listaBlogs.get(0).getId()));
//    }
//    
//    @Test
//    public void getComentaiosTest()
//    {
//        List<ComentarioEntity> list = usuarioLogic.getComentarios(listaUsuarios.get(0).getId());
//        Assert.assertEquals(3, list.size());
//    }
//    
//    @Test
//    public void getcomentarioTest() throws BusinessLogicException
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        ComentarioEntity co = listaComentarios.get(0);
//        ComentarioEntity response = usuarioLogic.getComentario(entity.getId(), co.getId());
//        
//        Assert.assertEquals(response, co);
//    }
//    
//    @Test
//    public void createComentarioTest() throws BusinessLogicException
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        ComentarioEntity co = listaComentarios.get(0);
//        ComentarioEntity response = usuarioLogic.createComentario(entity.getId(), co.getId());
//        
//        Assert.assertNotNull(response);
//        Assert.assertEquals(response, co);
//    }
//    
//    @Test
//    public void updateComentarioTest()
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        List<ComentarioEntity> list = listaComentarios.subList(1, 3);
//        usuarioLogic.updateComentario(entity.getId(), list);
//
//        entity = usuarioLogic.getUsuario(entity.getId());
//        Assert.assertFalse(entity.getListaComentarios().contains(listaComentarios.get(0)));
//        Assert.assertTrue(entity.getListaComentarios().contains(listaComentarios.get(1)));
//        Assert.assertTrue(entity.getListaComentarios().contains(listaComentarios.get(2)));
//    }
//    
//    @Test
//    public void deleteComentarioTest() throws BusinessLogicException
//    {
//        usuarioLogic.deleteComentario(listaUsuarios.get(0).getId(), listaComentarios.get(0).getId());
//        
//        Assert.assertNotNull(em.find(ComentarioEntity.class, listaComentarios.get(0).getId()));
//    }
//    
//    @Test
//    public void getPreferenciasTest()
//    {
//        List<PreferenciasEntity> list = usuarioLogic.getPreferencias(listaUsuarios.get(0).getId());
//        Assert.assertEquals(3, list.size());
//    }
//    
//    @Test
//    public void getPreferenciaTest() throws BusinessLogicException
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        PreferenciasEntity pr = listaPreferencias.get(0);
//        PreferenciasEntity response = usuarioLogic.getPreferencia(entity.getId(), pr.getId());
//        
//        Assert.assertEquals(response, pr);
//    }
//    
//    @Test
//    public void createPreferenciaTest()
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        PreferenciasEntity pr = listaPreferencias.get(0);
//        PreferenciasEntity response = usuarioLogic.createPreferencia(entity.getId(), pr.getId());
//        
//        Assert.assertNotNull(response);
//        Assert.assertEquals(response, pr);
//    }
//    
//    @Test
//    public void updatePreferenciaTest()
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        List<PreferenciasEntity> list = listaPreferencias.subList(1, 3);
//        usuarioLogic.updatePreferencia(entity.getId(), list);
//
//        entity = usuarioLogic.getUsuario(entity.getId());
//        Assert.assertFalse(entity.getListaPreferencias().contains(listaPreferencias.get(0)));
//        Assert.assertTrue(entity.getListaPreferencias().contains(listaPreferencias.get(1)));
//        Assert.assertTrue(entity.getListaPreferencias().contains(listaPreferencias.get(2)));
//    }
//    
//    @Test
//    public void deletePreferenciaTest()
//    {
//        usuarioLogic.deletePreferencia(listaUsuarios.get(0).getId(), listaPreferencias.get(0).getId());
//        
//        Assert.assertNotNull(em.find(PreferenciasEntity.class, listaPreferencias.get(0).getId()));
//    }
//    
//    @Test
//    public void getFacturasTest()
//    {
//        List<FacturaEntity> list = usuarioLogic.getFacturas(listaUsuarios.get(0).getId());
//        Assert.assertEquals(3, list.size());
//    }
//    
//    @Test
//    public void getFacturaTest() throws BusinessLogicException
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        FacturaEntity fa = listaFacturas2.get(0);
//        FacturaEntity response = usuarioLogic.getFactura(entity.getId(), fa.getId());
//        
//        Assert.assertEquals(response, fa);
//    }
//    
//    @Test
//    public void createFacturaTest()
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        FacturaEntity fa = listaFacturas2.get(0);
//        FacturaEntity response = usuarioLogic.createFactura(entity.getId(), fa.getId());
//        
//        Assert.assertNotNull(response);
//        Assert.assertEquals(response, fa);
//    }
//    
//    @Test
//    public void updateFacturaTest()
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        List<FacturaEntity> list = listaFacturas.subList(1, 3);
//        usuarioLogic.updateFactura(entity.getId(), list);
//
//        entity = usuarioLogic.getUsuario(entity.getId());
//        Assert.assertFalse(entity.getListaFacturas().contains(listaFacturas.get(0)));
//        Assert.assertTrue(entity.getListaFacturas().contains(listaFacturas.get(1)));
//        Assert.assertTrue(entity.getListaFacturas().contains(listaFacturas.get(2)));
//    }
//    
//    @Test
//    public void deleteFacturaTest()
//    {
//        usuarioLogic.deleteFactura(listaUsuarios.get(0).getId(), listaFacturas.get(0).getId());
//        
//        Assert.assertNull(listaFacturas.get(0));
//    }
//    
//    @Test
//    public void getTarjetasTest()
//    {
//        List<TarjetaDeCreditoEntity> list = usuarioLogic.getTarjetas(listaUsuarios.get(0).getId());
//        Assert.assertEquals(3, list.size());
//    }
//    
//    @Test
//    public void getTarjetaTest() throws BusinessLogicException
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        TarjetaDeCreditoEntity ta = listaTarjetas.get(0);
//        TarjetaDeCreditoEntity response = usuarioLogic.getTarjeta(entity.getId(), ta.getId());
//        
//        Assert.assertEquals(response, ta);
//    }
//    
//    @Test
//    public void createTarjetaTest()
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        TarjetaDeCreditoEntity ta = listaTarjetas.get(0);
//        TarjetaDeCreditoEntity response = usuarioLogic.createTarjeta(entity.getId(), ta.getId());
//        
//        Assert.assertNotNull(response);
//        Assert.assertEquals(response, ta);
//    }
//    
//    @Test
//    public void updateTarjeta()
//    {
//        UsuarioEntity entity = listaUsuarios.get(0);
//        List<TarjetaDeCreditoEntity> list = listaTarjetas.subList(1, 3);
//        usuarioLogic.updateTarjeta(entity.getId(), list);
//
//        entity = usuarioLogic.getUsuario(entity.getId());
//        Assert.assertFalse(entity.getListaTarjetas().contains(listaTarjetas.get(0)));
//        Assert.assertTrue(entity.getListaTarjetas().contains(listaTarjetas.get(1)));
//        Assert.assertTrue(entity.getListaTarjetas().contains(listaTarjetas.get(2)));
//    }
//    
//    @Test
//    public void deleteTarjetaTest()
//    {
//        usuarioLogic.deleteTarjeta(listaUsuarios.get(0).getId(), listaTarjetas.get(0).getId());
//        
//        Assert.assertNull(em.find(TarjetaDeCreditoEntity.class, listaTarjetas.get(0).getId()));
//    }
}
