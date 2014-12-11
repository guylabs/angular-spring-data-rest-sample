package org.guylabs.angular.spring.data.rest.sample;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Collection;

/**
 * Simple category entity.
 */
@Entity
public final class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    @OneToOne
    Category parent;

    @OneToMany(mappedBy = "category")
    Collection<Item> items;

    private Category() {
    }

    private Category(String name, Category parent, Collection<Item> items) {
        this.name = name;
        this.parent = parent;
        this.items = items;
    }

    public static Category from(String name, Category parent, Collection<Item> items) {
        Assert.isTrue(!StringUtils.isEmpty(name), "The name must not be empty");
        Assert.isTrue(items != null, "The items must not be null");
        return new Category(name, parent, items);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getParent() {
        return parent;
    }

    public Collection<Item> getItems() {
        return items;
    }
}
