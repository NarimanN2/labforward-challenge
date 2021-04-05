package io.labforward.categories.repository;

import io.labforward.categories.entity.Category;
import io.labforward.categories.entity.Item;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @EntityGraph(value = "item-with-values")
    List<Item> findAllByCategory(Category category);
}
