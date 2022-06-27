package cn.dubhe.keycloak.springboot.factory;

import org.keycloak.theme.ClasspathThemeResourceProviderFactory;


/**
 * 主题组件注册
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
public final class EmbeddedThemeResourceProviderFactory extends ClasspathThemeResourceProviderFactory {

    public EmbeddedThemeResourceProviderFactory() {
        super("dubhe-embedded-theme-resources", Thread.currentThread().getContextClassLoader());
    }

}
