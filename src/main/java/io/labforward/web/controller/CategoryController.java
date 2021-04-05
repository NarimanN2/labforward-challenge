package io.labforward.web.controller;

import io.labforward.categories.entity.Category;
import io.labforward.categories.entity.Item;
import io.labforward.categories.service.CategoryService;
import io.labforward.web.dto.CategoryJson;
import io.labforward.web.dto.ItemJson;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity createCategory(@Valid @RequestBody CategoryJson categoryJson) {
        Category category = modelMapper.map(categoryJson, Category.class);
        category = categoryService.createCategory(category);
        categoryJson = modelMapper.map(category, CategoryJson.class);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryJson.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoryJson);
    }

    @GetMapping("/categories/{id}/items")
    public ResponseEntity findAllItems(@PathVariable("id") Long categoryId) {
        List<Item> items = categoryService.findAllItems(categoryId);
        List<ItemJson> jsonItems = modelMapper.map(items, new TypeToken<List<ItemJson>>() {}.getType());
        return ResponseEntity.ok(jsonItems);
    }

    @PostMapping("/categories/{id}/items")
    public ResponseEntity createItem(@PathVariable("id") Long categoryId, @Valid @RequestBody ItemJson itemJson) {
        Item item = modelMapper.map(itemJson, Item.class);
        item = categoryService.createItem(categoryId, item);
        itemJson = modelMapper.map(item, ItemJson.class);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(itemJson.getId())
                .toUri();

        return ResponseEntity.created(location).body(itemJson);
    }

    @PutMapping("/categories/{categoryId}/items/{itemId}")
    public ResponseEntity updateItem(@PathVariable Long categoryId, @PathVariable Long itemId, @Valid @RequestBody ItemJson itemJson) {
        Item item = modelMapper.map(itemJson, Item.class);
        item.setId(itemId);
        item = categoryService.updateItem(categoryId, item);
        itemJson = modelMapper.map(item, ItemJson.class);
        return ResponseEntity.ok(itemJson);
    }
}
