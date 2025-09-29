package integration_tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChatIntegrationTest  extends BaseIntegrationTest {

    private static String chatId;

    @Test
    @Order(1)
    public void createChat() throws IOException, InterruptedException, IOException {
        Map<String, Object> payload = Map.of("topic", "Integration Test Chat");
        Map<String, Object> created = client.createChat(payload);
        chatId = created.get("id").toString();
        assertNotNull(chatId);
    }

    @Test
    @Order(2)
    public void updateChat() throws IOException, InterruptedException {
        Map<String, Object> payload = Map.of("topic", "Updated Chat Topic");
        Map<String, Object> updated = client.updateChat(chatId, payload);
        assertEquals("Updated Chat Topic", updated.get("topic"));
    }

    @Test
    @Order(3)
    public void deleteChat() throws IOException, InterruptedException {
        boolean deleted = client.deleteChat(chatId);
        assertTrue(deleted);
    }
}
