
# Sistema gerenciador de solicitações

Sistema para facilitar e otimizar a gerência das solicitações dos consumidores de uma concessionária do setor elétrico.

- Para acessar o diagrama de casos de uso UML, [clique aqui](https://github.com/cassiasilvaR/gerenciador/blob/master/images/Casos%20de%20uso%20UML).
- Para acessar o modelo entidade relacionamento, [clique aqui](https://github.com/cassiasilvaR/gerenciador/blob/master/images/Modelo%20entidade-relacionamento.png). Também é possível acessar outro modelo entidade relacionamento, com a notação "pé de galinha" [aqui](https://github.com/cassiasilvaR/gerenciador/blob/master/images/Diagrama%20entidade-relacionamento.png).
- O script para criação das tabelas do banco de dados está disponível em [src/sql](https://github.com/cassiasilvaR/gerenciador/blob/master/src/sql/script.sql).




## Documentação

O sistema gerenciador é capaz de receber dois tipos de solicitações de um consumidor: reclamação ou reavaliação de metas. Em sua base de dados, é possível registrar usuários, que podem ser consumidores ou funcionários, bem como as solicitações e os pareceres emitidos por funcionários.




## Funcionalidades

- Criar usuário (consumidor ou funcionário)
- Realizar login
- Criar solicitação (Consumidor)
- Avaliar solicitação (Funcionário atendente)
- Direcionar solicitação para o departamento responsável 
- Analisar solicitação (Funcionário avaliador)
- Emitir parecer 
- Estudar solicitação (Funcionário gerente)
- Aprovar ou reprovar solicitação 
- Responder o cliente 
## Compilação

/> javac Main.java
    
## Execução 
/> java Main    
## Aprendizados

Através deste desafio pude compreender como uma boa documentação de software é capaz de acelerar o processo de desenvolvimento de um projeto. Neste período, as etapas de criação da modelagem uml e do modelo entidade relacionamento foram de suma importância para o desenvolvimento das classes, que viriam a ser desenvolvidas com a linguagem de programação java, e das tabelas do banco de dados. 
 


## Melhorias

- Implementação das funcionalidades dos funcionários responsáveis pelas reclamações
- Implementação da emissão de estatísticas 
- Implementação de uma arquitetura cliente/servidor, em que seja possível ter acesso simultâneo de mais de um cliente

## Autores

- [@cassiasilvaR](https://github.com/cassiasilvaR)


