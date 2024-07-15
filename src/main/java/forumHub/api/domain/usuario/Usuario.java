package forumHub.api.domain.usuario;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter  // gera os metodos getter
@NoArgsConstructor // gera o construtor sem argumento
@AllArgsConstructor // recebe o arqgumento do contrutor
@EqualsAndHashCode(of="id") // indica que as comparações devem ser feitas pelo id.
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //atributo id na JPA deve ser gerado automaticamente
    private Long id;
    private String login;
    private String senha;
}
