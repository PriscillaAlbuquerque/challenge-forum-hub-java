package forumHub.api.domain.topicos;

import java.time.LocalDateTime;

public record DadosListagemTopicos(Long id, String titulo, String mensagem, LocalDateTime data, Status status, String autor, String curso) {

    public DadosListagemTopicos(Topicos topicos){
        this(topicos.getId(),topicos.getTitulo(), topicos.getMensagem(), topicos.getData(), topicos.getStatus(), topicos.getAutor(), topicos.getCurso());
    }
}