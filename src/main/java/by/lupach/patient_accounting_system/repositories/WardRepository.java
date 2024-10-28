package by.lupach.patient_accounting_system.repositories;

import by.lupach.patient_accounting_system.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface WardRepository extends JpaRepository<Ward, Integer> {
    List<Ward> findAll();
    @Query(value = "CALL GetAvailableWards()", nativeQuery = true)
    List<Ward> findAvailableWards();
}