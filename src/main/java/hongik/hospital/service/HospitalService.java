package hongik.hospital.service;

import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.doctor.DoctorRepository;
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
    private final DoctorRepository doctorRepository;


    public void addDoctor(Long doctorId, Long hospitalId) {

        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(
                () -> new CustomApiException("존재하지 않는 병원 입니다")
        );

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                () -> new CustomApiException("존재하지 않는 의사 입니다")
        );

        hospital.addDoctor(doctor);
        doctorRepository.save(doctor);
    }

    public Hospital findByName(String name) {
        return hospitalRepository.findByName(name).orElseThrow(
                () -> new CustomApiException("서비스 오류 : 존재하지 않는 병원 입니다")
        );
    }
}
