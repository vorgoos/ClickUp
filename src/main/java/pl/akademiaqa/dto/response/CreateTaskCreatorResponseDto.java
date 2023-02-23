package pl.akademiaqa.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter     // tworzenie getterów przy pomocy biblioteki Lombok (nie pojawiają się w kodzie, ale można z nich korzystać)
@ToString   // tworzenie ToString przy pomocy biblioteki Lombok, jak wyżej

@JsonIgnoreProperties(ignoreUnknown = true)    // przy zamianie jsona na obiekt, ignoruje pola, które nas nie interesują
public class CreateTaskCreatorResponseDto {

    @JsonProperty("username")       // przypisanie wartości z pola 'username' do zmiennej 'userName'
    private String userName;
    private String email;

    // ZAMIAST GENEROWANIA GETTERÓW I TOSTRING UŻYWAMY BIBLIOTEKI LOMBOK, PATRZ WYŻEJ

    // generowanie getterów:
    // public String getUserName() {
    //     return userName;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // generowanie toString():
    // @Override
    // public String toString() {
    //     return "CreateTaskCreatorResponseDto{" +
    //             "username='" + userName + '\'' +
    //             ", email='" + email + '\'' +
    //             '}';
    // }
}
