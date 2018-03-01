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
import ru.bellintegrator.catalog.dao.DocsDAO;
import ru.bellintegrator.catalog.dao.impl.CountriesDAOImpl;
import ru.bellintegrator.catalog.dao.impl.DocsDAOImpl;
import ru.bellintegrator.catalog.service.impl.CatalogServiceImpl;
import ru.bellintegrator.offices.controller.impl.OfficesControllerImpl;
import ru.bellintegrator.offices.dao.impl.OfficesDAOImpl;
import ru.bellintegrator.offices.model.Offices;
import ru.bellintegrator.offices.service.impl.OfficeServiceImpl;
import ru.bellintegrator.organizations.controller.OrganizationsController;
import ru.bellintegrator.organizations.controller.impl.OrganizationsControllerImpl;
import ru.bellintegrator.organizations.dao.impl.OrganizationsDAOImpl;
import ru.bellintegrator.organizations.service.OrganizationService;
import ru.bellintegrator.organizations.service.impl.OrganizationServiceImpl;
import ru.bellintegrator.users.controller.impl.UsersControllerImpl;
import ru.bellintegrator.users.dao.impl.UsersDAOImpl;
import ru.bellintegrator.users.service.impl.UsersServiceImpl;
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
        CatalogControllerImpl.class, CatalogServiceImpl.class, CountriesDAOImpl.class, DocsDAOImpl.class,
        UsersControllerImpl.class, UsersServiceImpl.class, UsersDAOImpl.class,
        OfficesControllerImpl.class, OfficeServiceImpl.class, OfficesDAOImpl.class,
        OrganizationsControllerImpl.class, OrganizationServiceImpl.class, OrganizationsDAOImpl.class})

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

    @Bean
    public Docket postApi() {
       return new Docket(DocumentationType.SWAGGER_2).groupName("docs").apiInfo(apiInfo()).
                select().paths(regex("/docs.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Sample with Swagger")
                .description("Spring REST Sample with Swagger")
                .contact("https://github.com/azEsm/empty_project")
                .version("1.0")
                .build();
    }
}