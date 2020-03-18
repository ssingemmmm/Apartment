# Apartment Information Project
a maven project based on Spring Boot framework

### Project Tech
* Database - PostgreSQL, Flyway
* Testing - JUnit, Mockito, Postman
* Framework - Spring Boot, Hibernate
* AWS Service - SQS, S3
* Other Tools - Docker, Maven, Git, Redis 

```bash
mvn clean compile flyway:migrate -Ddb_url=jdbc:postgresql://${db_url}:5432/${db_name} -Ddb_user=${db_user} -Ddb_password=${db_password} -Ddb_driver=org.postgresql.Driver
```

```bash
mvn test -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql://${db_url}:5432/${db_name} -Ddatabase.user=${db_user} -Ddatabase.password=${db_password} -Dlogging.level.org.springframework=INFO -Dlogging.level.com.xingzhi=TRACE -Dserver.port=8080 -Dsecret.key=66545321 -Daws.queue.name=''
```
