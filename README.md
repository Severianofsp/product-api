# Requisitos
 * Java 17 ou maior;
 * Maven 3.6 ou maior;
 * Mysql 8.0 ou maior;

## Variáveis
  * DATASOURCE_URL
  * DATASOURCE_USER
  * DATASOURCE_PASSWORD
  * DATASOURCE_DATABASE_NAME


# Iniciando
Execute para gerar o arquivo .jar

 `./mvnw install`   

 Execute para inciar a aplicação
 
 `java -jar target/productapi-3.2.2.jar.jar`

## Executando via Docker
Caso queria usar o docker-compose para iniciar a aplicação é necessário executar o seguinte comando

`docker-compose up`

Caso queria executar via Dockerfile execute os seguintes comandos

`docker build -t product-api . `

Para iniciar 

`docker run --name product-api-container -e DATASOURCE_URL=<url host do banco> -e DATASOURCE_DATABASE_NAME=<nome do banco> -e DATASOURCE_USER=<usuário do banco> -e DATASOURCE_PASSWORD=<senha do banco> -p 8080:8080 product-api`

Após inicialização acessar a rota da documentação:

[Swagger Document](http://localhost:8080/swagger-ui/index.html#/)
