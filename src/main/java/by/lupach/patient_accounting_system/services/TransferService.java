package by.lupach.patient_accounting_system.services;

import by.lupach.patient_accounting_system.entities.Patient;
import by.lupach.patient_accounting_system.entities.Transfer;
import by.lupach.patient_accounting_system.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;

    public Optional<List> getAll(){
        return Optional.ofNullable(transferRepository.findAll());
    }

    public Optional<Page<Transfer>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return Optional.ofNullable(transferRepository.findAll(pageable));
    }

    public Optional<Transfer> findById(int id){
        return transferRepository.findById(id);
    }

    public Optional<Page<Transfer>> searchByWardNumberOrPatientName(String wardNumber, String patientName, Pageable pageable) {
        return Optional.ofNullable(transferRepository.findByWardNumberAndPatientName(wardNumber, patientName, pageable));
    }

    public Transfer save(Transfer transfer){
        return transferRepository.save(transfer);
    }

    public void deleteById(Integer id){
        transferRepository.deleteById(id);
    }

//    public void findAllByFromAccount_IdOrderByFromAccount_Id(Integer account_id){}
}
