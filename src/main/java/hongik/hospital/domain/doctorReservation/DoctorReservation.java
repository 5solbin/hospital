package hongik.hospital.domain.doctorReservation;

import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class DoctorReservation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public void assignReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void assignDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
