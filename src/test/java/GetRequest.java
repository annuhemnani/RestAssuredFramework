import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class GetRequest {

    @Test
    public void getTests(){
      Response response=  given().log().all().get("  http://localhost:3000/employees");
      //response.prettyPrint();
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
    }

    public void posttest(){
        ValidatableResponse body = given().when().post().then().statusCode(200).body("firstname", hasItems("annu"));
       Response response= given().get().then().extract().path("id");

    }
}
