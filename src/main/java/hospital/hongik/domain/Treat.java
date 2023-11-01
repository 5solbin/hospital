package hospital.hongik.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "treats")
@Getter
@Setter
public class Treat {
    @Id @GeneratedValue
    @Column(name = "treat_id")
    private Long id;

    private LocalDateTime treat_time;

    private Long pay;

    @OneToMany(mappedBy = "treat",cascade = CascadeType.ALL)
    private List<PatientTreat> patientTreats = new ArrayList<>();

    @OneToMany(mappedBy = "treat",cascade = CascadeType.ALL)
    private List<DoctorTreat> doctorTreats = new ArrayList<>();
}
