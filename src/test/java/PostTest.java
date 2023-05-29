import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.*;

public class PostTest {

    @Test
    public void postTest(){
       Response response= given().body("{\n" +
                "    \"id\": 4569,\n" +
                "    \"first_name\": \"Annu4956\",\n" +
                "    \"last_name\": \"Hemnani456\",\n" +
                "    \"email\": \"sebastitestan@codingthesmartway.com\"\n" +
                "  }").log().all().post(" http://localhost:3000/employees");
       response.prettyPrint();

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
