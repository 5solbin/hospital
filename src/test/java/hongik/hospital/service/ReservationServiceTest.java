package hongik.hospital.service;

import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.doctor.DoctorRepository;
import hongik.hospital.domain.doctorReservation.DoctorReservationRepository;
import hongik.hospital.domain.hospital.Hospital;
import hongik.hospital.domain.hospital.HospitalRepository;
import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.domain.patientReservation.PatientReservationRepository;
import hongik.hospital.domain.reservation.Reservation;
import hongik.hospital.domain.reservation.ReservationRepository;
import hongik.hospital.dto.reservation.ReserReqDto.ReservationReqDto;
import hongik.hospital.dto.reservation.ReserResDto.HospitalData;
import hongik.hospital.dto.reservation.ReserResDto.ReservationResDto;
import hongik.hospital.dummy.DummyObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static hongik.hospital.dto.reservation.ReserReqDto.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ReservationServiceTest extends DummyObject {
    @InjectMocks
    ReservationService reservationService;
    @InjectMocks
    HospitalService hospitalService;
    @Mock
    HospitalRepository hospitalRepository;
    @Mock
    DoctorRepository doctorRepository;
    @Mock
    PatientRepository patientRepository;
    @Mock
    PatientReservationRepository patientReservationRepository;
    @Mock
    DoctorReservationRepository doctorReservationRepository;
    @Mock
    ReservationRepository reservationRepository;

    @Test
    public void 병원별_의사목록() throws Exception{
        //given
        Hospital god = newHospital("god", "신과함께", "서울시", "마포구");
        Doctor kim = newDoctor("kim", "김구");
        Doctor choi = newDoctor("choi", "최일구");

        god.addDoctor(kim);
        god.addDoctor(choi);

        DoctorDataReq doctorDataReq = new DoctorDataReq();
        doctorDataReq.setId(god.getId());

        // stub 1
        when(hospitalRepository.findById(doctorDataReq.getId())).thenReturn(Optional.of(god));

        // stub 2
        when(doctorRepository.findByHospital(any())).thenReturn(List.of(kim, choi));

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
        Patient kim = newPatient("kim", "김민진");
        Patient park = newPatient("park", "박상구");

        Doctor choi = newDoctor("choi", "최형운");
        Doctor oh = newDoctor("oh", "오병택");

        Hospital god = newHospital("god", "신과함께", "서울시", "잠실");
        Hospital bad = newHospital("bad", "나쁜손", "서울시", "마포구");

        Reservation newReservation = new Reservation();

        ReservationReqDto reqDto = new ReservationReqDto();
        reqDto.setDoctorId(choi.getId());
        reqDto.setPatientId(kim.getId());
        reqDto.setTime(LocalDateTime.now().plusDays(7));

        // stub
        when(patientRepository.findById(reqDto.getPatientId())).thenReturn(Optional.of(kim));
        when(doctorRepository.findById(reqDto.getDoctorId())).thenReturn(Optional.of(choi));
        when(doctorReservationRepository.findByDoctorIdAndTime(reqDto.getDoctorId(), reqDto.getTime())).thenReturn(Optional.empty());
        when(patientReservationRepository.findByPatientIdAndTime(reqDto.getPatientId(), reqDto.getTime())).thenReturn(Optional.empty());
        when(reservationRepository.findByDate(reqDto.getTime().toLocalDate())).thenReturn(Optional.of(newReservation));

        //when
        ReservationResDto reservation = reservationService.reservation(reqDto);

        //then
        Assertions.assertThat(reservation.getDoctorName()).isEqualTo("최형운");
        Assertions.assertThat(reservation.getPatientName()).isEqualTo("김민진");
    }


}