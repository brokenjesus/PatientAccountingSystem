package by.lupach.patient_accounting_system.controllers;

import by.lupach.patient_accounting_system.entities.Transfer;
import by.lupach.patient_accounting_system.services.PatientService;
import by.lupach.patient_accounting_system.services.TransferService;
import by.lupach.patient_accounting_system.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransferController {
    @Autowired
    private TransferService transferService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private WardService wardService;


    @GetMapping("transfers")
    public String transfers() {
        return "transfers";
    }

    @ModelAttribute
    public void addTransfersToModel(Model model){
        model.addAttribute("transfers", transferService.getAll().get());
    }

    @ModelAttribute
    public void addPatientToModel(Model model){
        model.addAttribute("patients", patientService.getAvailableToTransferPatients().get());
    }

    @ModelAttribute
    public void addWardToModel(Model model){
        model.addAttribute("wards", wardService.getAvailableWards().get());
    }

    @PostMapping("/transfers/create-transfer")
    public String createTransfer(Transfer transfer){
        transferService.save(transfer);
        return "redirect:/transfers";
    }

    @GetMapping("/transfers/delete-transfer/{id}")
    public String createTransfer(@PathVariable("id") Integer id, Transfer transfer){
        transferService.deleteById(id);
        return "redirect:/transfers";
    }

    @GetMapping("/transfers/edit-transfer/{id}")
    public String editTransfer(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("transfer",  transferService.findById(id).get());
        model.addAttribute("wards", wardService.getAvailableWards().get());
        return "edit_transfer";
    }

    @PostMapping("/transfers/edit-transfer/{id}")
    public String updateTransfer(@PathVariable("id") Integer id, @ModelAttribute Transfer transfer) {
        transfer.setId(id); // Ensure the ID is set for the update
        transfer.setPatient(patientService.getById(transfer.getPatient().getId()).get());
        transfer.setWard(wardService.getById(transfer.getWard().getId()).get());
        transferService.save(transfer);
        return "redirect:/transfers";
    }

}
