package forumHub.api.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;


public record DadosCadastroTopicos(


        @NotBlank  // verifica que o campo não pode ser nulo e nem vazio
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        @Past
        LocalDateTime data,
        @NotNull
        Status status,
        @NotBlank
        String autor,
        @NotBlank
        String curso) {
}
