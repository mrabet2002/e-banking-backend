package ma.enset.ebankingbackend.services.impls;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbackend.dtos.LoginResponse;
import ma.enset.ebankingbackend.dtos.RegisterUserDto;
import ma.enset.ebankingbackend.dtos.UserCredentials;
import ma.enset.ebankingbackend.entities.User;
import ma.enset.ebankingbackend.mappers.UserMapper;
import ma.enset.ebankingbackend.services.interfaces.AuthService;
import ma.enset.ebankingbackend.services.interfaces.JwtService;
import ma.enset.ebankingbackend.services.interfaces.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Override
    public void registerUser(RegisterUserDto registerUserDto) {
        userService.saveUser(this.prepareUserForRegistration(registerUserDto));
    }

    private RegisterUserDto prepareUserForRegistration(RegisterUserDto registerUserDto) {
        this.userService.existsByUsername(registerUserDto.getUsername());
        registerUserDto.setPassword(
                passwordEncoder.encode(registerUserDto.getPassword())
        );

        return registerUserDto;
    }

    private void checkPassword(UserCredentials userCredentials) {
        if (userCredentials.getPassword() == null) {
            throw new RuntimeException("Password is required!");
        }

        if (userCredentials.getPassword().length() < 8)
            throw new RuntimeException("Password must be at least 8 characters long!");
    }

    @Override
    public LoginResponse login(UserCredentials userCredentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userCredentials.getUsername(),
                        userCredentials.getPassword()
                )
        );
        return LoginResponse.builder()
                .accessToken(jwtService.generateToken(
                                (User) authentication.getPrincipal()
                ))
                .build();
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            String username = jwt.getSubject();
            return (User) userService.loadUserByUsername(username);
        } else {
            throw new IllegalStateException("Principal is not a Jwt object");
        }
    }
}
