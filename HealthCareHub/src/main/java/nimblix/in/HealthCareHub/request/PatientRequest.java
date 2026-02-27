package nimblix.in.HealthCareHub.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequest {
    private Long id;
    private String name;
    private Integer age;
}
