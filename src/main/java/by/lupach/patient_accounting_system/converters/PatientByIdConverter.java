package by.lupach.patient_accounting_system.converters;

import by.lupach.patient_accounting_system.entities.Patient;
import by.lupach.patient_accounting_system.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PatientByIdConverter implements Converter<String, Patient> {
    @Autowired
    PatientService patientService;

    @Override
    public Patient convert(String source) {
        return patientService.getById(Integer.parseInt(source)).get();
    }
}
