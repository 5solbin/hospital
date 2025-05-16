package hongik.hospital.controller;

import hongik.hospital.domain.doctor.DoctorRepository;
import hongik.hospital.dto.ResponseDto;
import hongik.hospital.dto.doctor.DoctorReqDto;
import hongik.hospital.dto.doctor.DoctorResDto;
import hongik.hospital.service.DoctorService;
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

import static hongik.hospital.dto.doctor.DoctorReqDto.*;
import static hongik.hospital.dto.doctor.DoctorResDto.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/join")
    public ResponseEntity<?> join (@RequestBody @Valid JoinReqDto joinReqDto, BindingResult bindingResult) {

        // 유효성 검사 전용 추후에 유효성 관련 기능 추가하면 작동할 예정, @Valid 어노테이션 추가
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(new ResponseDto<>(-1, "유효성 검사 실패", errorMap), HttpStatus.BAD_REQUEST);
        }
        JoinResDto joinResDto = doctorService.join(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 성공", joinResDto), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginReqDto loginReqDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(new ResponseDto<>(-1, "로그인 실패", errorMap), HttpStatus.BAD_REQUEST);
        }

        LoginResDto loginResDto = doctorService.login(loginReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "로그인 성공", loginResDto), HttpStatus.OK);
    }
}
