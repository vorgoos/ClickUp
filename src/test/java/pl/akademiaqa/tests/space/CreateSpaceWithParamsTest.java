package pl.akademiaqa.tests.space;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;

import java.util.stream.Stream;

class CreateSpaceWithParamsTest {

    @ParameterizedTest(name = "Create space name with space name: {0}")
    @DisplayName("Create space with valid space name")
    @MethodSource("createSpaceData")
    void createSpaceTest(String spaceName) {

        JSONObject space = new JSONObject();
        space.put("name", spaceName);

        final var createSpaceResponse = CreateSpaceRequest.createSpace(space);

        Assertions.assertThat(createSpaceResponse.statusCode()).isEqualTo(200);
        Assertions.assertThat(createSpaceResponse.jsonPath().getString("name")).isEqualTo(spaceName);

        final var spaceId = createSpaceResponse.jsonPath().getString("id");

        final var deleteSpaceResponse = DeleteSpaceRequest.deleteSpace(spaceId);

        Assertions.assertThat(deleteSpaceResponse.statusCode()).isEqualTo(200);
    }

    private static Stream<Arguments> createSpaceData() {

        return Stream.of(
                Arguments.of("TEST SPACE"),
                Arguments.of("666"),
                Arguments.of("#"),
                Arguments.of("$$$")
        );

    }

}
