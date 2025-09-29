package integration_tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AssistantIntegrationTest extends BaseIntegrationTest {

    private static String assistantId;

    @Test
    @Order(1)
    public void createAssistant() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "IntegrationAssistant");
        Map<String, Object> created = client.createAssistant(payload);
        assistantId = created.get("id").toString();
        assertNotNull(assistantId);
    }

    @Test
    @Order(2)
    public void updateAssistant() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "UpdatedAssistant");
        Map<String, Object> updated = client.updateAssistant(assistantId, payload);
        assertEquals("UpdatedAssistant", updated.get("name"));
    }

    @Test
    @Order(3)
    public void deleteAssistant() throws IOException, InterruptedException {
        boolean deleted = client.deleteAssistant(assistantId);
        assertTrue(deleted);
    }
}
