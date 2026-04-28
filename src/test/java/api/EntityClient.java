package api;

import dto.CreateRequest;
import dto.EntityResponse;
import dto.PatchRequest;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class EntityClient {

    private final RequestSpecification requestSpec;

    public EntityClient(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    private io.restassured.specification.RequestSpecification request() {
        return given().spec(requestSpec);
    }

    public Integer create(CreateRequest request) {
        String id = request()
                .body(request)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        return Integer.valueOf(id.trim());
    }

    public EntityResponse getById(Integer id) {
        return request()
                .when()
                .get("/get/" + id)
                .then()
                .statusCode(200)
                .extract()
                .as(EntityResponse.class);
    }

    public List<EntityResponse> getAll() {
        return request()
                .when()
                .get("/getAll")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("entity", EntityResponse.class);
    }

    public void delete(Integer id) {
        request()
                .when()
                .delete("/delete/" + id)
                .then()
                .statusCode(204);
    }

    public void patch(Integer id, PatchRequest request) {
        request()
                .body(request)
                .when()
                .patch("/patch/" + id)
                .then()
                .log().all()
                .statusCode(204);
    }
}