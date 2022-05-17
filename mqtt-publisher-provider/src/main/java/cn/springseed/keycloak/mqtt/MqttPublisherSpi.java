package cn.springseed.keycloak.mqtt;

import org.keycloak.provider.Provider;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.Spi;

/**
 * spid定义
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public class MqttPublisherSpi implements Spi {

    @Override
    public boolean isInternal() {
        return false;
    }

    @Override
    public String getName() {
        return "mqttPublisher";
    }

    @Override
    public Class<? extends Provider> getProviderClass() {
        return PublisherService.class;
    }

    @Override
    public Class<? extends ProviderFactory<?>> getProviderFactoryClass() {
        return PublisherServiceProviderFactory.class;
    }
    
}
