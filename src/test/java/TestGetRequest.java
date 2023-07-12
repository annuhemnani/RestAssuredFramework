import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestGetRequest {
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
    public void getTest(){
       /* Response response = given().get(" http://localhost:3000/employees");
        System.out.println(response.statusCode());
        response.prettyPrint();*/

        given().queryParam("id","1").
                when().get("/employees").
                then().
                assertThat().statusCode(200).body("first_name",hasItems("Annu"),"size()",equalTo(20),
                        "lastname",hasItem("dixit"));



    }

    @Test
    public void responseMethod(){
        Response response=given().when().get("/employees").then().extract().response();
        System.out.println("name is this"+response.path("id"));
    }
}
