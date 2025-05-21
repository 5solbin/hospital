package hongik.hospital.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ReserReqDto {

    @Getter
    @Setter
    public static class ReservationReqDto {
        private Long patientId;
        private Long doctorId;
        @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "yyyyMMddHHmmss",
                timezone = "Asia/Seoul")
        private LocalDateTime time;
    }

    @Getter @Setter
    public static class DoctorDataReq {
        private Long id;
    }
}
