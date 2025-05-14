package hongik.hospital.dto.hospital;

import hongik.hospital.domain.hospital.Hospital;
import lombok.Getter;
import lombok.Setter;

public class HospitalResDto {

    @Getter
    @Setter
    public static class JoinResDto {
        private String username;
        private String name;

        public JoinResDto(Hospital hospital) {
            this.username = hospital.getUsername();
            this.name = hospital.getName();
        }
    }

    @Getter @Setter
    public static class LoginResDto {
        private String name;

        public LoginResDto(Hospital hospital) {
            this.name = hospital.getName();
        }
    }
}
