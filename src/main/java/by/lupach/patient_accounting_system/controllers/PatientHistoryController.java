package by.lupach.patient_accounting_system.controllers;

import by.lupach.patient_accounting_system.entities.AdmissionDischargeHistory;
import by.lupach.patient_accounting_system.entities.Patient;
import by.lupach.patient_accounting_system.services.AdmissionDischargeHistoryService;
import by.lupach.patient_accounting_system.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients/patient-history")
public class PatientHistoryController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AdmissionDischargeHistoryService admissionDischargeHistoryService;

    @GetMapping("/{id}")
    public String getPatientHistory(@PathVariable("id") int patientId, Model model) {
        Patient patient = patientService.getById(patientId).get(); // Fetch patient details
        List<AdmissionDischargeHistory> history = admissionDischargeHistoryService.getByPatientId(patientId).get(); // Fetch history

        model.addAttribute("patient", patient);
        model.addAttribute("admissionDischargeHistory", history);
        return "patient_history";
    }

    @PostMapping("/{id}/add_history")
    public String save(@PathVariable("id") int patientId, AdmissionDischargeHistory admissionDischargeHistory) {
        admissionDischargeHistory.setPatient(patientService.getById(patientId).get());
        admissionDischargeHistoryService.save(admissionDischargeHistory);
        return "redirect:/patients/patient-history/"+patientId;
    }

    @GetMapping("/{patientId}/edit/{historyId}")
    public String edit(@PathVariable("patientId") int patientId, @PathVariable("historyId") int historyId, Model model){
        Patient patient = patientService.getById(patientId).get(); // Fetch patient details
        AdmissionDischargeHistory history = admissionDischargeHistoryService.getById(historyId).get(); // Fetch history

        model.addAttribute("patient", patient);
        model.addAttribute("history", history);
        return "patient_history_edit";
    }

    @PostMapping("/{patientId}/edit/{historyId}")
    public String edit(@PathVariable("patientId") int patientId,
                       @PathVariable("historyId") int historyId,
                       @ModelAttribute AdmissionDischargeHistory admissionDischargeHistory) {
        admissionDischargeHistory.setId(historyId);
        admissionDischargeHistory.setPatient(patientService.getById(patientId).get());
        admissionDischargeHistoryService.save(admissionDischargeHistory);
        return "redirect:/patients/patient-history/" + patientId;
    }

    @GetMapping("/{patientId}/delete/{historyId}")
    public String delete(@PathVariable("patientId") int patientId, @PathVariable("historyId") int historyId) {
        admissionDischargeHistoryService.deleteById(historyId);
        return "redirect:/patients/patient-history/"+patientId;
    }
}
