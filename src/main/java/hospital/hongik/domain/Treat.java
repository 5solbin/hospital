package hospital.hongik.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Treats")
@Getter
@Setter
public class Treat {
    @Id @GeneratedValue
    @Column(name = "treat_id")
    private Long id;

    private LocalDateTime treat_time;

    private Long pay;
}
