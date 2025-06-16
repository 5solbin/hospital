package hongik.hospital.dto;

import lombok.Getter;
import lombok.Setter;

public class Login {

    @Getter
    @Setter
    public static class LoginReqDto {
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public static class LoginResDto {
        private String token;
    }
}
