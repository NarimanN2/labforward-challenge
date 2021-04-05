package io.labforward.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

public class ValueJson {

    private Long id;

    @NotNull(message = "Item is required")
    private ItemJson item;

    @NotNull(message = "Attribute is required")
    private AttributeJson attribute;

    @NotNull(message = "Value is required")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemJson getItem() {
        return item;
    }

    public void setItem(ItemJson item) {
        this.item = item;
    }

    public AttributeJson getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeJson attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
