package hongik.hospital.service;

import hongik.hospital.domain.patient.Gender;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.dto.patient.PatientReqDto.JoinReqDto;
import hongik.hospital.dto.patient.PatientResDto.JoinResDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class PatientServiceTest {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientService patientService;

    // 별별 : stub 사용하여 테스트하는 것도 추가
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