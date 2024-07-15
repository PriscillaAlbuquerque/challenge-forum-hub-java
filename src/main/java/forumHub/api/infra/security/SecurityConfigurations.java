package forumHub.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable()) // Desabilita o CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/auth/**")).permitAll() // Permite acesso livre a /api/v1/auth
//                        .anyRequest().authenticated() // Requer autenticação para outras requisições
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Define a política de sessão como STATELESS
//                )
//                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro de autenticação JWT
//                .build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ // aqui estou dizendo que estou usando o método Bcrypt para hash de senhas
        return new BCryptPasswordEncoder();
    }
}




// csrf: quero desabilitar os a proteção contra os ataques csrf, pois vamos trabalhar com tokens;
//session management que é pra mostrar como vai ser o gerenciamento da sessão.
// session creation policy que é pra mostrar qual a politica de gerenciamento de sessão
