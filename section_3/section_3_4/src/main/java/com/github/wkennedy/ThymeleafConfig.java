package com.github.wkennedy;

import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;

@Configuration
@EnableConfigurationProperties(ThymeleafProperties.class)
public class ThymeleafConfig {

    private ApplicationContext applicationContext;
    private ThymeleafProperties thymeleafProperties;

    public ThymeleafConfig(
            final ApplicationContext applicationContext,
            final ThymeleafProperties thymeleafProperties) {
        super();
        this.applicationContext = applicationContext;
        this.thymeleafProperties = thymeleafProperties;
    }

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver() {
        final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(this.applicationContext);
        resolver.setPrefix(this.thymeleafProperties.getPrefix());
        resolver.setSuffix(this.thymeleafProperties.getSuffix());
        resolver.setTemplateMode(this.thymeleafProperties.getMode());
        if (this.thymeleafProperties.getEncoding() != null) {
            resolver.setCharacterEncoding(this.thymeleafProperties.getEncoding().name());
        }
        resolver.setCacheable(this.thymeleafProperties.isCache());
        final Integer order = this.thymeleafProperties.getTemplateResolverOrder();
        if (order != null) {
            resolver.setOrder(order);
        }
        resolver.setCheckExistence(this.thymeleafProperties.isCheckTemplate());

        return resolver;
    }

    @Bean
    public ISpringWebFluxTemplateEngine thymeleafTemplateEngine() {
        // We override here the SpringTemplateEngine instance that would otherwise be instantiated by
        // Spring Boot because we want to apply the SpringWebFlux-specific context factory, link builder...
        final SpringWebFluxTemplateEngine templateEngine = new SpringWebFluxTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafReactiveViewResolver thymeleafChunkedAndDataDrivenViewResolver() {
        final ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
        viewResolver.setTemplateEngine(thymeleafTemplateEngine());
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[]{"thymeleaf/*"});
        viewResolver.setResponseMaxChunkSizeBytes(8192); // OUTPUT BUFFER size limit
        return viewResolver;
    }
}
