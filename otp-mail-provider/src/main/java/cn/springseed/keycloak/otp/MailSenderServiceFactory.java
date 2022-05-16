package cn.springseed.keycloak.otp;

import lombok.extern.slf4j.Slf4j;

/**
 * 邮件发送服务工程
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@Slf4j
public class MailSenderServiceFactory {
	public static MailSenderService get(ConfigProperties properties) {
		if (properties.isSimulation()) {
			return (mail, message) ->
				log.info(String.format("***** SIMULATION MODE ***** Would send CODE to %s with text: %s", mail, message));
		} else {
			// 使用其他方法发送短信
            throw new RuntimeException("没有实现");
		}
	}    
}
