package io.labforward.categories.repository;

import io.labforward.categories.entity.Category;
import io.labforward.categories.entity.Item;

import java.util.List;

public interface CategoryRepository {

    Category createCategory(Category category);

    Category findCategory(String id);

    Item createItem(Item item);

    Item updateItem(Item item);

    List<Item> findAllItems(Category category);
}
