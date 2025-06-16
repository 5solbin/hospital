package hongik.hospital.domain.doctor;

import hongik.hospital.domain.user.Role;
import hongik.hospital.domain.user.User;
import hongik.hospital.domain.doctorReservation.DoctorReservation;
import hongik.hospital.domain.hospital.Hospital;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("doctor")
public class Doctor extends User {

    private String name;
    private Long career;
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private final List<DoctorReservation> doctorReservations = new ArrayList<>();

    @Builder
    public Doctor(Long id, String name, String username, String password, Long career, Department department) {
        super(id, username, password,Role.Doctor);
        this.name = name;
        this.career = career;
        this.department = department;
    }

    public void assignHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void addDoctorReservation(DoctorReservation dr) {
        doctorReservations.add(dr);
        dr.assignDoctor(this);
    }

    @Override
    public Role getRole() {
        return Role.Doctor;
    }

}
