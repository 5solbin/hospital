package hongik.hospital.controller;

import hongik.hospital.dto.ResponseDto;
import hongik.hospital.dto.join.JoinReqDto.JoinReq;
import hongik.hospital.dto.join.JoinResDto.JoinRes;
import hongik.hospital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/join")
    public ResponseEntity<?> join(@RequestBody JoinReq joinReqDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(new ResponseDto<>(-1, "유효성 검사 실패 ", errorMap), HttpStatus.BAD_REQUEST);
        }

        JoinRes join = userService.Join(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 성공", join), HttpStatus.OK);
    }
}
