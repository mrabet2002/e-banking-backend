package ma.enset.ebankingbackend.services.interfaces;


import ma.enset.ebankingbackend.dtos.LoginResponse;
import ma.enset.ebankingbackend.dtos.RegisterUserDto;
import ma.enset.ebankingbackend.dtos.UserCredentials;
import ma.enset.ebankingbackend.entities.User;

public interface AuthService {
    void registerUser(RegisterUserDto registerUserDto);

    LoginResponse login(UserCredentials userCredentials);

    User getCurrentUser();
}
