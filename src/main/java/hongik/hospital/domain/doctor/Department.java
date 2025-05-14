package hongik.hospital.domain.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Department {

    LEG("정형외과"), FACE("성형외과"), EYES("안과");

    private String value;
}
