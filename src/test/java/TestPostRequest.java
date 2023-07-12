import com.github.javafaker.Faker;
import data.UserData;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jdk.jfr.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TestPostRequest {
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
    @Description("Example of using Builder Pattern to pass test data in tests")
    public void postTestUsingBuilderPattern () {
        UserData userData = userDataBuilder ();
        given ().body (userData)
                .when ()
                .post ("/api/users")
                .then ()
                .statusCode (201)
                .and ()
                .assertThat ()
                .body ("name", equalTo (userData.getName ()))
                .body ("job", equalTo (userData.getJob ()));

    }

    private UserData userDataBuilder () {
        Faker faker = new Faker();
        return UserData.builder ()
                .name (faker.name ()
                        .firstName ())
                .job (faker.company ()
                        .profession ())
                .build ();
    }

}
