package hongik.hospital.domain.patientReservation;

import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Getter
public class PatientReservation {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public void  assignReservation (Reservation reservation) {
        this.reservation = reservation;
    }

    public void assignPatient(Patient patient) {
        this.patient = patient;
    }
}
