# Bot URL Checker
> Irá verificar para determinada URL se alguma alteração de texto ocorreu. 
 
> Caso sim, irá enviar e-mail informando.

## Uso
> Funciona apenas em requisições http/https GET;

> Não aceita captcha ou outras formas de validação pré carregamento página;

> Dica: informar a parte de texto da página que deseja verificar se irá ser alterado  
> com as tags de html.

> Ao criar seu e-mail para enviar as mensagens, por conta da forma de autenticação ser antiga,  
> é necessário ir nas configurações da conta, segurança e habilitar modo inseguro.

## Requisitos
1. Java 8 ou superior;
2. Maven compatível com Java 8 ou superior;

## Gerar arquivo executável
> Na raiz do projeto rodar o comando
```
mvn clean package
```

## Rodar programa JAVA default
```
java -jar target/bot-url-checker-jar-with-dependencies.jar
```

## Rodar programa Heroku
> Usar heroku cli
```
heroku login
heroku plugins:install java
mvn clean package
mv Procfile target/
cd target
heroku deploy:jar target/my-app.jar --app NOME_SEU_APP_HEROKU
```