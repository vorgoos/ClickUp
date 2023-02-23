package pl.akademiaqa.tests.e2e;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.akademiaqa.dto.request.CreateTaskRequestDto;
import pl.akademiaqa.requests.list.CreateListRequest;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;
import pl.akademiaqa.requests.task.CreateTaskRequest;
import pl.akademiaqa.requests.task.UpdateTaskRequest;

class UpdateTaskE2ETest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);
    private static final String spaceName = "TEST SPACE E2E";
    private static final String listName = "TASKS LIST - E2E";
    private static final String taskName = "Test the ClickUp app";
    private String spaceId;
    private String listId;
    private String taskId;

    @Test
    void updateTaskE2ETest() {

        spaceId = createSpaceStep();
        LOGGER.info("Space created with id: {}", spaceId);

        listId = createListStep();
        LOGGER.info("List created with id: {}", listId);

        taskId = createTaskStep();
        LOGGER.info("Task created with id: {}", taskId);

        updateTaskStep();
        closeTaskStep();
        deleteSpaceStep();

    }

    private String createSpaceStep() {

        JSONObject json = new JSONObject();
        json.put("name", spaceName);

        final var createSpaceResponse = CreateSpaceRequest.createSpace(json);

        Assertions.assertThat(createSpaceResponse.getStatusCode()).isEqualTo(200);

        JsonPath jsonData = createSpaceResponse.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(spaceName);

        return jsonData.getString("id");

    }

    private String createListStep() {

        JSONObject json = new JSONObject();
        json.put("name", listName);

        final var createListResponse = CreateListRequest.createList(json, spaceId);

        Assertions.assertThat(createListResponse.getStatusCode()).isEqualTo(200);

        JsonPath jsonData = createListResponse.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(listName);

        return jsonData.getString("id");

    }

    private String createTaskStep() {

        CreateTaskRequestDto taskDto = new CreateTaskRequestDto();
        taskDto.setName(taskName);
        taskDto.setDescription("I wonder how it works?");
        taskDto.setStatus("to do");

        final var createTaskResponse = CreateTaskRequest.createTask(taskDto, listId);
        LOGGER.info("CREATE TASK RESPONSE OBJ: {}", createTaskResponse);

        Assertions.assertThat(createTaskResponse.getName()).isEqualTo(taskName);
        Assertions.assertThat(createTaskResponse.getDescription()).isEqualTo("I wonder how it works?");

        return createTaskResponse.getId();

    }

    private void updateTaskStep() {

        JSONObject updateTask = new JSONObject();
        updateTask.put("name", "NOWA NAZWA TASKA");
        updateTask.put("description", "NOWY OPIS");

        final var updateTaskResponse = UpdateTaskRequest.updateTask(updateTask, taskId);

        Assertions.assertThat(updateTaskResponse.getStatusCode()).isEqualTo(200);

        JsonPath jsonData = updateTaskResponse.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo("NOWA NAZWA TASKA");
        Assertions.assertThat(jsonData.getString("description")).isEqualTo("NOWY OPIS");

    }

    private void closeTaskStep() {

        JSONObject closeTask = new JSONObject();
        closeTask.put("status", "complete");

        final var closeTaskResponse = UpdateTaskRequest.updateTask(closeTask, taskId);

        Assertions.assertThat(closeTaskResponse.getStatusCode()).isEqualTo(200);

        JsonPath jsonData = closeTaskResponse.jsonPath();
        Assertions.assertThat(jsonData.getString("status.status")).isEqualTo("complete");

    }

    private void deleteSpaceStep() {

        final var deleteSpaceResponse = DeleteSpaceRequest.deleteSpace(spaceId);

        Assertions.assertThat(deleteSpaceResponse.getStatusCode()).isEqualTo(200);

    }

}
