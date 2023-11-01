package hospital.hongik.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Hospital {
    @Id @GeneratedValue
    @Column(name = "hospital_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;
}
