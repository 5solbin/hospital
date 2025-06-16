package hongik.hospital.domain.hospital;

import hongik.hospital.domain.user.User;
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
@DiscriminatorValue("hospital")
public class Hospital extends User {

    private String name;

    @Embedded
    private Address address;
    @OneToMany (mappedBy = "hospital", cascade = CascadeType.ALL)
    private final List<Doctor> doctors = new ArrayList<>();

    @Builder
    public Hospital(Long id, String name, String username, String password, Address address) {
        super(id, username, password);
        this.name = name;
        this.address = address;
    }


    public void addDoctor(Doctor doctor) {
        doctor.assignHospital(this);
        doctors.add(doctor);
    }
}
