package integration_tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TenantIntegrationTest extends BaseIntegrationTest {

    private static String tenantId;

    @Test
    @Order(1)
    public void createTenant() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "IntegrationTenant");
        Map<String, Object> created = client.createTenant(payload);
        tenantId = created.get("id").toString();
        assertNotNull(tenantId);
    }

    @Test
    @Order(2)
    public void updateTenant() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "UpdatedTenant");
        Map<String, Object> updated = client.updateTenant(tenantId, payload);
        assertEquals("UpdatedTenant", updated.get("name"));
    }

    @Test
    @Order(3)
    public void deleteTenant() throws IOException, InterruptedException {
        boolean deleted = client.deleteTenant(tenantId);
        assertTrue(deleted);
    }
}
