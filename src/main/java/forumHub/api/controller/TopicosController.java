package forumHub.api.controller;

import forumHub.api.domain.topicos.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController //indicando para o spring carregar a classe.indica que todos os métodos da classe devem retornar dados diretamente no corpo da resposta HTTP, serializados como JSON.
@RequestMapping("topicos") // mapeando a url/topicos
@SecurityRequirement(name = "bearer-key") // mesma string da classe de configuração do springDoc
public class TopicosController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping // verbo POST para inserir dados
    @Transactional // para usar o método save no banco de dados

    public ResponseEntity<Void> cadastrar(@RequestBody @Valid DadosCadastroTopicos dados, UriComponentsBuilder uriBuilder) {
        // Cria a entidade a partir dos dados recebidos
        Topicos topicos = new Topicos(dados);

        // Salva a entidade no repositório
        topicos = repository.save(topicos);

        // Constrói a URI para o novo recurso
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicos.getId()).toUri();

        // Retorna a resposta com status 201 Created e a URI do novo recurso
        return ResponseEntity.created(uri).build();
    }


    //método para fazer a listagem:

    //http://localhost:8080/topicos?size=1&page=1 onde size é o tamanho, ou seja, um dado e 1 pagina
    //http://localhost:8080/topicos?sort=data ordenando por data

    @GetMapping //verbo get para listar todos os tópicos;
    public ResponseEntity<Page<DadosListagemTopicos>> listar(@PageableDefault(size = 10, sort = {"curso"}) Pageable paginacao) {

        Page<DadosListagemTopicos> page = repository.findAll(paginacao).map(DadosListagemTopicos::new);
        return ResponseEntity.ok(page);
    }



    @GetMapping("/{id}")
    public Topicos listarTopico(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }



    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoTopicos dados) {

        Optional<Topicos> optionalTopico = repository.findById(id); // o optional é utilizado paea buscar valores que podem ou não estar presentes

        //caso o tópico não seja encontrado, precisa retornar um erro (404)
        if (optionalTopico.isEmpty()) {
           return ResponseEntity.notFound().build();
        }

        Topicos topico = optionalTopico.get();

        // Atualizar as informações do tópico com os dados fornecidos
        topico.atualizarInformacoes(dados);

        // Salvar as mudanças
        repository.save(topico);

        // Retornar uma resposta de sucesso
        return ResponseEntity.ok("Tópico atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluirTopico(@PathVariable Long id) {
        // Buscar o tópico no banco de dados usando o ID da URL
        Optional<Topicos> optionalTopico = repository.findById(id);

        if (optionalTopico.isEmpty()) {
            // Se o tópico não for encontrado, retornar um erro 404
            return ResponseEntity.notFound().build();
        }

        // Excluir o tópico encontrado
        repository.deleteById(id);

        // Retornar uma resposta de sucesso
        return ResponseEntity.noContent().build(); // aqui estou usando no content para inidicar que a requisição foi procesada, porém não há conteúdo a ser exibido.
    }


}
