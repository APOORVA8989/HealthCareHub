package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.request.PatientRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepo;

    public PatientServiceImpl(PatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public PatientResponse softDeletePatient(PatientRequest request) {

        // Fetch patient by ID
        Patient patient = patientRepo.findById(request.getId())
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        // -------- Soft Delete --------
        patient.setDeleted(true);

        // Save updated record
        Patient updatedPatient = patientRepo.save(patient);

        // -------- Entity → Response Mapping --------
        PatientResponse response = new PatientResponse();

        response.setId(updatedPatient.getId());
        response.setName(updatedPatient.getName());
        response.setAge(updatedPatient.getAge());
        response.setGender(updatedPatient.getGender());
        response.setPhone(updatedPatient.getPhone());
        response.setDisease(updatedPatient.getDisease());
        response.setDeleted(updatedPatient.isDeleted());
        // Map hospitalId and userId safely
        response.setHospitalId(updatedPatient.getHospital() != null ? updatedPatient.getHospital().getId() : null);
        response.setUserId(updatedPatient.getUser() != null ? updatedPatient.getUser().getId() : null);

        response.setMessage("Patient soft deleted successfully");

        return response;
    }
}