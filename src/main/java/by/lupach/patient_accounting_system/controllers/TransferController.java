package by.lupach.patient_accounting_system.controllers;

import by.lupach.patient_accounting_system.entities.Patient;
import by.lupach.patient_accounting_system.entities.Transfer;
import by.lupach.patient_accounting_system.services.PatientService;
import by.lupach.patient_accounting_system.services.TransferService;
import by.lupach.patient_accounting_system.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private WardService wardService;

    private final static int PAGE_SIZE = 20;


    @GetMapping()
    public String listTransfers(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Transfer> transferPage = transferService.getAll(page, PAGE_SIZE).get();
        model.addAttribute("transferPage", transferPage);
        return "transfers";
    }

    @GetMapping("/search")
    public String searchTransfers(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "wardNumber", required = false) String wardNumber,
            @RequestParam(value = "patientName", required = false) String patientName) {

        Page<Transfer> transferPage = transferService.searchByWardNumberOrPatientName(wardNumber, patientName, PageRequest.of(page, PAGE_SIZE)).get();

        model.addAttribute("transferPage", transferPage);
        model.addAttribute("wardNumber", wardNumber);
        model.addAttribute("patientName", patientName);
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

    @PostMapping("/create-transfer")
    public String createTransfer(Transfer transfer){
        transferService.save(transfer);
        return "redirect:/transfers";
    }

    @GetMapping("/delete-transfer/{id}")
    public String createTransfer(@PathVariable("id") Integer id){
        transferService.deleteById(id);
        return "redirect:/transfers";
    }

    @GetMapping("/edit-transfer/{id}")
    public String editTransfer(@PathVariable("id") Integer id, Model model) {
        Optional<Transfer> transfer = transferService.findById(id);
        if (transfer.isPresent()) {
            model.addAttribute("transfer",  transfer.get());
            model.addAttribute("wards", wardService.getAvailableWards().get());
            return "edit_transfer";
        } else {
            return "redirect:/transfers"; // если пациент не найден, редирект на список
        }
    }

    @PostMapping("/edit-transfer/{id}")
    public String updateTransfer(@PathVariable("id") Integer id, @ModelAttribute Transfer transfer) {
        transfer.setId(id); // Ensure the ID is set for the update
        transfer.setPatient(patientService.getById(transfer.getPatient().getId()).get());
        transfer.setWard(wardService.getById(transfer.getWard().getId()).get());
        transferService.save(transfer);
        return "redirect:/transfers";
    }

}
