package ma.enset.ebankingbackend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secret;
    private int expiration;
    private String issuer;
    private String algorithm;
//    private String header;
//    private String prefix;
}
