package hongik.hospital.controller;

import hongik.hospital.dto.ResponseDto;
import hongik.hospital.dto.hospital.HospitalReqDto;
import hongik.hospital.dto.hospital.HospitalResDto;
import hongik.hospital.dto.patient.PatientReqDto;
import hongik.hospital.dto.patient.PatientResDto;
import hongik.hospital.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

import static hongik.hospital.dto.hospital.HospitalReqDto.*;
import static hongik.hospital.dto.hospital.HospitalResDto.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid JoinReqDto joinReqDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(new ResponseDto<>(-1,"유효성 검사 실패 ", errorMap), HttpStatus.BAD_REQUEST);
        }

        JoinResDto join = hospitalService.join(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 성공",join), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto loginReqDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(new ResponseDto<>(-1, "유효성 검사 실패", errorMap), HttpStatus.BAD_REQUEST);
        }

        LoginResDto loginResDto = hospitalService.login(loginReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "로그인 성공", loginResDto), HttpStatus.OK);

    }
}
