package com.testapi.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserStepDefs {

    private Response response;
    private String endpoint;

    @Given("I have the to-do endpoint")
    public void setEndpoint() {
        endpoint = "https://jsonplaceholder.typicode.com/todos";
    }

    @When("I retrieve the to-do by id {int}")
    public void getTodo(int id) {
        response = given()
                .when()
                .get(endpoint + "/" + id);
    }

    @Then("the response status should be {int}")
    public void verifyStatus(int status) {
        assertThat(response.statusCode(), equalTo(status));
    }

    @Then("the response should contain:")
    public void verifyBody(DataTable table) {
        Map<String, String> expected = table.asMap(String.class, String.class);

        // Validate each key/value pair
        expected.forEach((key, value) -> {
            switch (key) {
                case "userId":
                case "id":
                    assertThat(
                            "Mismatch on " + key,
                            response.jsonPath().getInt(key),
                            equalTo(Integer.parseInt(value))
                    );
                    break;
                case "completed":
                    assertThat(
                            "Mismatch on completed",
                            response.jsonPath().getBoolean(key),
                            equalTo(Boolean.parseBoolean(value))
                    );
                    break;
                default: // title or any other string
                    assertThat(
                            "Mismatch on " + key,
                            response.jsonPath().getString(key),
                            equalTo(value)
                    );
            }
        });
    }

    @When("I create a to-do with title {string} body {string} and userId {int}")
    public void postTodo(String title, String body, int userId) {
        response =
                given()
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .body(new org.json.JSONObject()
                                .put("title", title)
                                .put("body", body)
                                .put("userId", userId)
                                .toString())
                        .when()
                        .post(endpoint);
    }
}
