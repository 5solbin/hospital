package hongik.hospital.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ReserResDto {

    @Getter
    @Setter
    public static class ReservationResDto {
        private String patientName;
        private String doctorName;
        private LocalDateTime time;

        public ReservationResDto(String patientName, String doctorName, LocalDateTime time) {
            this.patientName = patientName;
            this.doctorName = doctorName;
            this.time = time;
        }
    }

}
