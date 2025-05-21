package hongik.hospital.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import hongik.hospital.domain.doctor.Department;
import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.service.ReservationService;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

public class ReserResDto {

    @Getter
    @Setter
    public static class ReservationResDto {
        private String patientName;
        private String doctorName;
        @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "yyyyMMddHHmmss",
                timezone = "Asia/Seoul")
        private LocalDateTime time;

        public ReservationResDto(String patientName, String doctorName, LocalDateTime time) {
            this.patientName = patientName;
            this.doctorName = doctorName;
            this.time = time;
        }
    }


    @Getter @Setter
    public static class DoctorData {
        private Long career;
        private Department department;

        public DoctorData(Doctor doctor) {
            this.career = doctor.getCareer();
            this.department = doctor.getDepartment();
        }
    }

    @Getter @Setter
    public static class HospitalData {
        private String name;
        private Map<String, DoctorData> doctors;

        public HospitalData(String name, Map<String, DoctorData> doctors) {
            this.name = name;
            this.doctors = doctors;
        }
    }


}
