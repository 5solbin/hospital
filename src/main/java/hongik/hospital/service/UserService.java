package hongik.hospital.service;

import hongik.hospital.domain.doctor.DoctorRepository;
import hongik.hospital.domain.hospital.HospitalRepository;
import hongik.hospital.domain.user.User;
import hongik.hospital.domain.user.UserRepository;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.dto.join.JoinReqDto.JoinReq;
import hongik.hospital.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static hongik.hospital.dto.join.JoinReqDto.*;
import static hongik.hospital.dto.join.JoinResDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository   userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public JoinRes Join(JoinReq joinReq) {


        // 1. 동일 유저네임 검사
        Optional<User> user = userRepository.findByUsername(joinReq.getUsername());
        if (user.isPresent()) {
            throw new CustomApiException("동일 유저네임이 존재합니다");
        }

        // 2. 유저 타입 검사, 회원가입 진행
        if (joinReq instanceof PatientJoinReq) {
            return new JoinRes(patientRepository.save(((PatientJoinReq) joinReq).toEntity(encoder)));
        }
        else if (joinReq instanceof DoctorJoinReq) {
            return new JoinRes(doctorRepository.save(((DoctorJoinReq) joinReq).toEntity(encoder)));
        }
        else {
            return new JoinRes(hospitalRepository.save(((HospitalJoinReq) joinReq).toEntity(encoder)));
        }
    }

}
