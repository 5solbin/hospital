package hongik.hospital.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    Patient("환자"), Doctor("의사"), Hospital("병원");

    private String value;
}
