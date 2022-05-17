package cn.springseed.keycloak.mqtt;

import org.keycloak.provider.Provider;

/**
 * 服务
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public interface PublisherService extends Provider {
    
    /**
     * 发布消息
     * @param topic
     * @param message
     */
    public void publish(final String topic, final String message);
}
