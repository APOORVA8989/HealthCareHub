package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.request.PatientRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientResponse softDeletePatient(PatientRequest request) {

        Patient patient = patientRepository
                .findById(request.getId())
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));
        if (Boolean.TRUE.equals(patient.getIsDeleted())) {
            throw new RuntimeException("Patient already deleted");
        }


        // Soft delete
        patient.setIsDeleted(true);

        Patient savedPatient = patientRepository.save(patient);

        // Response
        PatientResponse response = new PatientResponse();

        response.setId(savedPatient.getId());
        response.setName(savedPatient.getName());
        response.setAge(savedPatient.getAge());
        response.setGender(savedPatient.getGender());
        response.setPhone(savedPatient.getPhone());
        response.setDisease(savedPatient.getDisease());

        response.setUserId(
                savedPatient.getUser() != null ?
                        savedPatient.getUser().getId() : null);

        response.setHospitalId(
                savedPatient.getHospital() != null ?
                        savedPatient.getHospital().getId() : null);

        response.setCreatedTime(savedPatient.getCreatedTime());
        response.setUpdatedTime(savedPatient.getUpdatedTime());
        response.setIsDeleted(savedPatient.getIsDeleted());

        response.setMessage("Patient soft deleted successfully");

        return response;
    }
}

