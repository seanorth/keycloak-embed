package cn.springseed.keycloak.otp;

/**
 * 短信发送服务
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public interface SmsSenderService {
    void send(String phoneNumber, String message);
}
