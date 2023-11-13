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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treat_id")
    private Treat treat;

    //==연관관계 메서드==//
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctor.getDoctorTreats().add(this);
    }

    public void setTreat(Treat treat) {
        this.treat = treat;
        treat.getDoctorTreats().add(this);
    }
}
