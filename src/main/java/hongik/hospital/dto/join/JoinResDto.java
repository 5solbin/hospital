package hongik.hospital.dto.join;

import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.hospital.Hospital;
import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.user.User;
import lombok.Getter;
import lombok.Setter;

public class JoinResDto {

    @Getter
    @Setter
    public static class JoinRes {
        private String username;
        private String name;

        public JoinRes(User user) {
            this.username = user.getUsername();
            if (user instanceof Doctor) {
                this.name = ((Doctor) user).getName();
            }
            else if (user instanceof Patient) {
                this.name = ((Patient) user).getName();
            }
            else {
                this.name = ((Hospital) user).getName();
            }
        }
    }
}
