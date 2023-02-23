package pl.akademiaqa.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter     // tworzenie getterów przy pomocy biblioteki Lombok (nie pojawiają się w kodzie, ale można z nich korzystać)
@ToString   // tworzenie ToString przy pomocy biblioteki Lombok, jak wyżej

@JsonIgnoreProperties(ignoreUnknown = true)    // przy zamianie jsona na obiekt, ignoruje pola, które nas nie interesują
public class CreateTaskStatusResponseDto {

    private String status;
    private String type;

    // ZAMIAST GENEROWANIA GETTERÓW I TOSTRING UŻYWAMY BIBLIOTEKI LOMBOK, PATRZ WYŻEJ

    // generowanie getterów:
    // public String getStatus() {
    //     return status;
    // }

    // public String getType() {
    //     return type;
    // }

    // generowanie toString():
    // @Override
    // public String toString() {
    //     return "CreateTaskStatusResponseDto{" +
    //             "status='" + status + '\'' +
    //             ", type='" + type + '\'' +
    //             '}';
    // }
}
