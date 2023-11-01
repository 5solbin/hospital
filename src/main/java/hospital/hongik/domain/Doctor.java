package hospital.hongik.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Doctor {
    @Id @GeneratedValue
    @Column(name = "doctor_id")
    private Long id;

    private String name;

    private Long career;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<DoctorReservation> doctorReservations = new ArrayList<>();

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<DoctorTreat> doctorTreats = new ArrayList<>();
}
