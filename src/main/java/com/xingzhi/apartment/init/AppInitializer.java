package com.xingzhi.apartment.init;

import com.xingzhi.apartment.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/*
 * Maven skip test:
 * mvn -Dmaven.test.skip=true clean compile package
 *
 */

/* Define VM options
    -Ddatabase.driver=org.postgresql.Driver
    -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect
    -Ddatabase.url=jdbc:postgresql://localhost:5432/training_db
    -Ddatabase.user=admin
    -Ddatabase.password=Training123!
    -Dlogging.level.org.springframework=INFO
    -Dlogging.level.com.ascending=TRACE
    -Dserver.port=8080
*/

@SpringBootApplication(scanBasePackages = {"com.xingzhi.apartment"},
                       exclude = {
                       DataSourceAutoConfiguration.class,
                       DataSourceTransactionManagerAutoConfiguration.class,
                       HibernateJpaAutoConfiguration.class})
public class AppInitializer {
    public static void main(String[] args) throws NullPointerException{
        if (HibernateUtil.getSessionFactory() == null) {
            throw new NullPointerException("The Hibernate session factory is NULL!");
        }

        SpringApplication.run(AppInitializer.class, args);
    }
}