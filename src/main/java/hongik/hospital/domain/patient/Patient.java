package hongik.hospital.domain.patient;

import hongik.hospital.domain.patient.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public Patient(Long id, String username, String password, String name, Long age, Gender gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
