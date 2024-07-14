package forumHub.api.topicos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @Column(name = "data")
    private LocalDateTime data;
    @Enumerated (EnumType.STRING)
    private Status status;
    private String autor;
    private String curso;

    public Topicos(DadosCadastroTopicos dados) {
        this.titulo = dados.titulo();
        this.mensagem=dados.mensagem();
        this.data = dados.data();
        this.status = dados.status();
        this.autor=dados.autor();
        this.curso=dados.curso();
    }

    // o spring entende que se eu não colocar o dado no json, ele deve ser nulo, então pra ele entender que não deve ser nulo e que tem que atualizar os campos que eu mandar na requisição, eu preciso colocar essa condição
    public void atualizarInformacoes(DadosAtualizacaoTopicos dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo();

        }

        if (dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }

    }
}
