package hongik.hospital.domain.doctorReservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DoctorReservationRepository extends JpaRepository<DoctorReservation, Long> {

    Optional<DoctorReservation>  findByDoctorIdAndReservation_ReservationTime(Long doctorId, LocalDateTime reservationTime);

}

