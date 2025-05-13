package hongik.hospital.service;

import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.dto.patient.PatientReqDto;
import hongik.hospital.dto.patient.PatientResDto;
import hongik.hospital.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    // 회원 가입
    @Transactional
    public PatientResDto.JoinResDto join(PatientReqDto.JoinReqDto joinReqDto) {
        // 1. 동일 username 존재 검사
        Optional<Patient> patientOP = patientRepository.findByUsername(joinReqDto.getUsername());
        if (patientOP.isPresent()) {
            throw new CustomApiException("동일 유저네임의 회원이 존재합니다");
        }

        // 2. 패스워드 인코딩 + 회원가입
        Patient patientPS = patientRepository.save(joinReqDto.toEntity(passwordEncoder));


        // DTO 응답
        return new PatientResDto.JoinResDto(patientPS);
    }


}
