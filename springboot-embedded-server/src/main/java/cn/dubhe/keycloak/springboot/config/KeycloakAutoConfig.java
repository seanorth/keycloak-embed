package cn.dubhe.keycloak.springboot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
@Configuration
@EnableConfigurationProperties({KeycloakProperties.class, KeycloakCustomProperties.class})
public class KeycloakAutoConfig {
    
}
