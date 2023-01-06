import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateSipleRequests {

    @Test // Ex5: Парсинг JSON
    public void testRestHomeWork5() {
        JsonPath response = RestAssured
                //.given()
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();
        List value = response.get("messages");
        System.out.println(value.get(1));
    }
    @Test // Ex6: Редирект
    public void testRestHomeWork6() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

        String locationHeaders = response.getHeader("Location");
        System.out.println(locationHeaders);

    }

    @Test // Ex7: Долгий редирект
    public void testRestHomeWork7() {
        String baseUrl = "https://playground.learnqa.ru/api/long_redirect";
        while (true) {
            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow( false)
                    .when()
                    .get(baseUrl)
                    .andReturn();
            int statusCode = response.getStatusCode();
            System.out.println(statusCode);
            if (statusCode == 200) {
                break;
            }
            String locationHeaders = response.getHeader("Location");
            System.out.println(locationHeaders);
            baseUrl = locationHeaders;
        }

    }
}
