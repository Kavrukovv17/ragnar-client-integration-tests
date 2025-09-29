package integration_tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserIntegrationTest extends BaseIntegrationTest {


    private static String userId;

    @Test
    @Order(1)
    public void createUser() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of(
                "username", "integrationUser",
                "email", "test@example.com",
                "password", "password123"
        );
        Map<String, Object> created = client.createUser(payload);
        userId = created.get("id").toString();
        assertNotNull(userId);
    }

    @Test
    @Order(2)
    public void updateUser() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("email", "updated@example.com");
        Map<String, Object> updated = client.updateUser(userId, payload);
        assertEquals("updated@example.com", updated.get("email"));
    }

    @Test
    @Order(3)
    public void deleteUser() throws IOException, InterruptedException {
        boolean deleted = client.deleteUser(userId);
        assertTrue(deleted);
    }
}
