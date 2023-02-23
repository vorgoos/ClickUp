package pl.akademiaqa.tests.space;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;

class CreateSpaceTest {

    private static final String SPACE_NAME = "TEST SPACE FROM JAVA";

    @Test
    void createSpaceTest() {

        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final var createSpaceResponse = CreateSpaceRequest.createSpace(space);

        Assertions.assertThat(createSpaceResponse.statusCode()).isEqualTo(200);
        Assertions.assertThat(createSpaceResponse.jsonPath().getString("name")).isEqualTo(SPACE_NAME);

        final var spaceId = createSpaceResponse.jsonPath().getString("id");

        final var deleteSpaceResponse = DeleteSpaceRequest.deleteSpace(spaceId);

        Assertions.assertThat(deleteSpaceResponse.statusCode()).isEqualTo(200);

    }

}
