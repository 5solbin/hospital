package hospital.hongik.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patient_reservation")
@Getter @Setter
public class PatientReservation {
    @Id @GeneratedValue
    @Column(name = "patient_reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    //==연관관계 메서드==//
    public void setPatient(Patient patient) {
        this.patient = patient;
        patient.getPatientReservations().add(this);
    }

    public void setReservation(Reservation reservation){
        this.reservation = reservation;
        reservation.getPatientReservations().add(this);
    }
}
