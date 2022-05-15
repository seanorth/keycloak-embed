package cn.springseed.keycloak.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

/**
 * 程序入口
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@SpringBootApplication(exclude = {LiquibaseAutoConfiguration.class, FreeMarkerAutoConfiguration.class})
public class KeycloakEmbedApplication {
    public static void main(String[] args) throws Exception {
		SpringApplication.run(KeycloakEmbedApplication.class, args);
	}
}
