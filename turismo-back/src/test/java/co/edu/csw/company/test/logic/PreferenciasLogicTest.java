package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.PreferenciasLogic;
import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.PreferenciasPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import podam.StringSinNumerosStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @Preferencias ISIS2603
 */
@RunWith(Arquillian.class)
public class PreferenciasLogicTest 
{

    private StringSinNumerosStrategy stringSinNumerosStrategy = new StringSinNumerosStrategy();
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PreferenciasLogic PreferenciasLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PreferenciasEntity> data = new ArrayList<PreferenciasEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PreferenciasEntity.class.getPackage())
                .addPackage(PreferenciasLogic.class.getPackage())
                .addPackage(PreferenciasPersistence.class.getPackage())
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
    private void clearData() 
    {
        em.createQuery("delete from PreferenciasEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            PreferenciasEntity entity = factory.manufacturePojo(PreferenciasEntity.class);
            em.persist(entity);
            data.add(entity);
         
        }

    }

    /**
     * Prueba para crear un Preferencias
     *
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void createPreferenciasTest() throws BusinessLogicException 
    {
        PreferenciasEntity newEntity = factory.manufacturePojo(PreferenciasEntity.class);
        PreferenciasEntity result = PreferenciasLogic.createPreferencias(newEntity);
        Assert.assertNotNull(result);
        PreferenciasEntity entity = em.find(PreferenciasEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTipoPlan(), entity.getTipoPlan());

    }

    /**
     * Prueba para consultar la lista de Preferenciass
     *
     *
     */
    @Test
    public void getPreferenciassTest() {
        List<PreferenciasEntity> list = PreferenciasLogic.getPreferenciass();
        Assert.assertEquals(data.size(), list.size());
        for (PreferenciasEntity entity : list) {
            boolean found = false;
            for (PreferenciasEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Preferencias
     *
     *
     */
    @Test
    public void getPreferenciasTest() {
        PreferenciasEntity entity = data.get(0);
        PreferenciasEntity resultEntity = PreferenciasLogic.getPreferencias(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getTipoPlan(), resultEntity.getTipoPlan());
    }

    /**
     * Prueba para eliminar un Preferencias
     *
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void deletePreferenciasTest() throws BusinessLogicException 
    {
        PreferenciasEntity entity = data.get(0);
        PreferenciasLogic.deletePreferencias(entity.getId());
        PreferenciasEntity deleted = em.find(PreferenciasEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Preferencias
     *
     *
     */
    @Test
    public void updatePreferenciasTest() throws BusinessLogicException 
    {
        PreferenciasEntity entity = data.get(0);
        PreferenciasEntity pojoEntity = factory.manufacturePojo(PreferenciasEntity.class);

        pojoEntity.setId(entity.getId());

        PreferenciasLogic.updatePreferencias(pojoEntity);

        PreferenciasEntity resp = em.find(PreferenciasEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getTipoPlan(), resp.getTipoPlan());
    }
    
    /**
     * Prueba que confirma la regla de negocio de crear categorias (preferencias) que ya existen
     * @throws BusinessLogicException 
     */
    @Test
    public void testReglaNegocioNoRepeticiones() throws BusinessLogicException 
    {
        PreferenciasEntity enti1 = new PreferenciasEntity();
        PreferenciasEntity enti2 = new PreferenciasEntity();
        String unStringRandom = stringSinNumerosStrategy.getValue();
        enti1.setTipoPlan(unStringRandom);
        enti2.setTipoPlan(unStringRandom);
        PreferenciasLogic.createPreferencias(enti1);
        boolean seAgrego = true;
        try 
        {
            PreferenciasLogic.createPreferencias(enti2);          
        } 
        
        catch (BusinessLogicException e) 
        {
            //Si llega aca la prueba salio bien porque se intento agregar una preferencia repetida
            seAgrego = false;
        }
        Assert.assertFalse(seAgrego);
        
    }

}
    

