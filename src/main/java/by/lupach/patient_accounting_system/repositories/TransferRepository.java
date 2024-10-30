package by.lupach.patient_accounting_system.repositories;

import by.lupach.patient_accounting_system.entities.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
//    List<Transfer> findAllByFromAccount_IdOrderByFromAccount_Id(Integer fromAccountId);
    List<Transfer> findAll();
    Page<Transfer> findAll(Pageable pageable);
    @Query(value = "CALL SearchTransfersByWardNumberAndPatientName()", nativeQuery = true)
    Page<Transfer> findByWardNumberAndPatientName(
            @Param("wardNumber") String wardNumber,
            @Param("patientName") String patientName,
            Pageable pageable);

}
