package hongik.hospital.domain.doctor;

import hongik.hospital.domain.hospital.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Optional<Doctor> findByUsername(String username);

    List<Doctor> findByHospital(Hospital hospital);
}
