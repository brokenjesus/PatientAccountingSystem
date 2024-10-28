package by.lupach.patient_accounting_system.repositories;

import by.lupach.patient_accounting_system.entities.AdmissionDischargeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionDischargeHistoryRepository extends JpaRepository<AdmissionDischargeHistory, Integer> {
    List<AdmissionDischargeHistory> findByPatientId(Integer patientId);
}
