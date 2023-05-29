import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class TestCode {

    @Test
    public void getTest(){
        Response response = given().get(" http://localhost:3000/employees");
        System.out.println(response.statusCode());
        response.prettyPrint();


    }
}
