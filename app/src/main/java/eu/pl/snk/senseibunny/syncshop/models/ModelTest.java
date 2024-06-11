package eu.pl.snk.senseibunny.syncshop.models;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class ModelTest {

    @Test
    public void testLogin() throws Exception {
        Model model = Model.getInstance(null);
        boolean result = model.login("testUser", "testPass");
        assertTrue(result);
        assertNotNull(model.getClient());
    }

    @Test
    public void testRegister() throws Exception {
        Model model = Model.getInstance(null);
        model.register("John", "Doe", "john.doe@example.com", "john", "password", 123456);
        assertNotNull(model.getClient());
    }

    @Test
    public void testCreateList() throws Exception {
        Model model = Model.getInstance(null);
        Integer listId = model.createList(1, "Test List", "2022-01-01", "2022-12-31");
        assertNotNull(listId);
    }


}