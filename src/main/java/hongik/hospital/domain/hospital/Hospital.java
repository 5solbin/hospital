package hongik.hospital.domain.hospital;

import hongik.hospital.domain.doctor.Doctor;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Hospital {

    @Id @GeneratedValue
    @Column(name = "hospital_id")
    private Long id;
    private String name;
    private String username;
    private String password;

    @Embedded
    private Address address;
    @OneToMany (mappedBy = "hospital", cascade = CascadeType.ALL)
    private final List<Doctor> doctors = new ArrayList<>();

    @Builder
    public Hospital(Long id, String name, String username, String password, Address address) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
    }


    public void addDoctor(Doctor doctor) {
        doctor.assignHospital(this);
        doctors.add(doctor);
    }
}
