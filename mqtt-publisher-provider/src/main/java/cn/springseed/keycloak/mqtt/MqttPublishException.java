package cn.springseed.keycloak.mqtt;

/**
 * 发送异常
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public class MqttPublishException extends RuntimeException {

    public MqttPublishException(Throwable cause) {
        super(cause);
    }
    
}
