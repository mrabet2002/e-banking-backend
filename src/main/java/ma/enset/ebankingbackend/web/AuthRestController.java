package ma.enset.ebankingbackend.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbackend.dtos.LoginResponse;
import ma.enset.ebankingbackend.dtos.RegisterUserDto;
import ma.enset.ebankingbackend.dtos.UserCredentials;
import ma.enset.ebankingbackend.services.interfaces.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/auth")
@Slf4j
public class AuthRestController {
    private final AuthService authService;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserCredentials userCredentials) {
        return ResponseEntity.ok(authService.login(userCredentials));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterUserDto registerUserDto) {
        authService.registerUser(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
