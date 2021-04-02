package io.labforward.categories.service;

import io.labforward.categories.entity.Category;
import io.labforward.categories.entity.Item;
import io.labforward.categories.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.createCategory(category);
    }

    @Override
    public Item createItem(String categoryId, Item item) {
        Category category = categoryRepository.findCategory(categoryId);
        item.setCategory(category);
        return categoryRepository.createItem(item);
    }

    @Override
    public Item updateItem(String categoryId, Item item) {
        Category category = categoryRepository.findCategory(categoryId);
        item.setCategory(category);
        return categoryRepository.updateItem(item);
    }

    @Override
    public List<Item> findAllItems(String categoryId) {
        Category category = categoryRepository.findCategory(categoryId);
        return categoryRepository.findAllItems(category);
    }
}
