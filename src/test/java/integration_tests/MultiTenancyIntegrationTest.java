package integration_tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MultiTenancyIntegrationTest extends BaseIntegrationTest {

    private static String multiTenancyId;

    @Test
    @Order(1)
    public void createMultiTenancy() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "IntegrationMultiTenancy");
        Map<String, Object> created = client.createMultiTenancy(payload);
        multiTenancyId = created.get("id").toString();
        assertNotNull(multiTenancyId);
    }

    @Test
    @Order(2)
    public void updateMultiTenancy() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "UpdatedMultiTenancy");
        Map<String, Object> updated = client.updateMultiTenancy(multiTenancyId, payload);
        assertEquals("UpdatedMultiTenancy", updated.get("name"));
    }

    @Test
    @Order(3)
    public void deleteMultiTenancy() throws IOException, InterruptedException {
        boolean deleted = client.deleteMultiTenancy(multiTenancyId);
        assertTrue(deleted);
    }
}
