import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class PostTest {
    @BeforeClass
    public void setup () {

        RequestSpecification request = new RequestSpecBuilder().addHeader ("Content-Type", "application/json")
                .setBaseUri ("https://reqres.in/")
                .addHeader ("Accept", "application/json")
                .addFilter (new RequestLoggingFilter())
                .addFilter (new ResponseLoggingFilter())
                .build ();

        ResponseSpecification response = new ResponseSpecBuilder().expectResponseTime (lessThan (10000L))
                .build ();

        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    @Test
    public void postTest(){
       Response response= given().header("Content-type","application/json").body("{\n" +
               "    \"name\": \"morpheus1\",\n" +
               "    \"job\": \"leader1\",\n" +

               "}").log().all().post(" /api/users");
       response.prettyPrint();
       response.
        System.out.println(response.statusCode());

    }
    @Test
    public void test1(){
        Response response = given()
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(new File(System.getProperty("user.dir") + "/test.json"))
                .post(" http://localhost:3000/employees");
        response.prettyPrint();
    }
    @Test
    public void test2() throws IOException {
        String reqBody=new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/test.json")));
        String replace =reqBody.replace("13",String.valueOf(new Faker().number().numberBetween(500,1000)));
        Response response = given()
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(replace)
                //.body(new File(System.getProperty("user.dir") + "/test.json"))
                .post(" http://localhost:3000/employees");
        response.prettyPrint();
    }

    @Test
    public void test3(){
        Map<String, Object> map=new HashMap<>();
        map.put("id", String.valueOf(new Faker().number().numberBetween(500,1000)));
        map.put("Fname",String.valueOf(new Faker().name().firstName()));
        map.put("lname",String.valueOf(new Faker().name().lastName()));
        map.put("email", "testqa@gmail.com");
        List<String> listofJob= new ArrayList<>();
        listofJob.add("tester");
        listofJob.add("trainer");
        map.put("jobs",listofJob);
        Map<String, Object> objectMap=new HashMap<>();
        objectMap.put("breakfast","idly");
        objectMap.put("dinner","rajma");
        map.put("favfoods",objectMap);


        Response response=given().header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(map)
            .post(" http://localhost:3000/employees");
response.prettyPrint();

    }
    @Test
    public void test4(){
        JSONObject map=new JSONObject();

        map.put("id", String.valueOf(new Faker().number().numberBetween(500,1000)));
        map.put("Fname",String.valueOf(new Faker().name().firstName()));
        map.put("lname",String.valueOf(new Faker().name().lastName()));
        map.put("email", "testqa@gmail.com");
    }
}
