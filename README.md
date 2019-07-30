# Intelipost: Teste prático para Backend Developer
Nome: Elvis Justino da Silva
----------------------------

## Stack de Tecnologias utilizadas
* Java 8
* Spring Boot
* Spring Data
* Spring Rest
* Spring MVC
* Spring Test
* Spring Cache
* Spring Security
* H2 (In Memory Database)
* Boostrap 
* Themeleaf

## Instrução de Build e Deploy
Baixar e extrair [JDK 8+](https://openjdk.java.net/) e [Maven](https://maven.apache.org/download.cgi).

Configurar a variavel de ambiente JAVA_HOME setando o seu valor como o caminho onde foi extraido o JDK.

Configurar a variavel de ambiente MAVEN_HOME setando o sseu valor como o caminho onde foi extraido o Maven.

Adicionar as duas variaveis criadas a variavel já existente PATH da seguinte maneira:

* %JAVA_HOME%\bin
* %MAVEN_HOME%\bin

Tendo esse projeto baixado como zip e extraido, ou clonado com o git clone do [git](https://git-scm.com/downloads).

Abrir o console do sistema operacional, entrar no caminho onde foi extraido o projeto e executar o seguinte comando:

## mvn clean install
Esse comando instalará as dependencias necessarias para execução do projeto e executará seu build.
## mvn spring-boot:run
Esse comando subirá o servidor embarcado do Spring, o Tomcat com  o .jar compilado.

Assim que terminado o processo, o projeto estará provisionado na url local [http://localhost:8080/](http://localhost:8080/).

## Login
Foi criado dois usuários de teste
* login: admin  | password: pass
* login: user  | password: pass

## Home
Após logado será redirecionado para a home do sistema.

No primeiro acesso a home, simulará um acesso lento ao sistema, que poderia acontecer em uma situação de alta indice de requisiçoes por segundo.

No segundo acesso em diante o sistema responderá utilizando o Cache implementado na aplicação.


## Api Rest
Foi também criado um end point Rest para acessar os mesmos dados exibidos na home.

Para fins de teste, não a necessidade de autenticação nesse end point.

Url de acesso: [http://localhost:8080/intelipost/vi/news](http://localhost:8080/intelipost/vi/news).

## Proposta do projeto
1) Imagine que hoje tenhamos um sistema de login e perfis de usuários. O sistema conta com mais de 10 milhões de usuários, sendo que temos um acesso concorrente de cerca de 5 mil usuários. Hoje a tela inicial do sistema se encontra muito lenta. Nessa tela é feita uma consulta no banco de dados para pegar as informações do usuário e exibi-las de forma personalizada. Quando há um pico de logins simultâneos, o carregamento desta tela fica demasiadamente lento. Na sua visão, como poderíamos iniciar a busca pelo problema, e que tipo de melhoria poderia ser feita?

R: Partindo do principio que esse projeto fosse um monolito (levando em conta que não foi especificado a arquitetura) a ordem natural da busca seria.

Primeiramente verificar se o gargalo ocorre na Aplicaçao ou no Banco de Dados, através de metricas de consumo nos momentos de muito acesso;

## 1. Problema na Aplicaçao
    * Se o Front e o Back End gargalam em situações não correspondentes, poderia ser o caso de segregar a apliçao de front e back end.
    a) Front
      * Verificar a quantidade de acesso a recursos staticos, utilizar recursos de minificação, bundle e habilitar o cache de recursos staticos.
      * Verificar melhorias nas imagens apresentadas no site, qualidade, cache.
      * Verificar a quantidade de chamadas Ajax.
    b) Back End
      * Adicionar logs de tempo de execução de consultas, para encontrar possiveis serviços com gargalo.
      * Otimizaçao da consulta, uso inteligente do cache da API de acesso a dados.
      * Habilitar cache das requisiçoes, a principio cache na aplicação já diminuiria bastante o stress da aplicaçao e banco.
      * Escalonamento horizontal poderia aliviar o indice de aumento de tempo por request.
      * Se a necessidade de manter cache entre instancias, poderia ser interessante começar a utilizar um sistema de Cache Distribuido como o Redis.
      * Se mesmo assim o aumento de CPU e Memoria continua crecente, o serviços com mais gargalo, poderiam ser isolados, em microserviços (ex: serviço de autenticação) e escalonado em paralelo ao resto do sistema.
      * Poderia ser interessante, dependendo do serviço impactante, testar uma soluçáo de Servless comom o AWS Lambda ou Azure Functions.
      * Outra saída interessante poderia ser alterar a forma em que os dados são processado e disposto a aplicaçao, utilizando um padrão Reativo, a principio nos microserviços impactantes, utilizando ainda o Spring, teriamos as soluçoes de Reactive Spring, usando servidor Netty (não blocante) para criação de aplicações reativas.
## 2. Gargalo no Banco
    * Levando em conta que não tem problemas na forma que as Querys estão sendo criadas pela aplicação.
    * Em bancos Relacionais, poderia ser o caso de checar os indices das tabelas, explorar formas particulares de indices tabela a tabela, olhando para a forma que ela é mais consultada.
    * Talvez segregando aos poucos as tabelas, mais impactantes, talvez colocando em outras soluçoes, talvez NOSQL em dados não estruturados, como Mongo DB.
    * Se ouver a necessidade de grande escalabilidade horizontal, poderia ser interessante utilizar o Cassandra, ou equivalentes na Nuvem.

A aplicação da forma que foi criada, demonstra só os primeiros passos em busca da resolução do problema disposto, onde poderia na verdade ter N causas, N soluções, e com os anos de experiência que eu tenho na área, sei que não existe bala de prata, existe boas práticas e algumas ações mais e menos convencionais para se tomar em situaçoes como essa, mas dependeria de caso a caso, de negócio a negócio.