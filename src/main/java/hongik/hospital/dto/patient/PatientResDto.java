package hongik.hospital.dto.patient;

import hongik.hospital.domain.patient.Patient;
import lombok.Getter;
import lombok.Setter;

public class PatientResDto {
    @Getter
    @Setter
    public static class JoinResDto {
        private String username;
        private String name;

        public JoinResDto(Patient patient) {
            this.username = patient.getUsername();
            this.name = patient.getName();
        }
    }
}
