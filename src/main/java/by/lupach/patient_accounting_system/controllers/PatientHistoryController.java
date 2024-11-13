package by.lupach.patient_accounting_system.controllers;

import by.lupach.patient_accounting_system.entities.*;
import by.lupach.patient_accounting_system.services.AdmissionDischargeHistoryService;
import by.lupach.patient_accounting_system.services.PatientService;
import by.lupach.patient_accounting_system.services.TransferService;
import by.lupach.patient_accounting_system.services.WardService;
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
    private TransferService transferService;
    @Autowired
    private WardService wardService;


    @Autowired
    private AdmissionDischargeHistoryService admissionDischargeHistoryService;

    @GetMapping("/{id}")
    public String getPatientHistory(@PathVariable("id") int patientId, Model model) {
        Patient patient = patientService.getById(patientId).get(); // Fetch patient details
        List<AdmissionDischargeHistory> history = admissionDischargeHistoryService.getByPatientId(patientId).get(); // Fetch history
        List<Ward> wards = wardService.getAvailableWards().get(); // Fetch history

        List<AdmissionDischargeHistory> admissionDischargeHistories = admissionDischargeHistoryService.getByPatientId(patientId).get();

        model.addAttribute("patient", patient);
        model.addAttribute("wards", wards);
        model.addAttribute("admissionDischargeHistory", history);
        if (!admissionDischargeHistories.isEmpty()) {
            AdmissionDischargeHistory lastAdmissionDischargeHistory = admissionDischargeHistories.get(admissionDischargeHistories.size()-1);
            if (lastAdmissionDischargeHistory.getReason() == Reason.DISCHARGE) {
                model.addAttribute("readyToAdmit", true);
            }
        }else {
            model.addAttribute("readyToAdmit", true);
        }
        return "patient_history";
    }

    @PostMapping("/{id}/add_history")
    public String saveHistory(@PathVariable("id") int patientId,
                              @ModelAttribute AdmissionDischargeHistory admissionDischargeHistory,
                              @RequestParam(required = false) Integer wardId) {

        Patient patient = patientService.getById(patientId).orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
        admissionDischargeHistory.setPatient(patient);

        admissionDischargeHistoryService.save(admissionDischargeHistory);

        // If reason is "ADMISSION" and wardId is provided, create a transfer entry with the same date
        if (admissionDischargeHistory.getReason() == Reason.ADMISSION && wardId != null) {
            Transfer transfer = new Transfer();
            transfer.setPatient(patient);
            transfer.setWard(wardService.getById(wardId).orElseThrow(() -> new IllegalArgumentException("Invalid ward ID")));
            transfer.setDate(admissionDischargeHistory.getDate()); // Use the same date as admission/discharge
            transferService.save(transfer);
        }

        return "redirect:/patients/patient-history/" + patientId;
    }

    // Method for editing an admission/discharge entry
    @GetMapping("/{patientId}/edit/{historyId}")
    public String edit(@PathVariable("patientId") int patientId, @PathVariable("historyId") int historyId, Model model) {
        Patient patient = patientService.getById(patientId).orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
        AdmissionDischargeHistory history = admissionDischargeHistoryService.getById(historyId).orElseThrow(() -> new IllegalArgumentException("Invalid history ID"));

        model.addAttribute("patient", patient);
        model.addAttribute("history", history);

        // If it's an admission, load the latest transfer details
        if (history.getReason() == Reason.ADMISSION) {
            model.addAttribute("admited", true);
            model.addAttribute("wards", wardService.getAvailableWards().get());
        }

        return "edit_patient_history"; // Return the edit view
    }

    // Method to update the edited history entry
    @PostMapping("/{patientId}/edit/{historyId}")
    public String updateHistory(@PathVariable("patientId") int patientId,
                                @PathVariable("historyId") int historyId,
                                @ModelAttribute AdmissionDischargeHistory history) {

        AdmissionDischargeHistory existingHistory = admissionDischargeHistoryService.getById(historyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid history ID"));

        // Update existing history fields
        existingHistory.setDate(history.getDate());
        existingHistory.setReason(history.getReason());
        existingHistory.setDiagnosis(history.getDiagnosis());
        existingHistory.setAdmissionMethod(history.getAdmissionMethod());

        admissionDischargeHistoryService.save(existingHistory); // Save updated history

        return "redirect:/patients/patient-history/" + patientId; // Redirect back to patient history
    }

    @GetMapping("/{patientId}/delete/{historyId}")
    public String delete(@PathVariable("patientId") int patientId, @PathVariable("historyId") int historyId) {
        admissionDischargeHistoryService.deleteById(historyId);
        return "redirect:/patients/patient-history/"+patientId;
    }
}
