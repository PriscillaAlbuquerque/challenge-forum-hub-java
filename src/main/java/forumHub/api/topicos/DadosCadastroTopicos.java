package forumHub.api.topicos;

import jakarta.validation.constraints.NotBlank;


public record DadosCadastroTopicos(


        @NotBlank  // verifica que o campo não pode ser nulo e nem vazio
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso) {
}
