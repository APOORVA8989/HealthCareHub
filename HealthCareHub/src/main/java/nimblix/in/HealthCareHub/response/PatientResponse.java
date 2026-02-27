package nimblix.in.HealthCareHub.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String disease;

    private Long userId;
    private Long hospitalId;

    private String createdTime;
    private String updatedTime;

    private Boolean isDeleted;

    private String message;
}

