package forumHub.api.controller;

import forumHub.api.topicos.DadosCadastroTopicos;
import forumHub.api.topicos.DadosListagemTopicos;
import forumHub.api.topicos.TopicoRepository;
import forumHub.api.topicos.Topicos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //método para fazer a listagem:

    //http://localhost:8080/topicos?size=1&page=1 onde size é o tamanho, ou seja, um dado e 1 pagina
    //http://localhost:8080/topicos?sort=data ordenando por data

    @GetMapping //verbo get para listar os topicos;
    public Page<DadosListagemTopicos> listar(@PageableDefault(size = 10, sort={"curso"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemTopicos::new);

    }

}
