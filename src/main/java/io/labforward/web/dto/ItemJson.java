package io.labforward.web.dto;

import io.labforward.web.validation.FieldNames;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class ItemJson {

    private Long id;

    private CategoryJson category;

    @NotNull(message = "Attributes are required")
    @FieldNames(message = "Attribute's name can only contains alphabetical characters and underscore")
    private Map<String, Object> attributes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryJson getCategory() {
        return category;
    }

    public void setCategory(CategoryJson category) {
        this.category = category;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
