import entities.Salle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.SalleService;

import java.util.List;

import static org.junit.Assert.*;

public class SalleServiceTest {

    private SalleService salleService;
    private Salle salle;

    @Before
    public void setUp() {
        salleService = new SalleService();
        salle = new Salle();
        salle.setCode("A101");
        salleService.create(salle);
    }

    @After
    public void tearDown() {
        Salle foundSalle = salleService.findById(salle.getId());
        if (foundSalle != null) {
            salleService.delete(foundSalle);
        }
    }

    @Test
    public void testCreate() {
        assertNotNull(salle.getId());
    }

    @Test
    public void testFindById() {
        Salle foundSalle = salleService.findById(salle.getId());
        assertNotNull(foundSalle);
        assertEquals(salle.getCode(), foundSalle.getCode());
    }

    @Test
    public void testUpdate() {
        salle.setCode("B202");
        boolean result = salleService.update(salle);
        assertTrue(result);
        Salle updatedSalle = salleService.findById(salle.getId());
        assertEquals("B202", updatedSalle.getCode());
    }

    @Test
    public void testDelete() {
        boolean result = salleService.delete(salle);
        assertTrue(result);
        Salle foundSalle = salleService.findById(salle.getId());
        assertNull(foundSalle);
    }

    @Test
    public void testFindAll() {
        List<Salle> salles = salleService.findAll();
        assertNotNull(salles);
        assertTrue(salles.size() > 0);
    }
}
