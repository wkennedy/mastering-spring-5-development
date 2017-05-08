package com.github.wkennedy;

        import org.springframework.boot.web.server.WebServerFactoryCustomizer;
        import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
        import org.springframework.core.Ordered;
        import org.springframework.stereotype.Component;

@Component
public class SimpleServletContainerCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, Ordered {

    @Override
    public void customize(ConfigurableServletWebServerFactory configurableServletWebServerFactory) {
        configurableServletWebServerFactory.setDisplayName("Simple Service");
        configurableServletWebServerFactory.setPort(8080);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
