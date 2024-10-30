package by.lupach.patient_accounting_system.services;

import by.lupach.patient_accounting_system.entities.Ward;
import by.lupach.patient_accounting_system.repositories.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        public Optional<Page<Ward>> getByNumber(String number, PageRequest pageRequest) {
            return Optional.ofNullable(wardRepository.findByNumberContainingIgnoreCase(number, pageRequest));
        }


    // Delete a patient by ID
        public void deleteById(Integer id) {
            wardRepository.deleteById(id);
        }

        public Optional<Page<Ward>> getAll(int page, int size) {
            Pageable pageable = PageRequest.of(page, size);
            return Optional.ofNullable(wardRepository.findAll(pageable));
        }
        public Optional<List<Ward>> getAvailableWards() {
            return Optional.ofNullable(wardRepository.findAvailableWards());
        }
    }
