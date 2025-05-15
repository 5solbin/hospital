package hongik.hospital.domain.patient;

import hongik.hospital.domain.patientReservation.PatientReservation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class Patient {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String name;
    private Long age;
    private Gender gender;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private final List<PatientReservation> patientReservationList = new ArrayList<>();

    @Builder
    public Patient(Long id, String username, String password, String name, Long age, Gender gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void addPatientReservation(PatientReservation pr) {
        patientReservationList.add(pr);
        pr.assignPatient(this);
    }
}
