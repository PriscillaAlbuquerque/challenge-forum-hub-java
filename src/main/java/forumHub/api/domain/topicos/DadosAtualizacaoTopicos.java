package forumHub.api.domain.topicos;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopicos(
                                      String mensagem,
                                      String titulo ) {
}
