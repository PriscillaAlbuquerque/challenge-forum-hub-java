package forumHub.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice // estou indicando que o spring vai carregar essa classe e que ela é para tratar erros
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class) // anotação para dizer que o método é para tratar erro.
    public ResponseEntity tratarErro404 (){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // anotação quanto tem algum campo invalido
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors(); // vai devolver uma lista contendo os erros
        return  ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    private record DadosErroValidacao(String campo, String mensagem){ // são os campos que quero devolver na mensagem

        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
