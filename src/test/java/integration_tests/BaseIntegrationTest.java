package integration_tests;

import aiclient.AIClient;
import org.junit.jupiter.api.BeforeAll;

import javax.naming.AuthenticationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BaseIntegrationTest {

    protected static AIClient client;

    @BeforeAll
    public static void setupClient() throws IOException, InterruptedException, AuthenticationException {
        String baseUrl = "RAGNAR_URL";
        String username = "RAGNAR_USERNAME";
        String password = "RAGNAR_PASSWORD";

        client = new AIClient(baseUrl);
        client.authenticate(username, password);
        assertNotNull(client.getAuthToken(), "Auth token should not be null after authentication");
    }
}
