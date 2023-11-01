package hospital.hongik.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctor_treat")
@Getter
@Setter
public class DoctorTreat {
    @Id
    @GeneratedValue
    @Column(name = "doctor_treat_id")
    private Long id;
}
