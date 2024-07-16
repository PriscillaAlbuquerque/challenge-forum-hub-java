package forumHub.api.infra.security;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // LÓGICA DE RECUPERAR O TOKEN: O ENVIO DO TOKEN É ENVIADO NO CABEÇALHO DO PROTOCOLO HTTP.


       var tokenJWT = recuperarToken(request);

        System.out.println(tokenJWT);



        filterChain.doFilter(request, response);
    }


    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null){
            throw new RuntimeException("Token JWT não enviado no cabeçalho authorization!");
        }

        return authorizationHeader.replace("Bearer ", "");




    }
}


// filter de segurança, serve para verificar autorizações, controles de acesso, tokens.
// filter internal: é o método
// filter chain: representa a cadeira de filtros que esta na aplicação.