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

/**
 * User 엔티티를 상속하고 있는 의사 도메인
 * 이름, 커리어, 과 속성 포함
 *  병원, 의사-예약 N:M 관계 사이에 있는 의사예약 테이블과 관계를 맺고 있다.
 *  */

// check : 유효성 검사 추가

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
