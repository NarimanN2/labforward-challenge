package io.labforward.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ItemJson {

    private Long id;

    private CategoryJson category;

    @NotNull(message = "Attributes are required")
    @JsonIgnoreProperties(value = "item", allowSetters = true)
    private List<ValueJson> values;

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

    public List<ValueJson> getValues() {
        return values;
    }

    public void setValues(List<ValueJson> values) {
        this.values = values;
    }
}
