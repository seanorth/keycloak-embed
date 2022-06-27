package cn.dubhe.keycloak.mqtt;

import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.ServerInfoAwareProviderFactory;

/**
 * 服务提供者工厂
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
public interface PublisherServiceProviderFactory extends ProviderFactory<PublisherService>, ServerInfoAwareProviderFactory {
    
}
