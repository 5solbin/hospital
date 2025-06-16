package hongik.hospital.dto.patient;

import hongik.hospital.domain.patient.Gender;
import hongik.hospital.domain.patient.Patient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PatientReqDto {

    @Getter
    @Setter
    public static class LoginReqDto {
        private String username;
        private String password;
    }
}
