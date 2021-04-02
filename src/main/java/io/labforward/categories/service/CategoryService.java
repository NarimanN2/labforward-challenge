package io.labforward.categories.service;

import io.labforward.categories.entity.Category;
import io.labforward.categories.entity.Item;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Item createItem(String categoryId, Item item);

    Item updateItem(String categoryId, Item item);

    List<Item> findAllItems(String categoryId);
}
