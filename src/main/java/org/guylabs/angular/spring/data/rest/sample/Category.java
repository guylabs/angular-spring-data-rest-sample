package org.guylabs.angular.spring.data.rest.sample;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;

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

    private Category() {
    }

    private Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

    public static Category from(String name, Category parent) {
        Assert.isTrue(!StringUtils.isEmpty(name), "The name must not be empty");
        return new Category(name, parent);
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
}
