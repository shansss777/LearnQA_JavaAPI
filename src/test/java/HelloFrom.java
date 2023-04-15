import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloFrom {
    @Test
    public void helloFromDmitriy() {
        System.out.println("Hello from Dmitriy");
    }

    @Test
    public void testRestAssured() {

        Map<String, String> params = new HashMap<>();
        params.put("name", "Андрей");

        JsonPath response = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();
        String name = response.get("answer");
        if (name == null) {
            System.out.println("Такого ключа 'answer2' не существует");
        }
        else {
            System.out.println(name);
        }
    }
    @Test
    public void testRestAssured2() {
        Map<String, Object> body = new HashMap<>();
        body.put("param1","value1");
        body.put("param2","value2");

        Response response = RestAssured
                .given()
                .body(body)
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        response.print();
    }
    @Test
    public void testRestStatus() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow( true)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }
    @Test
    public void testassert() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/map")
                .andReturn();
                assertTrue(response.statusCode() == 200, "Unexpected status code");
    }
    @Test
    public void testStringLengs() {
        String phrase = "kdJFsjfdjfdsjhfdhfdbbfds";
        assertTrue(phrase.length() > 15, "Unexpected length");
    }
}


