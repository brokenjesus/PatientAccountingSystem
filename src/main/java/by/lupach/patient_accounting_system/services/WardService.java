package by.lupach.patient_accounting_system.services;

import by.lupach.patient_accounting_system.entities.Patient;
import by.lupach.patient_accounting_system.entities.Ward;
import by.lupach.patient_accounting_system.repositories.PatientRepository;
import by.lupach.patient_accounting_system.repositories.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class WardService {

        @Autowired
        private WardRepository wardRepository;

        // Create or update a patient
        public Ward save(Ward ward) {
            return wardRepository.save(ward);
        }

        // Retrieve all patients
        public Optional<List<Ward>> getAll() {
            return Optional.ofNullable(wardRepository.findAll());
        }

        // Retrieve a patient by ID
        public Optional<Ward> getById(Integer id) {
            return wardRepository.findById(id);
        }

        // Delete a patient by ID
        public void deleteById(Integer id) {
            wardRepository.deleteById(id);
        }

        public Optional<List<Ward>> getAvailableWards() {
            return Optional.ofNullable(wardRepository.findAvailableWards());
        }
    }
