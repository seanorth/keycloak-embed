package cn.springseed.keycloak.springboot.factory;

import org.keycloak.theme.ClasspathThemeResourceProviderFactory;


/**
 * 主题组件注册
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public final class EmbeddedThemeResourceProviderFactory extends ClasspathThemeResourceProviderFactory {

    public EmbeddedThemeResourceProviderFactory() {
        super("s8d-embedded-theme-resources", Thread.currentThread().getContextClassLoader());
    }

}
