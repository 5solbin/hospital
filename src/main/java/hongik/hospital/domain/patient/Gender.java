package hongik.hospital.domain.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    MAlE("남자"), FEMALE("여자");

    private String value;
}
