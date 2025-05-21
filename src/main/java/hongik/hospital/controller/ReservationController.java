package hongik.hospital.controller;

import hongik.hospital.dto.ResponseDto;
import hongik.hospital.dto.reservation.ReserReqDto;
import hongik.hospital.dto.reservation.ReserReqDto.DoctorDataReq;
import hongik.hospital.dto.reservation.ReserReqDto.ReservationReqDto;
import hongik.hospital.dto.reservation.ReserResDto;
import hongik.hospital.dto.reservation.ReserResDto.HospitalData;
import hongik.hospital.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/reservation")
@Controller
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/new")
    public ResponseEntity<?> newReservation(@RequestBody ReservationReqDto reservationReqDto) {

        ReserResDto.ReservationResDto reservation = reservationService.reservation(reservationReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "예약 성공", reservation), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> doctorList(@ModelAttribute DoctorDataReq doctorDataReq) {

        HospitalData hospitalDoctor = reservationService.getHospitalDoctor(doctorDataReq);
        return new ResponseEntity<>(new ResponseDto<>(1, "출력완료", hospitalDoctor), HttpStatus.OK);

    }
}
