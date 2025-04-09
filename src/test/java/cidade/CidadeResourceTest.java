package cidade;

/*
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CidadeResourceTest {

    private static Long cidadeId;

    @Test
    @Order(1)
    void shouldCreateCity() {
        cidadeId =
                given()
                        .contentType(ContentType.JSON)
                        .body("""
                    {
                        "nome": "Florianópolis",
                        "uf": "sc"
                    }
                """)
                        .when()
                        .post("/cidades")
                        .then()
                        .statusCode(201)
                        .body("id", notNullValue())
                        .body("uf", equalTo("SC"))
                        .extract()
                        .path("id");
    }

    @Test
    @Order(2)
    void shouldGetCityById() {
        given()
                .when()
                .get("/cidades/{id}", cidadeId)
                .then()
                .statusCode(200)
                .body("id", equalTo(cidadeId.intValue()));
    }

    @Test
    @Order(3)
    void shouldUpdateCity() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                    "nome": "Floripa",
                    "uf": "sc"
                }
            """)
                .when()
                .put("/cidades/{id}", cidadeId)
                .then()
                .statusCode(200)
                .body("nome", equalTo("Floripa"))
                .body("uf", equalTo("SC"));
    }

    @Test
    @Order(4)
    void shouldListCities() {
        given()
                .when()
                .get("/cidades")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Order(5)
    void shouldDeleteCity() {
        given()
                .when()
                .delete("/cidades/{id}", cidadeId)
                .then()
                .statusCode(204);
    }

    @Test
    @Order(6)
    void shouldReturnNotFoundWhenCityDeleted() {
        given()
                .when()
                .get("/cidades/{id}", cidadeId)
                .then()
                .statusCode(404);
    }
}
*/