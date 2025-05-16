package hongik.hospital.dummy;

import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.doctor.DoctorRepository;
import hongik.hospital.domain.hospital.Hospital;
import hongik.hospital.domain.hospital.HospitalRepository;
import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.patient.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyInit extends DummyObject{

    @Bean
    CommandLineRunner init(PatientRepository patientRepository, DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {


        return (args) -> {
            Patient kim = patientRepository.save(newPatient("kim", "김민진"));
            Patient park = patientRepository.save(newPatient("park", "박상구"));

            Doctor choi = doctorRepository.save(newDoctor("choi", "최형운"));
            Doctor oh = doctorRepository.save(newDoctor("oh", "오병택"));

            Hospital god = hospitalRepository.save(newHospital("god", "신과함께", "서울시", "잠실"));
            Hospital bad = hospitalRepository.save(newHospital("bad", "나쁜손", "서울시", "마포구"));
        };
    }
}
