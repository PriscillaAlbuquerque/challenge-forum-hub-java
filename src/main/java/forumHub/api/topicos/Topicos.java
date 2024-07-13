package forumHub.api.topicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter  // gera os metodos getter
@NoArgsConstructor // gera o construtor sem argumento
@AllArgsConstructor // recebe o arqgumento do contrutor
@EqualsAndHashCode (of="id") // indica que as comparações devem ser feitas pelo id.
public class Topicos {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) //atributo id na JPA deve ser gerado automaticamente
    private Long id;


    private String titulo;
    private String mensagem;
    private String autor;
    private String curso;

    public Topicos(DadosCadastroTopicos dados) {
        this.titulo = dados.titulo();
        this.mensagem=dados.mensagem();
        this.autor=dados.autor();
        this.curso=dados.curso();
    }
}
