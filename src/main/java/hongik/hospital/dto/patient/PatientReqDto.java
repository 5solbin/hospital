package hongik.hospital.dto.patient;

import hongik.hospital.domain.patient.Gender;
import hongik.hospital.domain.patient.Patient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PatientReqDto {
    @Getter
    @Setter
    public static class JoinReqDto {
        private String username;
        private String password;
        private String name;
        private Gender gender;
        private Long age;

        public Patient toEntity() {
            return Patient.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .gender(gender)
                    .age(age)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class LoginReqDto {
        private String username;
        private String password;
    }
}
