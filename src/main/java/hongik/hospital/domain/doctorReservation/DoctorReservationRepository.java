package hongik.hospital.domain.doctorReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DoctorReservationRepository extends JpaRepository<DoctorReservation, Long> {

    Optional<DoctorReservation>  findByDoctorIdAndTime(Long doctorId, LocalDateTime time);

}

