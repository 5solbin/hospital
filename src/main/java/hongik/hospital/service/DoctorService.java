package hongik.hospital.service;

import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.doctor.DoctorRepository;
import hongik.hospital.dto.doctor.DoctorReqDto.JoinReqDto;
import hongik.hospital.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static hongik.hospital.dto.doctor.DoctorReqDto.*;
import static hongik.hospital.dto.doctor.DoctorResDto.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Transactional
    public JoinResDto join(JoinReqDto joinReqDto) {

        // 아이디 중복 확인
        Optional<Doctor> doctorOP = doctorRepository.findByUsername(joinReqDto.getUsername());
        if (doctorOP.isPresent()) {
            throw new CustomApiException("이미 존재하는 아이디 입니다.");
        }

        // 회원가입
        Doctor doctor = doctorRepository.save(joinReqDto.toEntity());

        // 응답 DTO
        return new JoinResDto(doctor);
    }

    public LoginResDto login(LoginReqDto loginReqDto) {

        Doctor doctor = doctorRepository.findByUsername(loginReqDto.getUsername())
                .filter(d -> d.getPassword().equals(loginReqDto.getPassword()))
                .orElseThrow(() -> new CustomApiException("아이디 또는 비밀번호가 일치하지 않습니다"));

        return new LoginResDto(doctor);
    }
}
