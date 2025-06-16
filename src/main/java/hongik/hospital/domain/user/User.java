package hongik.hospital.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@NoArgsConstructor
@Getter
@Table(name = "users") // user는 syntax 예약어니까 꼭 테이블 명을 비슷하게 변경하자
public abstract class User {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

//    @Builder 는 객체를 사용하지 못하는 추상 클래스의 특성상 안된다.
    public User(Long id, String password, String username, Role role) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.role = role;
    }
}
