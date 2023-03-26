package ru.leonid.Orderservice.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${auth.jwt.secret}")
    private String secretKey;

    public boolean checkToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();

        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            if (!decodedJWT.getIssuer().equals("userservice")) {
                System.out.println("Error: Incorrect source token service");
                return false;
            }

            if (!decodedJWT.getAudience().contains("simpleshop")) {
                System.out.println("Error: Audience is incorrect");
                return false;
            }
        } catch (JWTVerificationException e) {
            System.out.println("Token is invalid: " + e.getMessage());
            return false;
        }
        return true;
    }
}
