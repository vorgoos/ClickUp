package pl.akademiaqa.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.dto.request.CreateTaskRequestDto;
import pl.akademiaqa.dto.response.CreateTaskResponseDto;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {

    public static Response createTask(JSONObject task, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(task.toString())
                .when()
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }

    public static CreateTaskResponseDto createTask(CreateTaskRequestDto taskDto, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(taskDto)
                .when()
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .statusCode(200)
                .log().ifError()
                .extract()
                .response()
                // zamiana respone'a na obiekt / klasę:
                .as(CreateTaskResponseDto.class);
    }

}
