package co.edu.csw.company.test.persistence;

import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import co.edu.uniandes.csw.turismo.persistence.PlanPersistence;
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
 * @author  jc.montoyar
 */
@RunWith(Arquillian.class)
public class PlanPersistenceTest {

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Plan, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlanEntity.class.getPackage())
                .addPackage(PlanPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase PlanPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PlanPersistence PlanPersistence;

    /**
     * Contexto de Persostencia que se va autilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void setUp() {
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from PlanEntity").executeUpdate();
    }

    /**
     *
     */
    private List<PlanEntity> data = new ArrayList<PlanEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PlanEntity entity = factory.manufacturePojo(PlanEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Plan.
     *
     *
     */
    @Test
    public void createPlanTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PlanEntity newEntity = factory.manufacturePojo(PlanEntity.class);
        PlanEntity result = PlanPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PlanEntity entity = em.find(PlanEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getArchivo(), entity.getArchivo());
        Assert.assertEquals(newEntity.getCantidadPersonas(), entity.getCantidadPersonas());
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getDuracion(), entity.getDuracion());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
        Assert.assertEquals(newEntity.getPais(), entity.getPais());
        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(newEntity.getRestricciones(), entity.getRestricciones());
    }

    /**
     * Prueba para consultar la lista de Plans.
     *
     *
     */
    @Test
    public void getPlansTest() {
        List<PlanEntity> list = PlanPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PlanEntity ent : list) {
            boolean found = false;
            for (PlanEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Plan.
     *
     *
     */
    @Test
    public void getPlanTest() {
        PlanEntity entity = data.get(0);
        PlanEntity newEntity = PlanPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getArchivo(), entity.getArchivo());
        Assert.assertEquals(newEntity.getCantidadPersonas(), entity.getCantidadPersonas());
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getDuracion(), entity.getDuracion());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
        Assert.assertEquals(newEntity.getPais(), entity.getPais());
        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(newEntity.getRestricciones(), entity.getRestricciones());
    }

    /**
     * Prueba para eliminar un Plan.
     *
     *
     */
    @Test
    public void deletePlanTest() {
        PlanEntity entity = data.get(0);
        PlanPersistence.delete(entity.getId());
        PlanEntity deleted = em.find(PlanEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Plan.
     *
     *
     */
    @Test
    public void updatePlanTest() {
        PlanEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PlanEntity newEntity = factory.manufacturePojo(PlanEntity.class);

        newEntity.setId(entity.getId());

        PlanPersistence.update(newEntity);

        PlanEntity resp = em.find(PlanEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getArchivo(), resp.getArchivo());
        Assert.assertEquals(newEntity.getCantidadPersonas(), resp.getCantidadPersonas());
        Assert.assertEquals(newEntity.getCiudad(), resp.getCiudad());
        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(newEntity.getDuracion(), resp.getDuracion());
        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getLatitud(), resp.getLatitud());
        Assert.assertEquals(newEntity.getLongitud(), resp.getLongitud());
        Assert.assertEquals(newEntity.getPais(), resp.getPais());
        Assert.assertEquals(newEntity.getPrecio(), resp.getPrecio());
        Assert.assertEquals(newEntity.getRestricciones(), resp.getRestricciones());
    }
}