
package co.edu.csw.company.test.logic;

import co.edu.uniandes.csw.turismo.ejb.PlanLogic;
import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import co.edu.uniandes.csw.turismo.entities.UbicacionEntity;
import co.edu.uniandes.csw.turismo.entities.ValoracionesEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
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
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class PlanLogicTest 
{

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PlanLogic PlanLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PlanEntity> data = new ArrayList<PlanEntity>();

    private List<GuiaEntity> GuiasData = new ArrayList();
    
    private List<PreferenciasEntity> preferenciasData = new ArrayList();
    private List<PreferenciasEntity> preferenciasData2 = new ArrayList();
    private List<ValoracionesEntity> valoracionesData = new ArrayList();
    private List<UbicacionEntity> ubicacionesData = new ArrayList();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlanEntity.class.getPackage())
                .addPackage(PlanLogic.class.getPackage())
                .addPackage(PlanPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
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
     */
    private void clearData()
    {
        em.createQuery("delete from PlanEntity").executeUpdate();
        em.createQuery("delete from GuiaEntity").executeUpdate();
        em.createQuery("delete from PreferenciasEntity").executeUpdate();
        em.createQuery("delete from ValoracionesEntity").executeUpdate();
        em.createQuery("delete from UbicacionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) 
        {
            UbicacionEntity ubics = factory.manufacturePojo(UbicacionEntity.class);
            em.persist(ubics);
            ubicacionesData.add(ubics);
        }
        for (int i = 0; i < 3; i++) 
        {
            GuiaEntity Guias = factory.manufacturePojo(GuiaEntity.class);
            em.persist(Guias);
            GuiasData.add(Guias);
        }
        for (int i = 0; i < 3; i++) 
        {
            ValoracionesEntity vals = factory.manufacturePojo(ValoracionesEntity.class);
            vals.setCalificacion(4.0);
            em.persist(vals);
            valoracionesData.add(vals);
        }
        for (int i = 0; i < 3; i++) 
        {
            PreferenciasEntity prefs = factory.manufacturePojo(PreferenciasEntity.class);
            em.persist(prefs);
            preferenciasData.add(prefs);
            PreferenciasEntity pref2 = factory.manufacturePojo(PreferenciasEntity.class);
            em.persist(pref2);
            preferenciasData2.add(pref2);
        }
        for (int i = 0; i < 3; i++)
        {
            PlanEntity entity = factory.manufacturePojo(PlanEntity.class);
            if(i == 0)
            {
               entity.setValoracionesPlan(valoracionesData);
               entity.setGuias(GuiasData);
            }
            entity.setUbicacion(ubicacionesData.get(i));
            entity.setPreferenciasPlan(preferenciasData);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Plan
     *
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void createPlanTest() throws BusinessLogicException {
        PlanEntity newEntity = factory.manufacturePojo(PlanEntity.class);
        newEntity.setPreferenciasPlan(preferenciasData);
        newEntity.setUbicacion(ubicacionesData.get(0));
        PlanEntity result = PlanLogic.createPlan(newEntity);
        Assert.assertNotNull(result);
        PlanEntity entity = em.find(PlanEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Plans
     *
     * 
     */
    @Test
    public void getPlansTest() {
        List<PlanEntity> list = PlanLogic.getPlans();
        Assert.assertEquals(data.size(), list.size());
        for (PlanEntity entity : list) {
            boolean found = false;
            for (PlanEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Plan
     *
     * 
     */
    @Test
    public void getPlanTest() {
        PlanEntity entity = data.get(0);
        PlanEntity resultEntity = PlanLogic.getPlan(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un Plan
     *
     * 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void deletePlanTest() throws BusinessLogicException
    
    {
        //Obtengo la entity del plan a borrar, junto con ids de sus asociaciones, la unica asociacion que debería borrarse es la de valoracion
        PlanEntity entity = data.get(0);
        Long idPref = data.get(0).getPreferenciasPlan().get(0).getId();
        Long idGuia = data.get(0).getGuias().get(0).getId();
        Long idVal = data.get(0).getValoracionesPlan().get(0).getId();
        Long idUbi = data.get(0).getUbicacion().getId();
        PlanLogic.removeGuia(GuiasData.get(0).getId(), entity.getId());
        PlanLogic.deletePlan(entity.getId());
        PlanEntity deleted = em.find(PlanEntity.class, entity.getId());
        PreferenciasEntity deletedPref = em.find(PreferenciasEntity.class, idPref);
        GuiaEntity deletedGuia = em.find(GuiaEntity.class, idGuia);
        ValoracionesEntity deletedVal = em.find(ValoracionesEntity.class, idVal);
        UbicacionEntity deletedUbicacion = em.find(UbicacionEntity.class, idUbi);
        Assert.assertNotNull(deletedUbicacion);
        Assert.assertNotNull(deletedPref);
        Assert.assertNotNull(deletedGuia);
        Assert.assertNull(deletedVal);
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Plan
     *
     * 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void updatePlanTest() throws BusinessLogicException {
        PlanEntity entity = data.get(0);
        PlanEntity pojoEntity = factory.manufacturePojo(PlanEntity.class);
        pojoEntity.setPreferenciasPlan(preferenciasData);
        pojoEntity.setUbicacion(ubicacionesData.get(0));
        pojoEntity.setId(entity.getId());

        PlanLogic.updatePlan(pojoEntity);

        PlanEntity resp = em.find(PlanEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }

    /**
     * Prueba para obtener una instancia de Guias asociada a una instancia
     * Plan
     *
     * 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void getGuiasTest() throws BusinessLogicException 
    {
        PlanEntity entity = data.get(0);
        GuiaEntity GuiaEntity = GuiasData.get(0);
        GuiaEntity response = PlanLogic.getGuia(entity.getId(), GuiaEntity.getId());

        Assert.assertEquals(GuiaEntity.getId(), response.getId());
        Assert.assertEquals(GuiaEntity.getName(), response.getName());
    }

    /**
     * Prueba para obtener una colección de instancias de Guias asociadas a una
     * instancia Plan
     *
     * 
     */
    @Test
    public void listGuiasTest() {
        List<GuiaEntity> list = PlanLogic.listGuias(data.get(0).getId());
        Assert.assertEquals(3, list.size());
    }

    /**
     * Prueba para asociar un Guias existente a un Plan
     *
     * 
     */
    @Test
    public void addGuiasTest() 
    {
        PlanEntity entity = data.get(1);
        GuiaEntity guiaEntity = GuiasData.get(1);
        GuiaEntity response = PlanLogic.addGuia(guiaEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(guiaEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Guias asociadas a una instancia
     * de Plan
     *
     * 
     */
    @Test
    public void replaceGuiasTest() {
        PlanEntity entity = data.get(0);
        List<GuiaEntity> list = GuiasData.subList(1, 3);
        PlanLogic.replaceGuias(entity.getId(), list);

        entity = PlanLogic.getPlan(entity.getId());
        Assert.assertFalse(entity.getGuias().contains(GuiasData.get(0)));
        Assert.assertTrue(entity.getGuias().contains(GuiasData.get(1)));
        Assert.assertTrue(entity.getGuias().contains(GuiasData.get(2)));
    }

    /**
     * Prueba para desasociar un Guia existente de un Plan existente
     *
     * 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void removeGuiasTest() throws BusinessLogicException {
        boolean estaBien = false;
        try {
            PlanLogic.removeGuia( GuiasData.get(0).getId(),data.get(0).getId());
            GuiaEntity response = PlanLogic.getGuia(data.get(0).getId(), GuiasData.get(0).getId());
            
        } catch (BusinessLogicException e) {
            estaBien = true;
        }
        Assert.assertTrue(estaBien);

    }
    
    /**
     * Prueba para obtener una instancia de Preferencias asociada a una instancia
     * Plan
     *
     * 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void getPreferenciasTest() throws BusinessLogicException 
    {
        PlanEntity entity = data.get(0);
        PreferenciasEntity PreferenciaEntity = preferenciasData.get(0);
        PreferenciasEntity response = PlanLogic.getPreferencias(entity.getId(), PreferenciaEntity.getId());

        Assert.assertEquals(PreferenciaEntity.getId(), response.getId());
        Assert.assertEquals(PreferenciaEntity.getName(), response.getName());
    }

    /**
     * Prueba para obtener una colección de instancias de Preferencias asociadas a una
     * instancia Plan
     *
     * 
     */
    @Test
    public void listPreferenciasTest() {
        List<PreferenciasEntity> list = PlanLogic.listCategorias(data.get(0).getId());
        Assert.assertEquals(3, list.size());
    }

    /**
     * Prueba para asociar un Preferencias existente a un Plan
     *
     * 
     */
    @Test
    public void addPreferenciasTest() 
    {
        PlanEntity entity = data.get(1);
        PreferenciasEntity PreferenciaEntity = preferenciasData2.get(1);
        PreferenciasEntity response = PlanLogic.addPreferencia(PreferenciaEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(PreferenciaEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Preferencias asociadas a una instancia
     * de Plan
     *
     * 
     */
    @Test
    public void replacePreferenciasTest() {
        PlanEntity entity = data.get(0);
        List<PreferenciasEntity> list = preferenciasData.subList(1, 3);
        PlanLogic.replacePreferencias(entity.getId(), list);

        entity = PlanLogic.getPlan(entity.getId());
        Assert.assertFalse(entity.getPreferenciasPlan().contains(preferenciasData.get(0)));
        Assert.assertTrue(entity.getPreferenciasPlan().contains(preferenciasData.get(1)));
        Assert.assertTrue(entity.getPreferenciasPlan().contains(preferenciasData.get(2)));
    }

    /**
     * Prueba para desasociar un Preferencia existente de un Plan existente
     *
     * 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void removePreferenciasTest() throws BusinessLogicException {
        boolean estaBien = false;
        try {
            PlanLogic.removePreferencia( preferenciasData.get(0).getId(),data.get(0).getId());
            PreferenciasEntity response = PlanLogic.getPreferencias(data.get(0).getId(), preferenciasData.get(0).getId());
            
        } catch (BusinessLogicException e) {
            estaBien = true;
        }
        Assert.assertTrue(estaBien);

    }
    
    /**
     * Prueba para obtener una instancia de Valoraciones asociada a una instancia
     * Plan
     *
     * 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void getValoracionesTest() throws BusinessLogicException 
    {
        PlanEntity entity = data.get(0);
        ValoracionesEntity ValoracionEntity = valoracionesData.get(0);
        ValoracionesEntity response = PlanLogic.getVal(entity.getId(), ValoracionEntity.getId());

        Assert.assertEquals(ValoracionEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de Valoraciones asociadas a una
     * instancia Plan
     *
     * 
     */
    @Test
    public void listValoracionesTest() {
        List<ValoracionesEntity> list = PlanLogic.listValoraciones(data.get(0).getId());
        Assert.assertEquals(3, list.size());
    }

    /**
     * Prueba para asociar un Valoraciones existente a un Plan
     *
     * 
     */
    @Test
    public void addValoracionesTest() 
    {
        PlanEntity entity = data.get(1);
        ValoracionesEntity ValoracionEntity = valoracionesData.get(1);
        ValoracionesEntity response = PlanLogic.addValoracion(ValoracionEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(ValoracionEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Valoraciones asociadas a una instancia
     * de Plan
     *
     * 
     */
    @Test
    public void replaceValoracionesTest() {
        PlanEntity entity = data.get(1);
        List<ValoracionesEntity> list = valoracionesData.subList(1, 3);
        PlanLogic.replaceValoraciones(entity.getId(), list);

        entity = PlanLogic.getPlan(entity.getId());
        Assert.assertFalse(entity.getValoracionesPlan().contains(valoracionesData.get(0)));
        Assert.assertTrue(entity.getValoracionesPlan().contains(valoracionesData.get(1)));
        Assert.assertTrue(entity.getValoracionesPlan().contains(valoracionesData.get(2)));
    }

    /**
     * Prueba para desasociar un Valoracion existente de un Plan existente
     *
     * 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void removeValoracionesTest() throws BusinessLogicException {
        boolean estaBien = false;
        try {
            PlanLogic.removeValoracion( valoracionesData.get(0).getId(),data.get(0).getId());
            ValoracionesEntity response = PlanLogic.getVal(data.get(0).getId(), valoracionesData.get(0).getId());
            
        } catch (BusinessLogicException e) {
            estaBien = true;
        }
        Assert.assertTrue(estaBien);

    }
    
    /**
     * Prueba si el metodo findByName esta Bien
     *
     * 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @Test
    public void findByNameTest() throws BusinessLogicException {
        String name = data.get(0).getName();
        PlanEntity e = PlanLogic.getByName(name);
        Assert.assertEquals(data.get(0), e);
        
        String name1 = "notAChanceDeQueEsteNombreExistalulnomamesfwef";
        PlanEntity en = PlanLogic.getByName(name1);
        Assert.assertNull(en);

    }
    
    /**
     * Prueba si el metodo getUbicacionDePlan de plan logic funciona correctamente
     */
    @Test
    public void getUbicacionTest()
    {
        UbicacionEntity ent = data.get(2).getUbicacion();
        Long idPlan = data.get(2).getId();
        UbicacionEntity ubicacion = PlanLogic.getUbicacionDePlan(idPlan);
        Assert.assertEquals(ent, ubicacion);
    }
    
    /**
     * Prueba si el metodo getUbicacionDePlan de plan logic funciona correctamente
     */
    @Test
    public void cambiarUbicacionTest()
    {
        UbicacionEntity ent = data.get(1).getUbicacion();
        Long idPlan = data.get(2).getId();
        Long idUbi = data.get(1).getUbicacion().getId();
        UbicacionEntity ubicacion = PlanLogic.replaceUbicacion(idPlan, idUbi);
        Assert.assertEquals(ent, ubicacion);
    }
    
    /**
     * Prueba reglas de negocio referente al nombre del plan y la ubicacion
     */
    @Test
    public void nombreNoNullYUbicacionNoNull()
    {
        boolean esCorrecto = false;
        PlanEntity prueba = factory.manufacturePojo(PlanEntity.class);
        prueba.setPreferenciasPlan(preferenciasData2);
        prueba.setUbicacion(ubicacionesData.get(0));
        //Prueba 1, plan con nombre null
        prueba.setName(null);
        try 
        {
            PlanLogic.createPlan(prueba);
        }
        catch (BusinessLogicException e) 
        {
            esCorrecto = true;
        }
        Assert.assertTrue(esCorrecto);
        
        //Caso 2, ubicacion null
        esCorrecto = false;
        prueba = factory.manufacturePojo(PlanEntity.class);
        prueba.setPreferenciasPlan(preferenciasData2);
        prueba.setUbicacion(ubicacionesData.get(0));
        prueba.setUbicacion(null);
        try 
        {
            PlanLogic.createPlan(prueba);
        }
        catch (BusinessLogicException e) 
        {
            esCorrecto = true;
        }
        Assert.assertTrue(esCorrecto);
        
        //Caso 3, ubicacion no null, pero ciudad null
        esCorrecto = false;
        prueba = factory.manufacturePojo(PlanEntity.class);
        prueba.setPreferenciasPlan(preferenciasData2);
        prueba.setUbicacion(ubicacionesData.get(0));
        prueba.getUbicacion().setCiudad(null);
        try 
        {
            PlanLogic.createPlan(prueba);
        }
        catch (BusinessLogicException e) 
        {
            esCorrecto = true;
        }
        Assert.assertTrue(esCorrecto);
        
        //Caso 4, ubicacion no null, pero pais null
        esCorrecto = false;
        prueba = factory.manufacturePojo(PlanEntity.class);
        prueba.setPreferenciasPlan(preferenciasData2);
        prueba.setUbicacion(ubicacionesData.get(0));
        prueba.getUbicacion().setPais(null);
        try 
        {
            PlanLogic.createPlan(prueba);
        }
        catch (BusinessLogicException e) 
        {
            esCorrecto = true;
        }
        Assert.assertTrue(esCorrecto);
        
        //Caso 5, ubicacion no null, pero latitud null
        esCorrecto = false;
        prueba = factory.manufacturePojo(PlanEntity.class);
        prueba.setPreferenciasPlan(preferenciasData2);
        prueba.setUbicacion(ubicacionesData.get(0));
        prueba.getUbicacion().setLatitud(null);
        try 
        {
            PlanLogic.createPlan(prueba);
        }
        catch (BusinessLogicException e) 
        {
            esCorrecto = true;
        }
        Assert.assertTrue(esCorrecto);
        
        //Caso 6, ubicacion no null, pero longitud null
        esCorrecto = false;
        prueba = factory.manufacturePojo(PlanEntity.class);
        prueba.setPreferenciasPlan(preferenciasData2);
        prueba.setUbicacion(ubicacionesData.get(0));
        prueba.getUbicacion().setLongitud(null);
        try 
        {
            PlanLogic.createPlan(prueba);
        }
        catch (BusinessLogicException e) 
        {
            esCorrecto = true;
        }
        Assert.assertTrue(esCorrecto);
        
        //Caso 7 nombre con length = 0
        esCorrecto = false;
        prueba = factory.manufacturePojo(PlanEntity.class);
        prueba.setPreferenciasPlan(preferenciasData2);
        prueba.setUbicacion(ubicacionesData.get(0));
        prueba.setName("");
        try 
        {
            PlanLogic.createPlan(prueba);
        }
        catch (BusinessLogicException e) 
        {
            esCorrecto = true;
        }
        Assert.assertTrue(esCorrecto);  
    }
}