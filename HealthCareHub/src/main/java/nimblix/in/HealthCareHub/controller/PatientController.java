package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.request.PatientRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @DeleteMapping("/soft-delete")
    public ResponseEntity<?> softDeletePatient(
            @RequestBody PatientRequest request) {

        //Request body null
        if (request == null || request.getId() == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Patient id is required");
        }

        try {

            PatientResponse response =
                    patientService.softDeletePatient(request);

            return ResponseEntity.ok(response);

        }
        // Patient not found
        catch (RuntimeException ex) {

            if (ex.getMessage().equals("Patient not found")) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ex.getMessage());
            }

            //Already deleted
            if (ex.getMessage().equals("Patient already deleted")) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ex.getMessage());
            }

            throw ex;
        }


    }
}