package forumHub.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import forumHub.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){

        try {
            var algoritimo  = Algorithm.HMAC256(secret); //senha da api
            return JWT.create()
                    .withIssuer("API Forumhub")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    // verificando se o token é válido e devolvendo o usuário do token:
    public String getSubject (String tokenJWT){
        try {
            var algoritimo  = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer("API Forumhub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){

            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

//    private void verificarExpiracao(String tokenJWT) {
//        try {
//            DecodedJWT decodedJWT = JWT.decode(tokenJWT);
//            Date expirationDate = decodedJWT.getExpiresAt();
//            if (expirationDate != null && expirationDate.before(new Date())) {
//                System.out.println("Token expirado em: " + expirationDate);
//            } else {
//                System.out.println("Token ainda não expirou.");
//            }
//        } catch (Exception e) {
//            System.out.println("Erro ao decodificar o token: " + e.getMessage());
//        }
//    }

}
