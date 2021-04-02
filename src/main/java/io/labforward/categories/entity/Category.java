package io.labforward.categories.entity;

import java.util.Map;

public class Category {

    private Long id;

    private String name;

    private Map<String, Type> attributes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Type> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Type> attributes) {
        this.attributes = attributes;
    }
}
