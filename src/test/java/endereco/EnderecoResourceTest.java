package endereco;

/*

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EnderecoResourceTest {

    private static Long cidadeId;
    private static Long enderecoId;

    @BeforeAll
    static void setupCidade() {
        cidadeId =
                given()
                        .contentType(ContentType.JSON)
                        .body("""
                    {
                        "nome": "Joinville",
                        "uf": "sc"
                    }
                """)
                        .when()
                        .post("/cidades")
                        .then()
                        .statusCode(201)
                        .extract()
                        .path("id");
    }

    @Test
    @Order(1)
    void shouldCreateEndereco() {
        enderecoId =
                given()
                        .contentType(ContentType.JSON)
                        .body("""
                    {
                        "tipoLogradouro": "Rua",
                        "logradouro": "XV de Novembro",
                        "numero": 15,
                        "bairro": "Centro",
                        "cidade": { "id": %d }
                    }
                """.formatted(cidadeId))
                        .when()
                        .post("/enderecos")
                        .then()
                        .statusCode(201)
                        .body("id", notNullValue())
                        .body("cidade.id", equalTo(cidadeId.intValue()))
                        .extract()
                        .path("id");
    }

    @Test
    @Order(2)
    void shouldGetEnderecoById() {
        given()
                .when()
                .get("/enderecos/{id}", enderecoId)
                .then()
                .statusCode(200)
                .body("id", equalTo(enderecoId.intValue()));
    }

    @Test
    @Order(3)
    void shouldUpdateEndereco() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                    "tipoLogradouro": "Avenida",
                    "logradouro": "Getúlio Vargas",
                    "numero": 100,
                    "bairro": "Boa Vista",
                    "cidade": { "id": %d }
                }
            """.formatted(cidadeId))
                .when()
                .put("/enderecos/{id}", enderecoId)
                .then()
                .statusCode(200)
                .body("logradouro", equalTo("Getúlio Vargas"))
                .body("numero", equalTo(100));
    }

    @Test
    @Order(4)
    void shouldListEnderecos() {
        given()
                .when()
                .get("/enderecos")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Order(5)
    void shouldDeleteEndereco() {
        given()
                .when()
                .delete("/enderecos/{id}", enderecoId)
                .then()
                .statusCode(204);
    }

    @AfterAll
    static void cleanupCidade() {
        given()
                .when()
                .delete("/cidades/{id}", cidadeId)
                .then()
                .statusCode(204);
    }
}
*/