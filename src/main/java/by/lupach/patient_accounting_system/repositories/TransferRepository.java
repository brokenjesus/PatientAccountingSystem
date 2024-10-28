package by.lupach.patient_accounting_system.repositories;

import by.lupach.patient_accounting_system.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
//    List<Transfer> findAllByFromAccount_IdOrderByFromAccount_Id(Integer fromAccountId);
    List<Transfer> findAll();
}
