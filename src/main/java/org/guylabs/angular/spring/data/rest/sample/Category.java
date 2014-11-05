package org.guylabs.angular.spring.data.rest.sample;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Simple category entity.
 */
@Entity
public final class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    private Category() {
    }

    private Category(String name) {
        this.name = name;
    }

    public static Category withName(String name) {
        Assert.isTrue(!StringUtils.isEmpty(name), "The name must not be empty");
        return new Category(name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
