package by.lupach.patient_accounting_system.services;

import by.lupach.patient_accounting_system.dto.PatientCustomSearchQueries;
import by.lupach.patient_accounting_system.repositories.PatientCustomSearchQueriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientCustomSearchQueriesService {
    @Autowired
    private PatientCustomSearchQueriesRepository patientCustomSearchQueriesRepository;


    public Optional<List<PatientCustomSearchQueries>> getPatientWardAndPhoneByName(String patientName){
        return Optional.ofNullable(patientCustomSearchQueriesRepository.getPatientWardAndPhoneByName(patientName));
    }

    public Optional<List<PatientCustomSearchQueries>> getPatientsByDate(Date specifiedDate) {
        return Optional.ofNullable(patientCustomSearchQueriesRepository.getPatientsByDate(specifiedDate));
    }

    public Optional<List<PatientCustomSearchQueries>> getFemalePatientsByAge(int specifiedAge) {
        return Optional.ofNullable(patientCustomSearchQueriesRepository.getFemalePatientsByAge(specifiedAge));
    }

}
