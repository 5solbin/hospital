package hongik.hospital.service;

import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.doctor.DoctorRepository;
import hongik.hospital.domain.doctorReservation.DoctorReservation;
import hongik.hospital.domain.doctorReservation.DoctorReservationRepository;
import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.domain.patientReservation.PatientReservation;
import hongik.hospital.domain.patientReservation.PatientReservationRepository;
import hongik.hospital.domain.reservation.Reservation;
import hongik.hospital.domain.reservation.ReservationRepository;
import hongik.hospital.handler.ex.CustomApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ReservationRepository reservationRepository;
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
                .findByPatientIdAndReservation_ReservationTime(reqDto.getPatientId(), reqDto.getTime());

        if (prOP.isPresent()) {
            throw new CustomApiException("환자의 예약이 존재합니다");
        }

        // 의사 측에서 겹치는 날짜 있는지 확인
        Optional<DoctorReservation> drOP = doctorReservationRepository
                .findByDoctorIdAndReservation_ReservationTime(reqDto.getDoctorId(), reqDto.getTime());

        if (drOP.isPresent()) {
            throw new CustomApiException("의사의 예약이 존재합니다");
        }


        // 예약 저장
        PatientReservation pr = new PatientReservation();
        DoctorReservation dr = new DoctorReservation();
        LocalDateTime time = reqDto.getTime();
        LocalDate date = LocalDate.of(time.getYear(), time.getMonth(), time.getDayOfMonth());

        patient.addPatientReservation(pr);
        doctor.addDoctorReservation(dr);

        Reservation reservation = reservationRepository.findByDate(date)
                .orElseGet(() -> reservationRepository.save(Reservation.builder().date(date).build()));

        reservation.addDoctorReservation(dr);
        reservation.addPatientReservation(pr);
        // Dto 반환
        return new ReservationResDto(patient.getName(), doctor.getName(), time);

    }

    @Getter @Setter
    public static class ReservationReqDto {
        private Long patientId;
        private Long doctorId;
        private LocalDateTime time;
    }

    @Getter @Setter
    public static class ReservationResDto {
        private String patientName;
        private String doctorName;
        private LocalDateTime time;

        public ReservationResDto(String patientName, String doctorName, LocalDateTime time) {
            this.patientName = patientName;
            this.doctorName = doctorName;
            this.time = time;
        }
    }

}
