/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.csw.company.test.persistence;

import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
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
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author  jc.montoyar
 */
@RunWith(Arquillian.class)
public class PreferenciasPersistenceTest {

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Preferencias, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PreferenciasEntity.class.getPackage())
                .addPackage(PreferenciasPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase PreferenciasPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PreferenciasPersistence PreferenciasPersistence;

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
        em.createQuery("delete from PreferenciasEntity").executeUpdate();
    }

    /**
     *
     */
    private List<PreferenciasEntity> data = new ArrayList<PreferenciasEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PreferenciasEntity entity = factory.manufacturePojo(PreferenciasEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Preferencias.
     *
     *
     */
    @Test
    public void createPreferenciasTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PreferenciasEntity newEntity = factory.manufacturePojo(PreferenciasEntity.class);
        PreferenciasEntity result = PreferenciasPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PreferenciasEntity entity = em.find(PreferenciasEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getTiposPlan(), entity.getTiposPlan());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Preferenciass.
     *
     *
     */
    @Test
    public void getPreferenciassTest() {
        List<PreferenciasEntity> list = PreferenciasPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PreferenciasEntity ent : list) {
            boolean found = false;
            for (PreferenciasEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Preferencias.
     *
     *
     */
    @Test
    public void getPreferenciasTest() {
        PreferenciasEntity entity = data.get(0);
        PreferenciasEntity newEntity = PreferenciasPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getTiposPlan(), entity.getTiposPlan());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para eliminar un Preferencias.
     *
     *
     */
    @Test
    public void deletePreferenciasTest() {
        PreferenciasEntity entity = data.get(0);
        PreferenciasPersistence.delete(entity.getId());
        PreferenciasEntity deleted = em.find(PreferenciasEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Preferencias.
     *
     *
     */
    @Test
    public void updatePreferenciasTest() {
        PreferenciasEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PreferenciasEntity newEntity = factory.manufacturePojo(PreferenciasEntity.class);

        newEntity.setId(entity.getId());

        PreferenciasPersistence.update(newEntity);

        PreferenciasEntity resp = em.find(PreferenciasEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getTiposPlan(),resp.getTiposPlan());
    }
}
