package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@AllArgsConstructor
@Setter
public class PatientResponse {
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String disease;
    private boolean deleted;
    private Long hospitalId;
    private Long userId;
    private String message;


    public PatientResponse() {

    }
}
