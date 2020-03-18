# Apartment Information Project
a maven project based on Spring Boot framework

### Project Tech
* Database - PostgreSQL, Flyway
* Testing - JUnit, Mockito, Postman
* Framework - Spring Boot, Hibernate
* AWS Service - SQS, S3
* Other Tools - Docker, Maven, Git, Redis 

### Environemnt Setup

#### Maven
>Use `Maven` to manage the project.     
>
Maven is a software project management and comprehension tool.
Dependencies and plugins used in the project are managed by Maven.
Maven infos inclued in [pom.xml](https://github.com/ssingemmmm/Apartment/blob/master/pom.xml).
#### Docker
>Use `Docker` to build containers
>
```sh
List all Docker images and containers:
    docker images
    docker ps -a

Pull images: 
    docker pull <image_name>
    
Restart an exited container:
    docker start <container_name>

```
#### Postgres
>Use `PostgreSQL` container as database server.
>
```sh
Pull a docker image of postgres
    docker pull postgres
    
Build a docker container of postgre from postgresdocker image
    docker run --name <container_name> -e POSTGRES_DB=<server_name> 
    -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5432:5432 
    -d postgres
```
#### Database Connection
>Use `JVM Option` to create database connection.
>
```sh
    -Ddatabase.user=${user_name}
    -Ddatabase.password=${password}
    -Ddatabase.driver=org.postgresql.Driver
    -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect
    -Ddatabase.url=jdbc:postgresql://${database_url}:${port}/${database_name}
```
#### Database Migration
>Use `Flyway` as a data migration and database version control tool .
>
 ```sh
    mvn clean compile flyway:migrate -Ddb_url=jdbc:postgresql://${db_url}:5432/${db_name} -Ddb_user=${db_user} -Ddb_password=${db_password} -Ddb_driver=org.postgresql.Driver
```
#### Redis
>Use `Redis` to enable caching for better controller performance.
>
```sh
Pull image from redis and run container:
    docker pull redis
    docker run -p 6379:6379 --name spring-redis -d redis
    
Enter interactive terminal:
    docker exec -it spring-redis /bin/bash 
    
Redis commandline:
    redis-cli keys *
    redis-cli FLUSHDB
    redis-cli info stats | grep 'keyspace_*'
```
#### Unit Test in container
```bash
mvn test -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql://${db_url}:5432/${db_name} -Ddatabase.user=${db_user} -Ddatabase.password=${db_password} -Dlogging.level.org.springframework=INFO -Dlogging.level.com.xingzhi=TRACE -Dserver.port=8080 -Dsecret.key=66545321 -Daws.queue.name=''
```
