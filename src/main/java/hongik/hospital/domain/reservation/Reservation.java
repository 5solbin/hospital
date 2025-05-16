package hongik.hospital.domain.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import hongik.hospital.domain.doctorReservation.DoctorReservation;
import hongik.hospital.domain.patientReservation.PatientReservation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
// 날짜별로 구분
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @OneToMany(mappedBy = "reservation")
    private final List<DoctorReservation> doctorReservations = new ArrayList<>();

    @OneToMany(mappedBy = "reservation")
    private final List<PatientReservation> patientReservations = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyyMMdd",
            timezone = "Asia/Seoul")
    private LocalDate date;

    private final Long pay = 10000L;

    @Builder
    public Reservation(LocalDate date) {
        this.date = date;
    }

    public void addPatientReservation(PatientReservation pr) {
        patientReservations.add(pr);
        pr.assignReservation(this);
    }

    public void addDoctorReservation(DoctorReservation dr) {
        doctorReservations.add(dr);
        dr.assignReservation(this);
    }
}
