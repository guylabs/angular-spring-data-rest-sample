package org.guylabs.angular.spring.data.rest.sample;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;

/**
 * Simple item entity.
 */
@Entity
public final class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    @ManyToOne
    Category category;

    private Item() {
    }

    private Item(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public static Item from(String name, Category category) {
        Assert.isTrue(!StringUtils.isEmpty(name), "The name must not be empty");
        return new Item(name, category);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
