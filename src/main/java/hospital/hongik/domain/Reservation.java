package hospital.hongik.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations")
@Getter @Setter
public class Reservation {
    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private Status status = Status.YET;

    private int pay = 0;

    @OneToMany(mappedBy = "reservation",cascade = CascadeType.ALL)
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "reservation",cascade = CascadeType.ALL)
    private List<Doctor> doctors = new ArrayList<>();
}
