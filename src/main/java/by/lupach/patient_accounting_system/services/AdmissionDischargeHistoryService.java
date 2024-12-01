package by.lupach.patient_accounting_system.services;

import by.lupach.patient_accounting_system.entities.AdmissionDischargeHistory;
import by.lupach.patient_accounting_system.repositories.AdmissionDischargeHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdmissionDischargeHistoryService {
    private static final Logger logger = LoggerFactory.getLogger(AdmissionDischargeHistoryService.class);

    @Autowired
    private AdmissionDischargeHistoryRepository historyRepository;

    public Optional<List<AdmissionDischargeHistory>> getByPatientId(Integer patientId) {
        logger.info("Fetching admission/discharge history for patient with ID: {}", patientId);
        Optional<List<AdmissionDischargeHistory>> result = Optional.ofNullable(historyRepository.findByPatientId(patientId));
        logger.info("Found {} history records for patient with ID: {}", result.map(List::size).orElse(0), patientId);
        return result;
    }

    public Optional<AdmissionDischargeHistory> save(AdmissionDischargeHistory history) {
        logger.info("Saving admission/discharge history for patient with ID: {}", history.getPatient().getId());
        AdmissionDischargeHistory savedHistory = historyRepository.save(history);
        logger.info("Saved history with ID: {}", savedHistory.getId());
        return Optional.of(savedHistory);
    }

    public Optional<AdmissionDischargeHistory> getById(Integer historyId) {
        logger.info("Fetching admission/discharge history with ID: {}", historyId);
        Optional<AdmissionDischargeHistory> result = Optional.of(historyRepository.getById(historyId));
        logger.info("Found history with ID: {}", historyId);
        return result;
    }

    public void deleteById(Integer id) {
        logger.info("Deleting admission/discharge history with ID: {}", id);
        historyRepository.deleteById(id);
        logger.info("Deleted history with ID: {}", id);
    }
}
