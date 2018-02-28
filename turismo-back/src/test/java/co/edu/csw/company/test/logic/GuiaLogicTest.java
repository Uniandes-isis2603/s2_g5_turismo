package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.GuiaLogic;
import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.GuiaPersistence;
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
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @Guia ISIS2603
 */
@RunWith(Arquillian.class)
public class GuiaLogicTest 
{

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private GuiaLogic GuiaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<GuiaEntity> data = new ArrayList<GuiaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GuiaEntity.class.getPackage())
                .addPackage(GuiaLogic.class.getPackage())
                .addPackage(GuiaPersistence.class.getPackage())
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
        em.createQuery("delete from GuiaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            GuiaEntity entity = factory.manufacturePojo(GuiaEntity.class);
            em.persist(entity);
            data.add(entity);
         
        }

    }

    /**
     * Prueba para crear un Guia
     *
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void createGuiaTest() throws BusinessLogicException 
    {
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);
        //factory.manufacturePojo: esto crea nombres de guia que van en contra de reglas de negocio
        newEntity.setName("nombre");
        GuiaEntity result = GuiaLogic.createGuia(newEntity);
        Assert.assertNotNull(result);
        GuiaEntity entity = em.find(GuiaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getIdiomaGuia(), entity.getIdiomaGuia());

    }

    /**
     * Prueba para consultar la lista de Guias
     *
     *
     */
    @Test
    public void getGuiasTest() {
        List<GuiaEntity> list = GuiaLogic.getGuias();
        Assert.assertEquals(data.size(), list.size());
        for (GuiaEntity entity : list) {
            boolean found = false;
            for (GuiaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Guia
     *
     *
     */
    @Test
    public void getGuiaTest() {
        GuiaEntity entity = data.get(0);
        GuiaEntity resultEntity = GuiaLogic.getGuia(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getIdiomaGuia(), resultEntity.getIdiomaGuia());
    }

    /**
     * Prueba para eliminar un Guia
     *
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void deleteGuiaTest() throws BusinessLogicException 
    {
        GuiaEntity entity = data.get(0);
        GuiaLogic.deleteGuia(entity.getId());
        GuiaEntity deleted = em.find(GuiaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Guia
     *
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void updateGuiaTest() throws BusinessLogicException  
    {
        GuiaEntity entity = data.get(0);
        GuiaEntity pojoEntity = factory.manufacturePojo(GuiaEntity.class);

        pojoEntity.setId(entity.getId());
        
        //factory.manufacturePojo: esto crea nombres de guia que van en contra de reglas de negocio
        pojoEntity.setName("nombre");
        GuiaLogic.updateGuia(pojoEntity);

        GuiaEntity resp = em.find(GuiaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getIdiomaGuia(), resp.getIdiomaGuia());
    }
    
    @Test
    public void testReglaNegocioNombreNoNullNoNumerosYTamanioMayorA0() throws BusinessLogicException 
    {
        //En ninguno de los siguientes casos de debería agregar el guia
        
        //nombre sin caracteres
        Boolean seAgrego = true;
        GuiaEntity enti = new GuiaEntity();
        enti.setIdiomaGuia("Espaniol");
        enti.setName("");
        try 
        {
            GuiaLogic.createGuia(enti);
        }
        catch (BusinessLogicException e) 
        {
            seAgrego = false;
        }
        Assert.assertFalse(seAgrego);
        
        //nombre null
        seAgrego = true;
        GuiaEntity enti2 = new GuiaEntity();
        enti2.setName(null);
        enti2.setIdiomaGuia("Espaniol");
        try 
        {
            GuiaLogic.createGuia(enti2);
        }
        catch (BusinessLogicException e) 
        {
            seAgrego = false;
        }
        Assert.assertFalse(seAgrego);
        
        //nombre con numeros
        seAgrego = true;
        GuiaEntity enti3 = new GuiaEntity();
        enti3.setName("43242");
        enti3.setIdiomaGuia("Espaniol");
        try 
        {
            GuiaLogic.createGuia(enti3);
        }
        catch (BusinessLogicException e) 
        {
            seAgrego = false;
        }
        Assert.assertFalse(seAgrego);
        
        //idioma sin caracteres
        seAgrego = true;
        GuiaEntity enti4 = new GuiaEntity();
        enti4.setName("oli");
        enti4.setIdiomaGuia("");
        try 
        {
            GuiaLogic.createGuia(enti4);
        }
        catch (BusinessLogicException e) 
        {
            seAgrego = false;
        }
        Assert.assertFalse(seAgrego);
        
        //idioma null
        seAgrego = true;
        GuiaEntity enti5 = new GuiaEntity();
        enti5.setName("oli");
        enti5.setIdiomaGuia(null);
        try 
        {
            GuiaLogic.createGuia(enti5);
        }
        catch (BusinessLogicException e) 
        {
            seAgrego = false;
        }
        Assert.assertFalse(seAgrego);

        
    }

}
    

