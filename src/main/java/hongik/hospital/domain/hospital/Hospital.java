package hongik.hospital.domain.hospital;

import hongik.hospital.domain.user.Role;
import hongik.hospital.domain.user.User;
import hongik.hospital.domain.doctor.Doctor;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * 유저를 상속하는 병원 엔티티
 * 이름, 주소 속성을 가지고 있다.
 * 의사와의 연관관계 메서드를 포함하고 있고, 연관관계의 주인이라고 인식하였다.
 */

 // Check : 병원 엔티티가 꼭 필요한 부분이신지
@Entity
@NoArgsConstructor
@Getter
@DiscriminatorValue("hospital")
public class Hospital extends User {

    private String name;

    @Embedded
    private Address address;
    @OneToMany (mappedBy = "hospital", cascade = CascadeType.ALL)
    private final List<Doctor> doctors = new ArrayList<>();

    @Builder
    public Hospital(Long id, String name, String username, String password, Address address) {
        super(id, username, password,Role.Hospital);
        this.name = name;
        this.address = address;
    }


    public void addDoctor(Doctor doctor) {
        doctor.assignHospital(this);
        doctors.add(doctor);
    }

    @Override
    public Role getRole() {
        return Role.Hospital;
    }
}
