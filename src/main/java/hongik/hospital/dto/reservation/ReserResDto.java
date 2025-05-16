package hongik.hospital.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

}
