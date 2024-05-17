package ma.enset.ebankingbackend.services.interfaces;

import ma.enset.ebankingbackend.dtos.RegisterUserDto;
import ma.enset.ebankingbackend.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(RegisterUserDto registerUserDto);

    User update(User user);

    void existsByUsername(String username);

    User getByUsername(String username);

    User getCurrentUser();
}
