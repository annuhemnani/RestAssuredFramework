import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class PostReqWithPOJO {

    @Test
    public void pojoTest() throws JsonProcessingException {
        FavFoods favFoods=new FavFoods();
        favFoods.setBreakfast("idly");
        favFoods.setLunch("paratha");
        favFoods.setDinner(Arrays.asList("naan","tandoriroti"));
        Employee employee = new Employee(5367, "teena", "dixit", "mail.com", favFoods);

        Response response=given().header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(employee)
                .post(" http://localhost:3000/employees");
        response.prettyPrint();
    }
}
