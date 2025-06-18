package hongik.hospital.domain.patient;

import hongik.hospital.domain.user.Role;
import hongik.hospital.domain.user.User;
import hongik.hospital.domain.patientReservation.PatientReservation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * User 엔티티의 하위 클래스인 Patient 엔티티
 * 이름, 나이 ,성별 의 속성을 가진다.
 */
@NoArgsConstructor
@Entity
@Getter
@DiscriminatorValue("patient")
public class Patient extends User {

    private String name;
    private Long age;
    private Gender gender;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private final List<PatientReservation> patientReservationList = new ArrayList<>();

    @Builder
    public Patient(Long id, String username, String password, String name, Long age, Gender gender) {
        super(id,username,password,Role.Patient);
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void addPatientReservation(PatientReservation pr) {
        patientReservationList.add(pr);
        pr.assignPatient(this);
    }

    @Override
    public Role getRole() {
        return Role.Patient;
    }
}
