package hongik.hospital.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class ResponseDto<T> {

    // 응답의 DTO는 한번 만들어지면 수정될 일이 없다
    private final Integer code; // 1 성공 -1 실패
    private final String msg;
    private final T data;
}
