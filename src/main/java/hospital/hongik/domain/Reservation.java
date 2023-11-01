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

    private LocalDateTime reservation_time;

    @OneToMany(mappedBy = "reservation",cascade = CascadeType.ALL)
    private List<PatientReservation> patientReservations = new ArrayList<>();

    @OneToMany(mappedBy = "reservation",cascade = CascadeType.ALL)
    private List<DoctorReservation> doctorReservations = new ArrayList<>();
}
