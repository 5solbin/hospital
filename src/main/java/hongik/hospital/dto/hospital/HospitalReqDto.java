package hongik.hospital.dto.hospital;

import hongik.hospital.domain.hospital.Address;
import hongik.hospital.domain.hospital.Hospital;
import lombok.Getter;
import lombok.Setter;

public class HospitalReqDto {


    @Getter @Setter
    public static class LoginReqDto {
        private String username;
        private String password;
    }
}
