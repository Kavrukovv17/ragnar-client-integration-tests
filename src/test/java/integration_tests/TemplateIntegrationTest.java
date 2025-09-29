package integration_tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TemplateIntegrationTest extends BaseIntegrationTest {

    private static String templateId;

    @Test
    @Order(1)
    public void createTemplate() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "IntegrationTemplate");
        Map<String, Object> created = client.createTemplate(payload);
        templateId = created.get("id").toString();
        assertNotNull(templateId);
    }

    @Test
    @Order(2)
    public void updateTemplate() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "UpdatedTemplate");
        Map<String, Object> updated = client.updateTemplate(templateId, payload);
        assertEquals("UpdatedTemplate", updated.get("name"));
    }

    @Test
    @Order(3)
    public void deleteTemplate() throws IOException, InterruptedException {
        boolean deleted = client.deleteTemplate(templateId);
        assertTrue(deleted);
    }
}
