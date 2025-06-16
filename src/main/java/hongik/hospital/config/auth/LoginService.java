package hongik.hospital.config.auth;

import hongik.hospital.domain.user.User;
import hongik.hospital.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userPs = userRepository.findByUsername(username).orElseThrow(
                () -> new InternalAuthenticationServiceException("인증 실패")
        );
        return new LoginUser(userPs);
    }

}
