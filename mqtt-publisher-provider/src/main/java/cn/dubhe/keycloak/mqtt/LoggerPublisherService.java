package cn.dubhe.keycloak.mqtt;

import lombok.extern.slf4j.Slf4j;

/**
 * 模拟服务，将消息显示到控制台
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
@Slf4j
public class LoggerPublisherService implements PublisherService {

    @Override
    public void close() {     
    }

    @Override
    public void publish(String topic, String message) {
        log.info("***** SIMULATION MODE ***** Would publish to MTQQ server with topic {} and message: {}", topic, message);
    }
    
}
