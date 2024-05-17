package ma.enset.ebankingbackend.services.impls;

import lombok.RequiredArgsConstructor;
import ma.enset.ebankingbackend.config.JwtConfig;
import ma.enset.ebankingbackend.entities.User;
import ma.enset.ebankingbackend.services.interfaces.JwtService;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtConfig jwtConfig;
    private final JwtEncoder jwtEncoder;

    @Override
    public String generateToken(User user) {
        JwtEncoderParameters parameters = JwtEncoderParameters
                .from(JwsHeader.with(MacAlgorithm.from(jwtConfig.getAlgorithm())).build(), setClaims(user));

        return jwtEncoder.encode(parameters).getTokenValue();
    }

    private JwtClaimsSet setClaims(User user) {
        return JwtClaimsSet.builder()
                .issuer(jwtConfig.getIssuer())
                .expiresAt(
                        Instant.now().plusMillis(jwtConfig.getExpiration())
                )
                .subject(user.getUsername())
                .claim("scope", user.getRole().name())
                .claim("name", user.getName())
                .build();
    }
}
