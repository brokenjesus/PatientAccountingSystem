package by.lupach.patient_accounting_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admission_discharge_history", indexes = {
        @Index(name = "idx_admission_discharge_date", columnList = "date")
})
public class AdmissionDischargeHistory {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    @NonNull
    private Patient patient;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Reason reason;

    @NonNull
    @Column(nullable = false)
    private Date date;

    @NonNull
    @Column(nullable = false)
    private String diagnosis;

    private AdmissionMethod admissionMethod = null;
}
enum AdmissionMethod {
    AMBULANCE, REFERRAL
}





