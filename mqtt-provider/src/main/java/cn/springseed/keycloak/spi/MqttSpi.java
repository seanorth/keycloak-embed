package cn.springseed.keycloak.spi;

import org.keycloak.provider.Provider;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.Spi;

/**
 * spid定义
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public class MqttSpi implements Spi {

    @Override
    public boolean isInternal() {
        return false;
    }

    @Override
    public String getName() {
        return "mqtt";
    }

    @Override
    public Class<? extends Provider> getProviderClass() {
        return MqttService.class;
    }

    @Override
    public Class<? extends ProviderFactory<?>> getProviderFactoryClass() {
        return MqttServiceProviderFactory.class;
    }
    
}
