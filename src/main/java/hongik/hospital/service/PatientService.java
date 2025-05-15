package hongik.hospital.service;

import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static hongik.hospital.dto.patient.PatientReqDto.*;
import static hongik.hospital.dto.patient.PatientResDto.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
//    private final BCryptPasswordEncoder passwordEncoder;


    // 별별 회원 가입 추후에 JWT 때 비밀번호 인코딩 진행
    @Transactional
    public JoinResDto join(JoinReqDto joinReqDto) {
        // 1. 동일 username 존재 검사
        Optional<Patient> patientOP = patientRepository.findByUsername(joinReqDto.getUsername());
        if (patientOP.isPresent()) {
            throw new CustomApiException("동일 유저네임의 회원이 존재합니다");
        }

        // 2. 패스워드 인코딩 + 회원가입
        Patient patientPS = patientRepository.save(joinReqDto.toEntity());


        // DTO 응답
        return new JoinResDto(patientPS);
    }


    // 이후에 JWT 방식으로 다시 개발 예정. 자바 람다 스트림 map 등 공부점 별별
    public LoginResDto login(LoginReqDto loginReqDto) {

        // 아이디, 비밀번호 일치 여부 확인
        Patient loginPatient = patientRepository.findByUsername(loginReqDto.getUsername())
                .filter(p -> p.getPassword().equals(loginReqDto.getPassword()))
                .orElseThrow(() -> new CustomApiException("아이디 또는 비밀번호가 틀렸습니다."));


        // 로그인 성공 , Dto 반환
        return new LoginResDto(loginPatient);
    }




}
