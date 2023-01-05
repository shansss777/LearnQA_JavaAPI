import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloWorldTest {


    @Test
    public void testRestAssured() {

        //Map<String, String> params = new HashMap<>();
        //params.put("name", "Андрей");

        JsonPath response = RestAssured
                .given()
                //.queryParams(params)
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
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }
    @Test
    public void testRestHomeWork1() {
        JsonPath response = RestAssured
                //.given()
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();
        List value = response.get("messages");
        System.out.println(value.get(1));
    }
}
