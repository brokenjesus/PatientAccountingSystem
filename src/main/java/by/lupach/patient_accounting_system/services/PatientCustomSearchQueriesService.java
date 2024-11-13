package by.lupach.patient_accounting_system.services;

import by.lupach.patient_accounting_system.dto.PatientCustomSearchQueries;
import by.lupach.patient_accounting_system.repositories.PatientCustomSearchQueriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientCustomSearchQueriesService {
    @Autowired
    private PatientCustomSearchQueriesRepository patientCustomSearchQueriesRepository;

    public Optional<List<PatientCustomSearchQueries>> getPatientWardAndPhoneByName(String patientName) {
        List<Object[]> results = patientCustomSearchQueriesRepository.getPatientWardAndPhoneByName(patientName);
        return convertToWardAndPhoneDto(results);
    }

    public Optional<List<PatientCustomSearchQueries>> getPatientsByDate(Date specifiedDate) {
        List<Object[]> results = patientCustomSearchQueriesRepository.getPatientsByDate(specifiedDate);
        return convertToPatientsByDateDto(results);
    }

    public Optional<List<PatientCustomSearchQueries>> getFemalePatientsByAge(int specifiedAge) {
        List<Object[]> results = patientCustomSearchQueriesRepository.getFemalePatientsByAge(specifiedAge);
        return convertToFemalePatientsDto(results);
    }

    // Уникальный метод преобразования для просмотра телефона и номера палаты
    private Optional<List<PatientCustomSearchQueries>> convertToWardAndPhoneDto(List<Object[]> results) {
        if (results == null) {
            return Optional.empty();
        }

        List<PatientCustomSearchQueries> patientList = new ArrayList<>();
        for (Object[] row : results) {
            String patientName = (String) row[0];
            Integer age = (Integer) row[1];
            String phone = (String) row[2];
            String wardNumber = (String) row[3];

            patientList.add(new PatientCustomSearchQueries(patientName, age, wardNumber, phone, null, null)); // admissionDate = null
        }
        return Optional.of(patientList);
    }

    // Уникальный метод преобразования для списка больных на заданное число
    private Optional<List<PatientCustomSearchQueries>> convertToPatientsByDateDto(List<Object[]> results) {
        if (results == null) {
            return Optional.empty();
        }

        List<PatientCustomSearchQueries> patientList = new ArrayList<>();
        for (Object[] row : results) {
            String patientName = (String) row[0];
            Integer age = (Integer) row[1];
            Date transferDate = (Date) row[2];
            String wardNumber = (String) row[3];

            patientList.add(new PatientCustomSearchQueries(patientName, age, wardNumber, null,null, transferDate)); // phone = null
        }
        return Optional.of(patientList);
    }

    // Уникальный метод преобразования для женщин, достигших заданного возраста
    private Optional<List<PatientCustomSearchQueries>> convertToFemalePatientsDto(List<Object[]> results) {
        if (results == null) {
            return Optional.empty();
        }

        List<PatientCustomSearchQueries> patientList = new ArrayList<>();
        for (Object[] row : results) {
            String patientName = (String) row[0];
            Integer age = (Integer) row[1];
            Date admissionDate = (Date) row[2];

            patientList.add(new PatientCustomSearchQueries(patientName, age, null, null, admissionDate, null)); // wardNumber и phone = null
        }
        return Optional.of(patientList);
    }
}
