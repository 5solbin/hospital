package hongik.hospital.dto.hospital;

import hongik.hospital.domain.hospital.Address;
import hongik.hospital.domain.hospital.Hospital;
import lombok.Getter;
import lombok.Setter;

public class HospitalReqDto {

    @Getter
    @Setter
    public static class JoinReqDto {
        private String username;
        private String password;
        private String name;
        private Address address;

        public Hospital toEntity() {
            return Hospital.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .address(address)
                    .build();
        }
    }

    @Getter @Setter
    public static class LoginReqDto {
        private String username;
        private String password;
    }
}
