package ma.enset.ebankingbackend.services.interfaces;

import ma.enset.ebankingbackend.entities.User;

public interface JwtService {
    String generateToken(User user);

}
