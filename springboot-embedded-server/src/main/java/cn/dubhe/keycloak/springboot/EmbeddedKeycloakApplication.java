package cn.dubhe.keycloak.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

/**
 * 程序入口
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
@SpringBootApplication(exclude = {LiquibaseAutoConfiguration.class, FreeMarkerAutoConfiguration.class})
public class EmbeddedKeycloakApplication {
    public static void main(String[] args) throws Exception {
		SpringApplication.run(EmbeddedKeycloakApplication.class, args);
	}
}
