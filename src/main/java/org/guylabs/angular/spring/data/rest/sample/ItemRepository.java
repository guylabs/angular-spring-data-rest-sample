package org.guylabs.angular.spring.data.rest.sample;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Default {@link org.guylabs.angular.spring.data.rest.sample.Item} repository.
 */
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {}