package hongik.hospital.domain.patientReservation;

import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
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

    private LocalDateTime time;

    public void  assignReservation (Reservation reservation) {
        this.reservation = reservation;
    }

    public void assignPatient(Patient patient) {
        this.patient = patient;
    }

    @Builder
    public PatientReservation(Long id, Patient patient, Reservation reservation, LocalDateTime time) {
        this.id = id;
        this.patient = patient;
        this.reservation = reservation;
        this.time = time;
    }
}
