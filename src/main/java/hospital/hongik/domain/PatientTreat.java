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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treat_id")
    private Treat treat;

    //==연관관계 메서드==//
    public void setPatient(Patient patient) {
        this.patient = patient;
        patient.getPatientTreats().add(this);
    }

    public void setTreat(Treat treat) {
        this.treat = treat;
        treat.getPatientTreats().add(this);
    }
}
