package by.lupach.patient_accounting_system.services;

import by.lupach.patient_accounting_system.entities.Patient;
import by.lupach.patient_accounting_system.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Create or update a patient
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    // Retrieve all patients
    public Optional<List<Patient>> getAll() {
        return Optional.ofNullable(patientRepository.findAll());
    }

    // Retrieve a patient by ID
    public Optional<Patient> getById(Integer id) {
        return patientRepository.findById(id);
    }

    public Optional<List<Patient>> getAvailableToTransferPatients() {
        return Optional.ofNullable(patientRepository.findAvailableToTransferPatients());
    }

    // Delete a patient by ID
    public void deleteById(Integer id) {
        patientRepository.deleteById(id);
    }
}