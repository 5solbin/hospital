package hongik.hospital.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import hongik.hospital.config.auth.LoginUser;
import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.hospital.Hospital;
import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.user.Role;
import hongik.hospital.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JwtProcess {
    private final Logger log = LoggerFactory.getLogger(getClass());

    // 토큰 생성
    public static String create(LoginUser loginUser) {
        String jwtToken = JWT.create()
                .withSubject("bank")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtVo.EXPIRATION_TIME))
                .withClaim("id", loginUser.getUser().getId())
                .withClaim("role", loginUser.getUser().getRole()+"")
                .sign(Algorithm.HMAC512(JwtVo.SECRET));
        return JwtVo.TOKEN_PREFIX + jwtToken;
    }

    // 토큰 검증 (return 되는 LoginUser 객체를 강제로 시큐리티 세션에 직접 주입할 예정)
    public static LoginUser verify(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JwtVo.SECRET)).build().verify(token);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        User user = newUser(id,role);
        return new LoginUser(user);
    }

    private static User newUser(Long id, String role) {
        if (Role.valueOf(role) == Role.Patient) {
            return Patient.builder().id(id).build();
        }
        else if (Role.valueOf(role) == Role.Doctor) {
            return Doctor.builder().id(id).build();
        }
        else {
            return Hospital.builder().id(id).build();
        }
    }
}
