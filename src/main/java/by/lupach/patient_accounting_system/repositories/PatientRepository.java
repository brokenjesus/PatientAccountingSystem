package by.lupach.patient_accounting_system.repositories;

import by.lupach.patient_accounting_system.entities.Patient;
import by.lupach.patient_accounting_system.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findAll();
    @Query(value = "CALL get_patients_without_transfers()", nativeQuery = true)
    List<Patient> findAvailableToTransferPatients();
}