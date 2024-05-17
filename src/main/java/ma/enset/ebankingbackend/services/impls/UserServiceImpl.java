package ma.enset.ebankingbackend.services.impls;

import lombok.RequiredArgsConstructor;
import ma.enset.ebankingbackend.dtos.RegisterUserDto;
import ma.enset.ebankingbackend.entities.User;
import ma.enset.ebankingbackend.enums.Role;
import ma.enset.ebankingbackend.mappers.UserMapper;
import ma.enset.ebankingbackend.repos.UserRepository;
import ma.enset.ebankingbackend.services.interfaces.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void saveUser(RegisterUserDto registerUserDto) {
        User user = userMapper.toUser(registerUserDto);
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void existsByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already taken!");
        }
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email/username: " + username)
        );
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            String username = jwt.getSubject();
            return (User) this.loadUserByUsername(username);
        } else {
            throw new IllegalStateException("Principal is not a Jwt object");
        }
    }
}
