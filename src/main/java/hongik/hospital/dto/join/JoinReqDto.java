package hongik.hospital.dto.join;

import hongik.hospital.domain.doctor.Department;
import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.hospital.Address;
import hongik.hospital.domain.hospital.Hospital;
import hongik.hospital.domain.patient.Gender;
import hongik.hospital.domain.patient.Patient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JoinReqDto {

    public interface JoinReq{
        String getUsername();
        String getPassword();
    }

    @Getter
    @Setter
    public static class PatientJoinReq implements JoinReq{
        private String username;
        private String password;
        private String name;
        private Gender gender;
        private Long age;

        public Patient toEntity(BCryptPasswordEncoder encoder) {
            return Patient.builder()
                    .username(username)
                    .password(encoder.encode(password))
                    .name(name)
                    .gender(gender)
                    .age(age)
                    .build();
        }
    }


    @Getter
    @Setter
    public static class HospitalJoinReq implements JoinReq {
        private String username;
        private String password;
        private String name;
        private Address address;

        public Hospital toEntity(BCryptPasswordEncoder encoder) {
            return Hospital.builder()
                    .username(username)
                    .password(encoder.encode(password))
                    .name(name)
                    .address(address)
                    .build();
        }
    }

    @Getter @Setter
    public static class DoctorJoinReq implements JoinReq {
        private String username;
        private String password;
        private String name;
        private Long career;
        private Department department;

        public Doctor toEntity(BCryptPasswordEncoder encoder) {
            return Doctor.builder()
                    .username(username)
                    .password(encoder.encode(password))
                    .name(name)
                    .career(career)
                    .department(department)
                    .build();
        }
    }



}
