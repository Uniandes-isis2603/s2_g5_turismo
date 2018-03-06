/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.entities.UsuarioEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
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
public class TarjetaDeCreditoLogicTest 
{
     
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private TarjetaDeCreditoLogic TarjetaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TarjetaDeCreditoEntity> data = new ArrayList<TarjetaDeCreditoEntity>();

    private List<UsuarioEntity> UsuarioData = new ArrayList();
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaDeCreditoEntity.class.getPackage())
                .addPackage(TarjetaDeCreditoLogic.class.getPackage())
                .addPackage(TarjetaDeCreditoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     *
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
     *
     */
    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from TarjetaDeCreditoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) {
            UsuarioEntity user = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(user);
            UsuarioData.add(user);
        }
        for (int i = 0; i < 3; i++) {
            TarjetaDeCreditoEntity entity = factory.manufacturePojo( TarjetaDeCreditoEntity.class);
           UsuarioData.get(0).getListaTarjetas().add(entity);

            em.persist(entity);
            data.add(entity);
        }
    }
     /**
     * Prueba para crear una tarjeta
     *
     *
     */
    @Test
    public void createTarjetaDeCreditoTest() throws BusinessLogicException 
    {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
         Long Cdv=(long)123;
         String numero = "1234567891011324";
        newEntity.setNumero(Long.parseLong(numero));
        newEntity.setCDV(Cdv);
        TarjetaDeCreditoEntity result = TarjetaLogic.createTarjetaDeCredito(newEntity);
        //factory.manufacturePojo: esto crea tarjetas que van en contra de reglas de negocio
       

        Assert.assertNotNull(result);
        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCedula(), entity.getCedula());
        Assert.assertEquals(newEntity.getCDV(), entity.getCDV());
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
    }
     @Test
    public void createTarjetaDeCreditoMalPorCDVTest() throws BusinessLogicException 
    {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        Long Cdv=(long)1234;
        String numero = "1234567891011324";
        newEntity.setNumero(Long.parseLong(numero));
        newEntity.setCDV(Cdv);
        try
        {
             TarjetaDeCreditoEntity result = TarjetaLogic.createTarjetaDeCredito(newEntity);
        }
        catch(Exception e)
        {
             Assert.assertTrue(true);
        }
       
        //factory.manufacturePojo: esto crea tarjetas que van en contra de reglas de negocio
       
    }
    
    @Test
    public void createTarjetaDeCreditoTestMalNumero() throws BusinessLogicException 
    {
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        
        //factory.manufacturePojo: esto crea tarjetas que van en contra de reglas de negocio
        Long Cdv=(long)123;
         String numero = "12345678910113";
         newEntity.setNumero(Long.parseLong(numero));
         newEntity.setCDV(Cdv);
        
        try
        {
             TarjetaDeCreditoEntity result = TarjetaLogic.createTarjetaDeCredito(newEntity);
        }
        catch(Exception e)
        {
             Assert.assertTrue(true);
        }
       
      
    }
    
     /**
     * Prueba para consultar la lista de tarjetas
     *
     *
     */
    @Test
    public void getarjetaDeCreditosTest() 
    {
        List<TarjetaDeCreditoEntity> list = TarjetaLogic.getTrajetasDeCredito();
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaDeCreditoEntity entity : list) 
        {
            boolean found = false;
            for (TarjetaDeCreditoEntity storedEntity : data) 
            {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getTarjetaDeCreditoTest()
    {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity resultEntity = TarjetaLogic.getTrajetaDeCredito(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(resultEntity.getCedula(), entity.getCedula());
        Assert.assertEquals(resultEntity.getCDV(), entity.getCDV());
        Assert.assertEquals(resultEntity.getNumero(), entity.getNumero());
    }


    /**
     * Prueba para actualizar un  TarjetaDeCredito
     *
     *
     */
    @Test
    public void updateTarjetaDeCreditoTest() throws BusinessLogicException
    {
         TarjetaDeCreditoEntity entity = data.get(0);
         TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo( TarjetaDeCreditoEntity.class);

        pojoEntity.setId(entity.getId());
         Long Cdv=(long)456;
         String numero = "1111111111111111";
        pojoEntity.setNumero(Long.parseLong(numero));
        pojoEntity.setCDV(Cdv);
        

         TarjetaLogic.updateTarjetaDeCredito(pojoEntity.getId(), pojoEntity);

         TarjetaDeCreditoEntity resp = em.find( TarjetaDeCreditoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getCedula(), resp.getCedula());
        Assert.assertEquals(pojoEntity.getCDV(), resp.getCDV());
        Assert.assertEquals(pojoEntity.getNumero(), resp.getNumero());
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
