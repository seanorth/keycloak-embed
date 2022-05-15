package cn.springseed.keycloak.otp;

import lombok.extern.slf4j.Slf4j;

/**
 * 短信发送服务工程
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@Slf4j
public class SmsSenderServiceFactory {
	public static SmsSenderService get(ConfigProperties properties) {
		if (properties.isSimulation()) {
			return (phoneNumber, message) ->
				log.info(String.format("***** SIMULATION MODE ***** Would send SMS to %s with text: %s", phoneNumber, message));
		} else {
			// 使用其他方法发送短信
            throw new RuntimeException("没有实现");
		}
	}    
}
