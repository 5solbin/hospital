package hongik.hospital.service;

import hongik.hospital.domain.patient.Gender;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.dto.patient.PatientReqDto;
import hongik.hospital.dto.patient.PatientReqDto.JoinReqDto;
import hongik.hospital.dto.patient.PatientResDto;
import hongik.hospital.dto.patient.PatientResDto.JoinResDto;
import hongik.hospital.dto.patient.PatientResDto.LoginResDto;
import hongik.hospital.handler.ex.CustomApiException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static hongik.hospital.dto.patient.PatientReqDto.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class PatientServiceTest {

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

    @Test
    public void 회원아이디중복() throws Exception{
        //given
        JoinReqDto joinReqDto1 = new JoinReqDto();
        joinReqDto1.setName("솔빈");
        joinReqDto1.setUsername("solbin");
        joinReqDto1.setPassword("1234");
        joinReqDto1.setAge(24L);
        joinReqDto1.setGender(Gender.MAlE);

        patientService.join(joinReqDto1);

        //when
        JoinReqDto joinReqDto2 = new JoinReqDto();
        joinReqDto2.setName("크림");
        joinReqDto2.setUsername("solbin");
        joinReqDto2.setPassword("4321");
        joinReqDto2.setAge(5L);
        joinReqDto2.setGender(Gender.MAlE);

        //then
        assertThrows(CustomApiException.class, () ->patientService.join(joinReqDto2));
    }

    @Test
    public void 로그인() throws Exception{
        //given

        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setName("솔빈");
        joinReqDto.setUsername("solbin");
        joinReqDto.setPassword("1234");
        joinReqDto.setAge(24L);
        joinReqDto.setGender(Gender.MAlE);

        patientService.join(joinReqDto);

        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setUsername("solbin");
        loginReqDto.setPassword("1234");

        //when
        LoginResDto login = patientService.login(loginReqDto);


        //then
        Assertions.assertThat(login.getName()).isEqualTo("솔빈");
    }

    @Test
    public void 아이디비밀번호틀림() throws Exception{
        //given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setName("솔빈");
        joinReqDto.setUsername("solbin");
        joinReqDto.setPassword("1234");
        joinReqDto.setAge(24L);
        joinReqDto.setGender(Gender.MAlE);

        patientService.join(joinReqDto);

        //when
        LoginReqDto loginReqDto1 = new LoginReqDto();
        loginReqDto1.setUsername("solbin");
        loginReqDto1.setPassword("123");

        LoginReqDto loginReqDto2 = new LoginReqDto();
        loginReqDto2.setUsername("sol");
        loginReqDto2.setPassword("1234");

        //then
        assertThrows(CustomApiException.class, () -> patientService.login(loginReqDto1));
        assertThrows(CustomApiException.class, () -> patientService.login(loginReqDto2));

    }


}