import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequest {

    @Test
    public void getTests(){
      Response response=  given().log().all().get("  http://localhost:3000/employees");
      //response.prettyPrint();
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
    }
}
