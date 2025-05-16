package hongik.hospital.domain.doctorReservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class DoctorReservation {

    @Id
    @GeneratedValue
    @Column(name = "doctor_reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyyMMddHHmmss",
            timezone = "Asia/Seoul")
    private LocalDateTime time;

    public void assignReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void assignDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Builder
    public DoctorReservation(Long id, Doctor doctor, Reservation reservation, LocalDateTime time) {
        this.id = id;
        this.doctor = doctor;
        this.reservation = reservation;
        this.time = time;
    }


}
