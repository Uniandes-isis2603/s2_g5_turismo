/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.FacturaLogic;
import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.FacturaPersistence;
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
public class FacturaLogicTest 

{
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FacturaLogic FacturaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private TarjetaDeCreditoEntity  Tarjetadata;

    private PaqueteTuristicoEntity PaqueteData;
    
    private List<FacturaEntity> FacturaData = new ArrayList<FacturaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
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
     private void clearData() 
     {
        em.createQuery("delete from FacturaEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from TarjetaDeCreditoEntity").executeUpdate(); 
        em.createQuery("delete from PaqueteTuristicoEntity").executeUpdate();
      
    }
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() 
    {
       
           TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
            em.persist(tarjeta);
            Tarjetadata=tarjeta;
            
            PaqueteTuristicoEntity paquete = factory.manufacturePojo(PaqueteTuristicoEntity.class);
            em.persist(paquete);
            PaqueteData= paquete;
        
        
        for (int i = 0; i < 3; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            entity.setPaqueteturistico(PaqueteData);
            entity.setTarjetadecredito(Tarjetadata);

            em.persist(entity);
            FacturaData.add(entity);
        }
    }
    
    @Test
    public void createFacturaTest() throws BusinessLogicException 
    {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);       
        FacturaEntity result = FacturaLogic.createFactura(newEntity);
        //factory.manufacturePojo: esto crea tarjetas que van en contra de reglas de negocio
       

        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
    }
    
    @Test
    public void getFacturaTest() throws BusinessLogicException 
    {
        List<FacturaEntity> list = FacturaLogic.getFacturas();
        Assert.assertEquals(FacturaData.size(), list.size());
        for (FacturaEntity entity : list) 
        {
            boolean found = false;
            for (FacturaEntity storedEntity : FacturaData) 
            {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void deletefacturaTest()
    {
         FacturaEntity entity = FacturaData.get(0);
         FacturaLogic.deleteFactura(entity.getId());
         FacturaEntity deleted = em.find( FacturaEntity.class, entity.getId());
         Assert.assertNull(deleted);
    }
    
     @Test
    public void updateFacturaTest() throws BusinessLogicException
    {
         FacturaEntity entity = FacturaData.get(0);
         FacturaEntity pojoEntity = factory.manufacturePojo( FacturaEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setPaqueteturistico(PaqueteData);
        pojoEntity.setTarjetadecredito(Tarjetadata);

         FacturaLogic.updateFactura(pojoEntity.getId(), pojoEntity);

         FacturaEntity resp = em.find( FacturaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getCosto(), resp.getCosto());
    }
        


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
