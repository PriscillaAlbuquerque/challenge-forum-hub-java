package forumHub.api.controller;

import forumHub.api.topicos.DadosCadastroTopicos;
import forumHub.api.topicos.TopicoRepository;
import forumHub.api.topicos.Topicos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //indicando para o spring carregar a classe
@RequestMapping("topicos") // mapeando a url/topicos
public class TopicosController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping // verbo POST para inserir dados
    @Transactional // para usar o metodo insert no banco de dados
    public void cadastrar (@RequestBody DadosCadastroTopicos dados){ // anotacaoRequestBody indica que o metodo cadastrar vai puxar os dados do corpo da requisição
        repository.save(new Topicos(dados));

    }
}
