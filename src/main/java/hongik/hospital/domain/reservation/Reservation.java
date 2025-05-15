package hongik.hospital.domain.reservation;

import hongik.hospital.domain.doctorReservation.DoctorReservation;
import hongik.hospital.domain.patientReservation.PatientReservation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "reservation")
    private List<DoctorReservation> doctorReservations = new ArrayList<>();

    @OneToMany(mappedBy = "reservation")
    private List<PatientReservation> patientReservations = new ArrayList<>();

    private LocalDate date;
    private Long pay = 10000L;

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
