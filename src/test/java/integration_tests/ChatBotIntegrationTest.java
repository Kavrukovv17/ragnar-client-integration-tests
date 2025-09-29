package integration_tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChatBotIntegrationTest extends BaseIntegrationTest {

    private static String chatBotId;

    @Test
    @Order(1)
    public void createChatBot() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "IntegrationBot", "description", "Integration test bot");
        Map<String, Object> created = client.createChatBot(payload);
        chatBotId = created.get("id").toString();
        assertNotNull(chatBotId);
    }

    @Test
    @Order(2)
    public void updateChatBot() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("name", "UpdatedBot");
        Map<String, Object> updated = client.updateChatBot(chatBotId, payload);
        assertEquals("UpdatedBot", updated.get("name"));
    }

    @Test
    @Order(3)
    public void deleteChatBot() throws IOException, InterruptedException {
        boolean deleted = client.deleteChatBot(chatBotId);
        assertTrue(deleted);
    }
}
