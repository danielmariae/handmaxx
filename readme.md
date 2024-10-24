<h1> Olá! Bem-vindo ao projeto HANDMAXX.</h1>

Este projeto foi feito usando a plataforma Quarkus e as linguagens: Java, SQL (através do SGBD PostgreSQL), AngularJS, NodeJS.

Como fazer para executar o projeto:

<h3>BANCO DE DADOS</h3>

*Necessário ter o PostgreSQL instalado, a partir da versão 14.*

**1. Criar usuário no PgAdmin com as seguintes informações de usuário:**

```java
Nome de usuário: handmaxx_admin
Senha: 123456
```
**2. Criar o banco de dados informando o usuário 'handmaxx_admin' como admin.**

```
Nome da base de dados: handmaxx_db
```

**3. Atualizar endereço do Postgres no arquivo application.propeties no seguinte caminho:**
```
.\src\main\resources\application.properties
```

<h3>BACKEND</h3>

*Necessário ter o JDK ou OpenJDK instalado na versão 17.*

**1. Verificar instalação do JDK 17, o suportado por esse projeto. Digite o comando:**

```
java --version
```
**2. Compilar o Quarkus e iniciar execução do servidor através dos comandos (usar Terminal ou PowerShell):**
```
./mvnw compile quarkus:dev

# Caso esteja no Linux ou MacOs, usar:
mvn compile quarkus:dev
```

<h3>API DO WHATSAPP</h3>

*Necessário ter o Docker Desktop instalado (em qualquer plataforma).*

**1. Executar os comandos abaixo:**

```
docker pull devlikeapro/waha
docker run -it -p 3000:3000/tcp -e "WHATSAPP_DEFAULT_ENGINE=NOWEB" devlikeapro/waha
```

**Fazer a conexão com WhatsApp, acessando o endereço: http://localhost:3000/dashboard**

<h3>EMAIL (GMAIL)</h3>

*Necessário ter o Docker Desktop instalado (em qualquer plataforma).*

**1. Configurar em application.properties:**

```
quarkus.mailer.from=#@gmail.com // Colocar aqui seu email do Google.
quarkus.mailer.host=smtp.gmail.com
quarkus.mailer.port=465
quarkus.mailer.ssl=true
quarkus.mailer.username=#@gmail.com // Colocar aqui também seu email do Google.
quarkus.mailer.password=# // Aqui digitar a senha de aplicativo, obtida nas configurações de segurança do seu gmail.
quarkus.mailer.mock=false // Se colocar = true, ele não enviará os emails.
```

