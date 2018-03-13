package ru.bellintegrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ru.bellintegrator.catalog.controller.impl.CatalogControllerImpl;
import ru.bellintegrator.catalog.dao.impl.CountrieDAOImpl;
import ru.bellintegrator.catalog.dao.impl.DocDAOImpl;
import ru.bellintegrator.catalog.service.impl.CatalogServiceImpl;
import ru.bellintegrator.offices.controller.impl.OfficeControllerImpl;
import ru.bellintegrator.offices.dao.impl.OfficeDAOImpl;
import ru.bellintegrator.offices.service.impl.OfficeServiceImpl;
import ru.bellintegrator.organization.controller.impl.OrganizationControllerImpl;
import ru.bellintegrator.organization.dao.impl.OrganizationDAOImpl;
import ru.bellintegrator.organization.service.impl.OrganizationServiceImpl;
import ru.bellintegrator.user.controller.impl.UserControllerImpl;
import ru.bellintegrator.user.dao.impl.UserDAOImpl;
import ru.bellintegrator.user.service.impl.UserServiceImpl;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@ImportResource("spring_mvc_config.xml")
@SpringBootApplication
@ComponentScan(basePackageClasses = {
        CatalogControllerImpl.class, CatalogServiceImpl.class, CountrieDAOImpl.class, DocDAOImpl.class,
        UserControllerImpl.class, UserServiceImpl.class, UserDAOImpl.class,
        OfficeControllerImpl.class, OfficeServiceImpl.class, OfficeDAOImpl.class,
        OrganizationControllerImpl.class, OrganizationServiceImpl.class, OrganizationDAOImpl.class})

public class ApplicationCatalog {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationCatalog.class);
        app.run(args);
    }

    @Bean
    public TaskExecutor controllerPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() + 1);
        executor.setQueueCapacity(25);
        return executor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Sample")
                .description("Spring REST Sample")
                .contact("https://github.com/RGlebovaBellIntegrator/test_bell_project")
                .version("1.0")
                .build();
    }
}