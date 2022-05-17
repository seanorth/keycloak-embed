package cn.springseed.keycloak.spi;

import lombok.extern.slf4j.Slf4j;

/**
 * 模拟服务，将消息显示到控制台
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@Slf4j
public class SimulationMqttService implements MqttService {

    @Override
    public void close() {     
    }

    @Override
    public void publish(String topic, String message) {
        log.info("***** SIMULATION MODE ***** Would publish to MTQQ server with topic {} and message: {}", topic, message);
    }
    
}
