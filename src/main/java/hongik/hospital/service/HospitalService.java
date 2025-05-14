package hongik.hospital.service;

import hongik.hospital.domain.hospital.Hospital;
import hongik.hospital.domain.hospital.HospitalRepository;
import hongik.hospital.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static hongik.hospital.dto.hospital.HospitalReqDto.*;
import static hongik.hospital.dto.hospital.HospitalResDto.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Transactional
    public JoinResDto join(JoinReqDto joinReqDto) {

        // 아이디 중복 확인
        Optional<Hospital> hospitalOP = hospitalRepository.findByUsername(joinReqDto.getUsername());
        if (hospitalOP.isPresent()) {
            throw new CustomApiException("이미 존재하는 아이디 입니다.");
        }

        // 회원가입
        Hospital hospital = hospitalRepository.save(joinReqDto.toEntity());

        // 응답 DTO
        return new JoinResDto(hospital);
    }

    public LoginResDto login(LoginReqDto loginReqDto) {

        Hospital hospital = hospitalRepository.findByUsername(loginReqDto.getUsername())
                .filter(h -> h.getPassword().equals(loginReqDto.getPassword()))
                .orElseThrow(() -> new CustomApiException("아이디 또는 비밀번호가 일치하지 않습니다"));

        return new LoginResDto(hospital);
    }
}
