package org.guylabs.angular.spring.data.rest.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import java.util.ArrayList;

/**
 * Main application class which is auto configured by Spring. It adds default values in the main method
 * and sets the base URI of the REST endpoint to "/rest".
 */
@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
public class Application extends RepositoryRestConfigurerAdapter {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        ItemRepository itemRepository = context.getBean(ItemRepository.class);

        // save a couple of categories
        Category firstCategory = categoryRepository.save(Category.from("Parent Category 1", null, new ArrayList<Item>()));
        categoryRepository.save(Category.from("Child Category 1", firstCategory, new ArrayList<Item>()));
        categoryRepository.save(Category.from("Child Category 2", firstCategory, new ArrayList<Item>()));
        categoryRepository.save(Category.from("Parent Category 2", null, new ArrayList<Item>()));
        Category secondCategory = categoryRepository.save(Category.from("Parent Category 3", null, new ArrayList<Item>()));
        categoryRepository.save(Category.from("Child Category 3", secondCategory, new ArrayList<Item>()));
        categoryRepository.save(Category.from("Child Category 4", secondCategory, new ArrayList<Item>()));
        categoryRepository.save(Category.from("Child Category 5", secondCategory, new ArrayList<Item>()));

        // save a couple of items
        itemRepository.save(Item.from("Item 1", null));
        itemRepository.save(Item.from("Item 2", firstCategory));
        itemRepository.save(Item.from("Item 3", firstCategory));
        itemRepository.save(Item.from("Item 4", secondCategory));
        itemRepository.save(Item.from("Item 5", secondCategory));
        itemRepository.save(Item.from("Item 6", secondCategory));
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            config.setBasePath("/rest");
    }

}