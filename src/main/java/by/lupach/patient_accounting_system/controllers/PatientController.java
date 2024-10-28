package by.lupach.patient_accounting_system.controllers;

import by.lupach.patient_accounting_system.entities.AdmissionDischargeHistory;
import by.lupach.patient_accounting_system.entities.Patient;
import by.lupach.patient_accounting_system.services.AdmissionDischargeHistoryService;
import by.lupach.patient_accounting_system.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private AdmissionDischargeHistoryService admissionDischargeHistoryService;

    @GetMapping("/create-patient")
    public String createPatient() {
        return "create_patient";
    }

    @PostMapping("/create-patient")
    public String createPatient(Patient patient) {
        patientService.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients")
    public String patients() {
        return "patients";
    }

    @ModelAttribute
    public void addPatients(Model model) {
        List<Patient> patients = patientService.getAll().get();
        model.addAttribute("patients", patients);
    }

    // Метод для редактирования пациента
    @GetMapping("/edit-patient/{id}")
    public String editPatient(@PathVariable("id") int id, Model model) {
        Optional<Patient> patient = patientService.getById(id);
        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());
            return "edit_patient";
        } else {
            return "redirect:/patients"; // если пациент не найден, редирект на список
        }
    }

    // Метод для сохранения изменений
    @PostMapping("/edit-patient/{id}")
    public String updatePatient(@PathVariable("id") int id, @ModelAttribute("patient") Patient patient) {
        patient.setId(id); // Устанавливаем ID для существующего пациента
        patientService.save(patient); // Сохраняем обновленного пациента
        return "redirect:/patients";
    }

    @GetMapping("/delete-patient/{id}")
    public String deleteWard(@PathVariable("id") Integer id) {
        patientService.deleteById(id);
        return "redirect:/patients";
    }
}
