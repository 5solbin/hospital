package hongik.hospital.dto.doctor;

import hongik.hospital.domain.doctor.Doctor;
import lombok.Getter;
import lombok.Setter;

public class DoctorResDto {



    @Getter @Setter
    public static class LoginResDto {
        private String name;

        public LoginResDto(Doctor doctor) {
            this.name = doctor.getName();
        }
    }
}
