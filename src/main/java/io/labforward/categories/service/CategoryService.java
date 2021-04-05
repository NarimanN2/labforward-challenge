package io.labforward.categories.service;

import io.labforward.categories.entity.Category;
import io.labforward.categories.entity.Item;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Item createItem(Long categoryId, Item item);

    Item updateItem(Long categoryId, Item item);

    List<Item> findAllItems(Long categoryId);
}
