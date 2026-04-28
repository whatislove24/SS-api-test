package tests.base;

import api.EntityClient;
import config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

    protected static EntityClient entityClient;

    @BeforeAll
    static void setUp() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.baseUrl())
                .setBasePath(ConfigReader.basePath())
                .setContentType(ContentType.JSON)
                .addHeader("Connection", "close")
                .log(LogDetail.ALL)
                .build();

        entityClient = new EntityClient(requestSpec);
    }
}