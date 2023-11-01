package hospital.hongik.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patient_treat")
@Getter @Setter
public class PatientTreat {
    @Id @GeneratedValue
    @Column(name = "patient_treat_id")
    private Long id;

}
