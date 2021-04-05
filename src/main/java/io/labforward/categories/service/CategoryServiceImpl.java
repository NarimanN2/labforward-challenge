package io.labforward.categories.service;

import io.labforward.categories.entity.Attribute;
import io.labforward.categories.entity.Category;
import io.labforward.categories.entity.Item;
import io.labforward.categories.entity.Value;
import io.labforward.categories.exception.ResourceNotFoundException;
import io.labforward.categories.repository.CategoryRepository;
import io.labforward.categories.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Category createCategory(Category category) {
        for (Attribute attribute : category.getAttributes())
            attribute.setCategory(category);

        return categoryRepository.save(category);
    }

    @Override
    public Item createItem(Long categoryId, Item item) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found")
        );

        for (Value value : item.getValues())
            value.setItem(item);

        item.setCategory(category);
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Long categoryId, Item item) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found")
        );

        for (Value value : item.getValues())
            value.setItem(item);

        item.setCategory(category);
        return itemRepository.save(item);
    }

    @Override
    public List<Item> findAllItems(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found")
        );

        return itemRepository.findAllByCategory(category);
    }
}
