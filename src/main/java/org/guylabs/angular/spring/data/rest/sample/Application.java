package org.guylabs.angular.spring.data.rest.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Main application class which is auto configured by Spring. It adds default values in the main method
 * and sets the base URI of the REST endpoint to "/rest".
 */
@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
public class Application extends RepositoryRestMvcConfiguration {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        CategoryRepository repository = context.getBean(CategoryRepository.class);

        // save a couple of categories
        Category firstCategory = repository.save(Category.from("Parent Category 1", null));
        repository.save(Category.from("Child Category 1", firstCategory));
        repository.save(Category.from("Child Category 2", firstCategory));
        repository.save(Category.from("Parent Category 2", null));
    }

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        try {
            config.setBaseUri(new URI("/rest"));
        } catch (URISyntaxException exception) {
            throw new RuntimeException("Cannot set base uri on REST configuration", exception);
        }
    }

}