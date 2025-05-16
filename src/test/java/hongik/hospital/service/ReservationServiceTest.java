package hongik.hospital.service;

import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.doctor.DoctorRepository;
import hongik.hospital.domain.hospital.Hospital;
import hongik.hospital.domain.hospital.HospitalRepository;
import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.dto.reservation.ReserReqDto;
import hongik.hospital.dto.reservation.ReserReqDto.ReservationReqDto;
import hongik.hospital.dto.reservation.ReserResDto;
import hongik.hospital.dto.reservation.ReserResDto.ReservationResDto;
import hongik.hospital.dummy.DummyObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class ReservationServiceTest extends DummyObject {
    @Autowired
    ReservationService reservationService;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    HospitalRepository hospitalRepository;

    @BeforeEach
    public void init() {
        Patient kim = patientRepository.save(newPatient("kim", "김민진"));
        Patient park = patientRepository.save(newPatient("park", "박상구"));

        Doctor choi = doctorRepository.save(newDoctor("choi", "최형운"));
        Doctor oh = doctorRepository.save(newDoctor("oh", "오병택"));

        Hospital god = hospitalRepository.save(newHospital("god", "신과함께", "서울시", "잠실"));
        Hospital bad = hospitalRepository.save(newHospital("bad", "나쁜손", "서울시", "마포구"));
    }

    @Test
    public void 예약하기() throws Exception{
        //given
        Long patientId = 1L;
        Long doctorId = 1L;
        ReservationReqDto reqDto = new ReservationReqDto();
        reqDto.setDoctorId(doctorId);
        reqDto.setPatientId(patientId);
        reqDto.setTime(LocalDateTime.now().plusDays(7));


        //when
        ReservationResDto reservation = reservationService.reservation(reqDto);

        //then
        Assertions.assertThat(reservation.getDoctorName()).isEqualTo("최형운");
        Assertions.assertThat(reservation.getPatientName()).isEqualTo("김민진");

    }

}