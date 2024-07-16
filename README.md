

![image](https://github.com/user-attachments/assets/76d5f203-8ca7-48b8-b864-153d9ec75413)





# Projeto Forumhub

Bem-vindo ao meu projeto de  Back End do curso da Alura!

História:

Um fórum é um espaço onde todos os participantes de uma plataforma podem colocar suas perguntas sobre determinados assuntos. Na alura, os alunos e alunas utilizam o fórum para tirar suas dúvidas sobre os cursos e projetos em que estão participando. Este lugar mágico está cheio de muita aprendizagem e colaboração entre alunos, professores e moderadores.

Já sabemos para que serve o fórum e sabemos sua aparência, mas sabemos como ele funciona por trás dos panos? Isto é, onde se armazenam as informações? Como são tratados os dados para que se relacione um tópico com uma resposta, ou como se relacionam os usuários com as respostas de um tópico?

Esta foi a proposta chamado de FórumHub: nele, vamos replicar este processo no nível do back end e, para isso, criaremos uma API REST usando Spring.

Nossa API se concentrará especificamente nos tópicos, e deve permitir aos usuários:

* Criar um novo tópico;

* Mostrar todos os tópicos criados;

* Mostrar um tópico específico;

* Atualizar um tópico;

* Eliminar um tópico.

É o que conhecemos normalmente como CRUD (CREATE, READ, UPDATE, DELETE)*  e usaremos um framework que facilitará muito o nosso trabalho.

*Tradução livre (em ordem): Criar, Consultar, Atualizar e Deletar.

Em resumo, nosso objetivo com este projeto é implementar uma API REST com as seguintes funcionalidades:

* API com rotas implementadas seguindo as melhores práticas do modelo REST;

* Validações realizadas segundo as regras de negócio;

* Implementação de uma base de dados relacional para a persistência da informação;

* Serviço de autenticação/autorização para restringir o acesso à informação.

Além disso a API foi documentada e personalizada com Swagger:

![image](https://github.com/user-attachments/assets/1723e9cb-4c4d-4f89-8100-819364189e55)


O primeiro passo é fazer o login, ele vai gerar um token e esse token vai autorizar a mexer em todas as requisições, da mesma forma que fiz no meu teste de API que fiz no insomnia:

![image](https://github.com/user-attachments/assets/319e10a9-8f4d-4777-9308-1bf315c6b25d)



# Principais Tecnologias utilizadas:

* Spring Boot;
* Spring Data JPA;
* MySql;
* Maven;
* Insomnia


![image](https://github.com/user-attachments/assets/b0220a47-ca3d-4b5d-a182-9f2fd35c8477)


