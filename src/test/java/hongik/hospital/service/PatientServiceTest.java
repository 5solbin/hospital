package hongik.hospital.service;

import hongik.hospital.domain.patient.Gender;
import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.service.PatientService.JoinReqDto;
import hongik.hospital.service.PatientService.JoinResDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class PatientServiceTest {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientService patientService;

    @Test
    public void 회원가입() throws Exception{
        //given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setName("솔빈");
        joinReqDto.setUsername("solbin");
        joinReqDto.setPassword("1234");
        joinReqDto.setAge(24L);
        joinReqDto.setGender(Gender.MAlE);

        //when
        JoinResDto join = patientService.join(joinReqDto);

        //then
        Assertions.assertThat(join.getName()).isEqualTo("솔빈");
        Assertions.assertThat(join.getUsername()).isEqualTo("solbin");

    }


}