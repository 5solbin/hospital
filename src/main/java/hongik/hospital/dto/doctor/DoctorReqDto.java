package hongik.hospital.dto.doctor;

import hongik.hospital.domain.doctor.Department;
import hongik.hospital.domain.doctor.Doctor;
import lombok.Getter;
import lombok.Setter;

// 별별 : 유효성 검사 추가
// DTO는 외부에서 사용할 일이 많으니 public 으로 열자;;
public class DoctorReqDto {

    @Getter @Setter
    public static class LoginReqDto {
        private String username;
        private String password;
    }

}
