package hongik.hospital.service;

import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.doctor.DoctorRepository;
import hongik.hospital.domain.hospital.Hospital;
import hongik.hospital.domain.hospital.HospitalRepository;
import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.dto.reservation.ReserReqDto.ReservationReqDto;
import hongik.hospital.dto.reservation.ReserResDto.HospitalData;
import hongik.hospital.dto.reservation.ReserResDto.ReservationResDto;
import hongik.hospital.dummy.DummyObject;
import hongik.hospital.handler.ex.CustomApiException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static hongik.hospital.dto.reservation.ReserReqDto.*;


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

    @Test
    public void 병원별_의사목록() throws Exception{
        //given
        Hospital hospital = hospitalRepository.findByName("신과함께").orElseThrow(
                () -> new CustomApiException("해당 이름의 병원이 존재하지 않습니다.")
        );
        DoctorDataReq doctorDataReq = new DoctorDataReq();
        doctorDataReq.setId(hospital.getId());


        //when
        HospitalData hospitalDoctor1 = reservationService.getHospitalDoctor(doctorDataReq);

        System.out.println("병원 이름 : " + hospitalDoctor1.getName());
        System.out.println("의사 이름 : " +  hospitalDoctor1.getDoctors());

        //then
        Assertions.assertThat(hospitalDoctor1.getName()).isEqualTo("신과함께");
        Assertions.assertThat(hospitalDoctor1.getDoctors().size()).isEqualTo(2);
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