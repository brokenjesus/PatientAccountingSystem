package by.lupach.patient_accounting_system.repositories;

import by.lupach.patient_accounting_system.entities.User;
import by.lupach.patient_accounting_system.entities.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Page<User> findAll(Pageable pageable);
}