package hongik.hospital.domain.doctor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public Doctor(Long id, String name, String username, String password, Long career, Department department) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.career = career;
        this.department = department;
    }

}
