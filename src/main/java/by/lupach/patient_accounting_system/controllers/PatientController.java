package by.lupach.patient_accounting_system.controllers;

import by.lupach.patient_accounting_system.dto.PatientCustomSearchQueries;
import by.lupach.patient_accounting_system.entities.Patient;
import by.lupach.patient_accounting_system.services.PatientCustomSearchQueriesService;
import by.lupach.patient_accounting_system.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientCustomSearchQueriesService patientCustomSearchQueriesService;

    private static final int PAGE_SIZE = 20;


    @PostMapping("/create-patient")
    public String createPatient(Patient patient) {
        patientService.save(patient);
        return "redirect:/patients";
    }

    @GetMapping()
    public String patients(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Patient> patientsPage = patientService.getAll(page, PAGE_SIZE).get();
        model.addAttribute("patientsPage", patientsPage);
        return "patients";
    }

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

    @GetMapping("/search")
    public String searchPatients(@RequestParam String name, @RequestParam(defaultValue = "0") int page, Model model) {
        Page<Patient> patientsPage = patientService.getByNameContaining(name,page, PAGE_SIZE).get();
        model.addAttribute("patientsPage", patientsPage);
        model.addAttribute("name", name); // Сохраняем текущее имя для отображения
        return "patients";
    }

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

//    //CUSTOM QUERIES HANDLER
    @GetMapping("/custom-query/get-patient-ward")
    public String getPatientWardAndPhoneByName(@RequestParam String name, Model model) {
        List<PatientCustomSearchQueries> result = patientCustomSearchQueriesService.getPatientWardAndPhoneByName(name).orElse(new ArrayList<>());
        model.addAttribute("customQueryResult", result);
        return "patient_custom_search_result";
    }

    // Custom query for patients on a specific date
    @GetMapping("/custom-query/get-patients-by-date")
    public String getPatientsByDate(@RequestParam Date date, Model model) {
        List<PatientCustomSearchQueries> result = patientCustomSearchQueriesService.getPatientsByDate(date).orElse(new ArrayList<>());
        model.addAttribute("customQueryResult", result);
        return "patient_custom_search_result";
    }

    // Custom query for female patients by age
    @GetMapping("/custom-query/get-female-by-age")
    public String getFemalePatientsByAge(@RequestParam int age, Model model) {
        List<PatientCustomSearchQueries> result = patientCustomSearchQueriesService.getFemalePatientsByAge(age).orElse(new ArrayList<>());
        model.addAttribute("customQueryResult", result);
        return "patient_custom_search_result";
    }
}
