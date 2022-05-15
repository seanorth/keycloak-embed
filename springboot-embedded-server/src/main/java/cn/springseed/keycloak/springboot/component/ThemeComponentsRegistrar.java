package cn.springseed.keycloak.springboot.component;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.KeycloakDeploymentInfo;
import org.keycloak.provider.ProviderManager;
import org.keycloak.provider.ProviderManagerDeployer;
import org.keycloak.provider.ProviderManagerRegistry;

import lombok.extern.slf4j.Slf4j;

/**
 * 主题组件注册
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@Slf4j
public class ThemeComponentsRegistrar extends AbstractComponentsRegistrar  {
    public static final String THEME_RESOURCES_LOCATION = "theme-resources";

    @Override
    public String getId() {
        return "s8d-theme-component-registrar";
    }

    @Override
    public String getDisplayType() {
        return getId().toUpperCase();
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
       List<URL> themes = discoverEmbeddedThemes();
        if (!CollectionUtils.isEmpty(themes)) {
            registerEmbeddedThemes((ProviderManagerDeployer) factory, themes);
        }
        
    }
    
    private List<URL> discoverEmbeddedThemes() {

        try {
            List<URL> themeResources = Collections.list(Thread.currentThread().getContextClassLoader().getResources(THEME_RESOURCES_LOCATION));
            if (CollectionUtils.isEmpty(themeResources)) {
                log.info("Could not detect any embedded theme components.");
                return Collections.emptyList();
            }

            log.info("Detected embedded theme components. themes={}", themeResources.size());

            return themeResources;
        } catch (IOException e) {
            log.info("Failed to load detect any theme-resources on classpath from {}.", THEME_RESOURCES_LOCATION, e);
        }
        return Collections.emptyList();
    }

    private void registerEmbeddedThemes(ProviderManagerDeployer factory, List<URL> themes) {

        KeycloakDeploymentInfo kdi = KeycloakDeploymentInfo.create().name("theme-components");
        kdi.themeResources();
        kdi.themes();

        ProviderManager pm = new ProviderManager(kdi, Thread.currentThread().getContextClassLoader());

        // Ugly hack to trigger theme deployment...
        Executors.newScheduledThreadPool(1).schedule(() -> {
            log.info("Register theme components");
            ProviderManagerRegistry.SINGLETON.deploy(pm);
        }, 1, TimeUnit.SECONDS);

    }    
}
