package hongik.hospital.service;

import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.doctor.DoctorRepository;
import hongik.hospital.domain.doctorReservation.DoctorReservation;
import hongik.hospital.domain.doctorReservation.DoctorReservationRepository;
import hongik.hospital.domain.hospital.Hospital;
import hongik.hospital.domain.hospital.HospitalRepository;
import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.domain.patientReservation.PatientReservation;
import hongik.hospital.domain.patientReservation.PatientReservationRepository;
import hongik.hospital.domain.reservation.Reservation;
import hongik.hospital.domain.reservation.ReservationRepository;
import hongik.hospital.dto.reservation.ReserReqDto;
import hongik.hospital.dto.reservation.ReserReqDto.ReservationReqDto;
import hongik.hospital.dto.reservation.ReserResDto.HospitalData;
import hongik.hospital.dto.reservation.ReserResDto.ReservationResDto;
import hongik.hospital.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static hongik.hospital.dto.reservation.ReserResDto.*;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ReservationRepository reservationRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorReservationRepository doctorReservationRepository;
    private final PatientReservationRepository patientReservationRepository;

    @Transactional
    public ReservationResDto reservation(ReservationReqDto reqDto) {

        // 의사, 환자 존재 확인
        Patient patient = patientRepository.findById(reqDto.getPatientId())
                .orElseThrow(() -> new CustomApiException("존재하지 않는 유저입니다"));
        Doctor doctor = doctorRepository.findById(reqDto.getDoctorId())
                .orElseThrow(() -> new CustomApiException("존재하지 않는 의사 입니다"));

        // 환자 측에서 겹치는 날짜 있는지 확인
        Optional<PatientReservation> prOP = patientReservationRepository
                .findByPatientIdAndTime(reqDto.getPatientId(), reqDto.getTime());

        if (prOP.isPresent()) {
            throw new CustomApiException("환자의 예약이 존재합니다");
        }

        // 의사 측에서 겹치는 날짜 있는지 확인
        Optional<DoctorReservation> drOP = doctorReservationRepository
                .findByDoctorIdAndTime(reqDto.getDoctorId(), reqDto.getTime());

        if (drOP.isPresent()) {
            throw new CustomApiException("의사의 예약이 존재합니다");
        }


        // 예약 저장
        PatientReservation pr = new PatientReservation();
        DoctorReservation dr = new DoctorReservation();

        patient.addPatientReservation(pr);
        doctor.addDoctorReservation(dr);

        // 예약 엔티티에는 날짜기준이라서 변환해줌
        LocalDateTime time = reqDto.getTime();
        LocalDate date = LocalDate.of(time.getYear(), time.getMonth(), time.getDayOfMonth());


        Reservation reservation = reservationRepository.findByDate(date)
                .orElseGet(() -> reservationRepository.save(Reservation.builder().date(date).build()));

        reservation.addDoctorReservation(dr);
        reservation.addPatientReservation(pr);

        // Dto 반환
        return new ReservationResDto(patient.getName(), doctor.getName(), time);

    }

    // 병원을 알려줬을 때 의사 목록 출력
    public HospitalData getHospitalDoctor(ReserReqDto.DoctorDataReq doctorDataReq) {

        // 병원 찾기
        Hospital hospital = hospitalRepository.findById(doctorDataReq.getId()).orElseThrow(
                () ->  new CustomApiException("존재하지 않는 병원입니다.")
        );

        // 의사 리스트 추출하기
        List<Doctor> doctors = doctorRepository.findByHospital(hospital);

        //
        Map<String, DoctorData> doctorDataMap = new HashMap<>();

        for (Doctor doctor : doctors) {
            DoctorData doctorData = new DoctorData(doctor);
            doctorDataMap.put(doctor.getName(), doctorData);
        }

        return new HospitalData(hospital.getName(),doctorDataMap);
    }

}
