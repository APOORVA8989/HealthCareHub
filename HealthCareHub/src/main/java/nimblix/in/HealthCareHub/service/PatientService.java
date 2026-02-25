package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.PatientRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;


public interface PatientService {
    PatientResponse softDeletePatient(PatientRequest request);      // For GET all
}
