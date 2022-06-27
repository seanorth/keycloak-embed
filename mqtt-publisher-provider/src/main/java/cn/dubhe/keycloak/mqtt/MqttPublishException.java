package cn.dubhe.keycloak.mqtt;

/**
 * 发送异常
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
public class MqttPublishException extends RuntimeException {

    public MqttPublishException(Throwable cause) {
        super(cause);
    }
    
}
