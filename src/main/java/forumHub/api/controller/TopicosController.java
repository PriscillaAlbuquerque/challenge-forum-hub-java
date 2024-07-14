package forumHub.api.controller;

import forumHub.api.topicos.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //indicando para o spring carregar a classe.indica que todos os métodos da classe devem retornar dados diretamente no corpo da resposta HTTP, serializados como JSON.
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

    @GetMapping //verbo get para listar todos os topicos;
    public Page<DadosListagemTopicos> listar(@PageableDefault(size = 10, sort = {"curso"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemTopicos::new);

    }


    @GetMapping("/{id}")
    public Topicos listarTopico(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Topico não encontrado"));
    }

//    //@GetMapping("/{id}")
//    public Topicos listarTopico(@PathVariable Long id) {
//        var topicos = repository.getReferenceById(id);
//        return topicos;
//    }
//    @PutMapping ("/{id}")// usado para atualizar
//    @Transactional //usado para o insert, fazer uma escrita no banco.
//    public void atualizar( @PathVariable Long id, @RequestBody DadosAtualizacaoTopicos dados){
//        var topicos = repository.getReferenceById(dados.id());
//        topicos.atualizarInformacoes(dados);
//
//


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
        return ResponseEntity.noContent().build();
    }


}
