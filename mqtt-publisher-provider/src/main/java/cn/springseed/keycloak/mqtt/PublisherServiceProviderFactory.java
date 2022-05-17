package cn.springseed.keycloak.mqtt;

import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.ServerInfoAwareProviderFactory;

/**
 * 服务提供者工厂
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public interface PublisherServiceProviderFactory extends ProviderFactory<PublisherService>, ServerInfoAwareProviderFactory {
    
}
