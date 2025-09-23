import java.io.IOException;
import java.net.Authenticator;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.net.http.HttpRequest;
public class clashAPI {
    private HttpClient client;
    private HttpResponse<String> response;
    private final String baseUrl = "https://api.clashroyale.com/v1";
    private final String API_KEY;
    public clashAPI(String API_KEY) {
        this.API_KEY = API_KEY;
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }
    public String request(String endpoint) {
        HttpRequest req=HttpRequest.newBuilder()
                .uri(URI.create(baseUrl+endpoint))
                .headers("Authorization", "Bearer " + API_KEY,"Content-Type", "application/json")
                .GET()
                .build();
        try {
            response = client.send(req, HttpResponse.BodyHandlers.ofString());
        } catch(IOException | InterruptedException e) {
            e.printStackTrace(System.err);
        }
        return response.body();
    }
}
