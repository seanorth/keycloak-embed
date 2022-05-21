package cn.springseed.keycloak.springboot.config;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * 属性
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "s8d.keycloak")
public class KeycloakProperties extends HashMap<String, Object>  {
    
}
