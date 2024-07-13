package forumHub.api.topicos;

import java.awt.*;
import java.time.LocalDateTime;

public record DadosListagemTopicos(String titulo, String mensagem, LocalDateTime data, Status status, String autor, String curso) {

    public DadosListagemTopicos(Topicos topicos){
        this(topicos.getTitulo(), topicos.getMensagem(), topicos.getData(), topicos.getStatus(), topicos.getAutor(), topicos.getCurso());
    }
}