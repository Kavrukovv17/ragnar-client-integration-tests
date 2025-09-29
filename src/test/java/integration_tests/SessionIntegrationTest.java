package integration_tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SessionIntegrationTest extends BaseIntegrationTest {

    private static String sessionId;

    @Test
    @Order(1)
    public void createSession() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "IntegrationSession");
        Map<String, Object> created = client.createSession(payload);
        sessionId = created.get("id").toString();
        assertNotNull(sessionId);
    }

    @Test
    @Order(2)
    public void updateSession() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "UpdatedSession");
        Map<String, Object> updated = client.updateSession(sessionId, payload);
        assertEquals("UpdatedSession", updated.get("name"));
    }

    @Test
    @Order(3)
    public void deleteSession() throws IOException, InterruptedException {
        boolean deleted = client.deleteSession(sessionId);
        assertTrue(deleted);
    }
}
