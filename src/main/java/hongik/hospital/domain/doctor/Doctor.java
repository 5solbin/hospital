package hongik.hospital.domain.doctor;

import hongik.hospital.domain.doctorReservation.DoctorReservation;
import hongik.hospital.domain.hospital.Hospital;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// 별별 : 의사 병원 관계 추가
@Entity
@Getter
@NoArgsConstructor
public class Doctor {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String password;
    private Long career;
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<DoctorReservation> doctorReservations;

    @Builder
    public Doctor(Long id, String name, String username, String password, Long career, Department department) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
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

}
