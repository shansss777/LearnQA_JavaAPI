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
            int statusCode = response.getStatusCode(); // находим статус код
            System.out.println(statusCode);
            if (statusCode == 200) {
                break;
            }
            String locationHeaders = response.getHeader("Location"); //находим новый URL
            System.out.println(locationHeaders);
            baseUrl = locationHeaders;
        }
    }
    @Test // Ex8: Токены
    public void testRestHomeWork8() throws InterruptedException {
        String baseURL = "https://playground.learnqa.ru/ajax/api/longtime_job";
        JsonPath responseNoToken = RestAssured
                .given()
                .get(baseURL)
                .jsonPath();
        String getToken = responseNoToken.get("token");
        int getSecond = responseNoToken.get("seconds");
        System.out.println(getSecond);
        System.out.println(getToken);

//        Thread.sleep(getSecond*500);

        Map<String, String> params = new HashMap<>();
        params.put("token", getToken);
        Response responseNotReady = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .andReturn();
        responseNotReady.prettyPrint();

        Thread.sleep(getSecond*1000);

        Map<String, String> params1 = new HashMap<>();
        params.put("token", getToken);
        Response responseReady = RestAssured
                .given()
                .queryParams(params1)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .andReturn();
        responseReady.prettyPrint();
    }
    @Test
    public void testRestStatus() throws InterruptedException {
        Response response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .andReturn();
        Thread.sleep(1);
        response.print();
    }
}
