package hongik.hospital.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ReserReqDto {

    @Getter
    @Setter
    public static class ReservationReqDto {
        private Long patientId;
        private Long doctorId;
        private LocalDateTime time;
    }
}
