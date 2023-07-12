import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AutomateGet {
@Test
    public void validate_get_status_code(){
    given().baseUri("https://api.getpostman.com/").
            header("X-Api-key","PMAK-6474cadac919e92687ba1458-8cce9ac2acc4ddc0165fd8377402df6fa4").
            when().get("/workspaces").
            then().
            log().all().assertThat().statusCode(201);
}

    @Test
    public void validate_get_response_body(){
        given().baseUri("https://api.getpostman.com/").
                header("X-Api-key","PMAK-6474cadac919e92687ba1458-8cce9ac2acc4ddc0165fd8377402df6fa4").
                when().get("/workspaces").
                then().
                log().all().assertThat().body("workspaces.name",hasItems("Team Workspace","My Workspace"),"workspaces.type",hasItems("team","personal"),
                        "workspaces.size()",equalTo(2));
    }
}

