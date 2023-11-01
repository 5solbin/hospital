package hospital.hongik.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Patient {

    @Id @GeneratedValue
    @Column (name = "patient_id")
    private Long id;

    private int age;

    private Sex sex;

    private String name;
}
