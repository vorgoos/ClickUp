package pl.akademiaqa.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter     // tworzenie getterów przy pomocy biblioteki Lombok (nie pojawiają się w kodzie, ale można z nich korzystać)
@ToString   // tworzenie ToString przy pomocy biblioteki Lombok, jak wyżej

@JsonIgnoreProperties(ignoreUnknown = true)    // przy zamianie jsona na obiekt, ignoruje pola, które nas nie interesują
public class CreateTaskResponseDto {

    // w responsie otrzymujemy jsona, który posiada dużo pól
    // nas interesują tylko te poniżej:
    private String id;
    private String name;
    @JsonProperty("text_content")
    // w response json jest 'text_content' a my stosujemy notację java 'texContent'
    // więc powyższy kod powoduje przypisanie wartości z pola 'text_content' do zmiennej 'textContent'
    private String textContent;
    private String description;
    // te pola poniżej (status i creator) w responsie są pod-obiektami, dlatego tworzymy dodatkowe klasy:
    // CreateTaskCreatorResponseDto i CreateTaskStatusResponseDto
    // a z nich wyciągamy tylko interesujące nas pola:
    // dla status: status i type; dla creator: username i email
    private CreateTaskStatusResponseDto status;
    private CreateTaskCreatorResponseDto creator;


    // ZAMIAST GENEROWANIA GETTERÓW I TOSTRING UŻYWAMY BIBLIOTEKI LOMBOK, PATRZ WYŻEJ

    // generowanie getterów:

    //public String getId() {
    //    return id;
    //}

    //public String getName() {
    //    return name;
    //}

    //public String getTextContent() {
    //    return textContent;
    //}

    //public String getDescription() {
    //    return description;
    //}

    //public CreateTaskStatusResponseDto getStatus() {
    //    return status;
    //}

    //public CreateTaskCreatorResponseDto getCreator() {
    //    return creator;
    //}

    // generowanie toString():

    // @Override
    // public String toString() {
    //     return "CreateTaskResponseDto{" +
    //             "id='" + id + '\'' +
    //             ", name='" + name + '\'' +
    //             ", textContent='" + textContent + '\'' +
    //             ", description='" + description + '\'' +
    //             ", status=" + status +
    //             ", creator=" + creator +
    //             '}';
    // }
}
