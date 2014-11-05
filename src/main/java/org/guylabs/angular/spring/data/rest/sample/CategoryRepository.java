package org.guylabs.angular.spring.data.rest.sample;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Default {@link org.guylabs.angular.spring.data.rest.sample.Category} repository.
 */
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {}