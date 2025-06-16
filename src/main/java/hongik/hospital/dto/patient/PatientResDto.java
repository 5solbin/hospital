package hongik.hospital.dto.patient;

import hongik.hospital.domain.patient.Patient;
import lombok.Getter;
import lombok.Setter;

public class PatientResDto {


    @Getter
    @Setter
    public static class LoginResDto{
        private String name;

        public LoginResDto(Patient patient) {
            this.name = patient.getName();
        }
    }

}
