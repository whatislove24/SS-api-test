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

    public Integer create(CreateRequest request) {
        String id = given()
                .spec(requestSpec)
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
        return given()
                .spec(requestSpec)
                .when()
                .get("/get/" + id)
                .then()
                .statusCode(200)
                .extract()
                .as(EntityResponse.class);
    }

    public void getByIdShouldReturnNotFound(Integer id) {
        given()
                .spec(requestSpec)
                .when()
                .get("/get/" + id)
                .then()
                .statusCode(404);
    }

    public List<EntityResponse> getAll() {
        return given()
                .spec(requestSpec)
                .when()
                .get("/getAll")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("entity", EntityResponse.class);
    }

    public void patch(Integer id, PatchRequest request) {
        given()
                .spec(requestSpec)
                .body(request)
                .when()
                .patch("/patch/" + id)
                .then()
                .statusCode(204);
    }

    public void delete(Integer id) {
        given()
                .spec(requestSpec)
                .when()
                .delete("/delete/" + id)
                .then()
                .statusCode(204);
    }
}