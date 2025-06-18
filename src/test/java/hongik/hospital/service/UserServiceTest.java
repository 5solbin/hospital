package hongik.hospital.service;

import hongik.hospital.domain.patient.Gender;
import hongik.hospital.domain.patient.Patient;
import hongik.hospital.domain.patient.PatientRepository;
import hongik.hospital.domain.user.UserRepository;
import hongik.hospital.dto.join.JoinReqDto.PatientJoinReq;
import hongik.hospital.dto.join.JoinResDto;
import hongik.hospital.dummy.DummyObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest extends DummyObject {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private PatientRepository patientRepository;

    @Test
    public void 환자_회원가입() throws Exception{
        //given
        PatientJoinReq patientJoinReq = new PatientJoinReq();
        patientJoinReq.setUsername("hong");
        patientJoinReq.setPassword("1234");
        patientJoinReq.setAge(21L);
        patientJoinReq.setName("홍길동");
        patientJoinReq.setGender(Gender.MAlE);

        // stub 1  유저네임으로 찾기를 실행하게 되면 >> 빈 옵셔널 객체를 생성한다.
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        // stub 2 아무거나 환자 레포에 저장을 하게 되면 홍길동을 반환한다.
        Patient patient = newPatient("hong","홍길동");
        when(patientRepository.save(any())).thenReturn(patient);

        //when
        JoinResDto.JoinRes join = userService.Join(patientJoinReq);
        System.out.println("테스트 : " + join);

        //then
        Assertions.assertThat(join.getName()).isEqualTo("홍길동");
    }

}