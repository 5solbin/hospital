package hongik.hospital.service;

import hongik.hospital.domain.doctor.Doctor;
import hongik.hospital.domain.doctor.DoctorRepository;
import hongik.hospital.domain.hospital.Hospital;
import hongik.hospital.dummy.DummyObject;
import hongik.hospital.handler.ex.CustomApiException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class HospitalServiceTest extends DummyObject {

    @Autowired
    HospitalService hospitalService;
    @Autowired
    DoctorRepository doctorRepository;


    @Test
    public void 의사추가() throws Exception{
        //given
        Doctor doctor = newDoctor("qwer", "오솔빈");
        Hospital hospital = hospitalService.findByName("신과함께");

        //when
        hospital.addDoctor(doctor);
        doctorRepository.save(doctor);

        //then
        Doctor doctorT = doctorRepository.findByUsername("qwer")
                .orElseThrow(() -> new CustomApiException("테스트 : 존재하지 않는 의사 아이디 입니다."));
        Assertions.assertThat(doctorT.getHospital().getName()).isEqualTo("신과함께");
    }
}