package by.lupach.patient_accounting_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ward", uniqueConstraints = {
        @UniqueConstraint(columnNames = "number")
})
public class Ward {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String phone;

    @NonNull
    @Column(unique = true, nullable = false)
    private String number;

    @NonNull
    @Column(nullable = false)
    @ColumnDefault("1")
    private Integer bedPlaceCount;
}
