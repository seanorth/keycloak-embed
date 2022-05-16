package cn.springseed.keycloak.otp;

/**
 * 邮件发送服务
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public interface MailSenderService {
    void send(String mail, String message);
}
