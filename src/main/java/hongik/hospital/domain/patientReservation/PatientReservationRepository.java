package hongik.hospital.domain.patientReservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PatientReservationRepository extends JpaRepository<PatientReservation, Long> {

    Optional<PatientReservation> findByPatientIdAndTime(Long patientId, LocalDateTime time);
}
