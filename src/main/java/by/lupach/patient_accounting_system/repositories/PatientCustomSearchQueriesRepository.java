package by.lupach.patient_accounting_system.repositories;

import by.lupach.patient_accounting_system.dto.PatientCustomSearchQueries;
import by.lupach.patient_accounting_system.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PatientCustomSearchQueriesRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "CALL GetPatientWardAndPhoneByName(:patientName)", nativeQuery = true)
    List<PatientCustomSearchQueries> getPatientWardAndPhoneByName(@Param("patientName") String patientName);

    @Query(value = "CALL GetPatientsByDate(:specifiedDate)", nativeQuery = true)
    List<PatientCustomSearchQueries> getPatientsByDate(@Param("specifiedDate") Date specifiedDate);

    @Query(value = "CALL GetFemalePatientsByAge(:specifiedAge)", nativeQuery = true)
    List<PatientCustomSearchQueries> getFemalePatientsByAge(@Param("specifiedAge") int specifiedAge);
}