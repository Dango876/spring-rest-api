package restapi;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import restapi.entity.User;


@Component
public class Communication {
    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";
    private final HttpHeaders headers = new HttpHeaders();
    private String result = "";

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getOperation() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);
        headers.set("Cookie", responseEntity.getHeaders().getFirst("Set-Cookie"));

        User user = new User(3L, "James", "Brown", (byte) 25);

        ResponseEntity<String> responseEntity1 = restTemplate.exchange(
                URL,
                HttpMethod.POST,
                new HttpEntity<>(user, headers),
                String.class);
        result = responseEntity1.getBody();

        user.setName("Thomas");
        user.setLastName("Shelby");

        ResponseEntity<String> responseEntity2 = restTemplate.exchange(
                URL,
                HttpMethod.PUT,
                new HttpEntity<>(user, headers),
                String.class);
        result = result + responseEntity2.getBody();

        ResponseEntity<String> responseEntity3 = restTemplate.exchange(
                URL + "/3",
                HttpMethod.DELETE,
                new HttpEntity<>(user, headers),
                String.class);
        result = result + responseEntity3.getBody();

        return result;
    }
}