package by.lupach.patient_accounting_system.services;

import by.lupach.patient_accounting_system.entities.AdmissionDischargeHistory;
import by.lupach.patient_accounting_system.repositories.AdmissionDischargeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdmissionDischargeHistoryService {

    @Autowired
    private AdmissionDischargeHistoryRepository historyRepository;

    public Optional<List<AdmissionDischargeHistory>> getByPatientId(Integer patientId) {
        return Optional.ofNullable(historyRepository.findByPatientId(patientId));
    }

    public Optional<AdmissionDischargeHistory> save(AdmissionDischargeHistory history) {
        return Optional.of(historyRepository.save(history));
    }

    public Optional<AdmissionDischargeHistory> getById(Integer historyId) {
        return Optional.of(historyRepository.getById(historyId));
    }

    public void deleteById(Integer id) {
        historyRepository.deleteById(id);
    }
}
