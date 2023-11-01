package hospital.hongik.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter @Setter
public class Reservation {
    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    private LocalDateTime reservation_time;
}
