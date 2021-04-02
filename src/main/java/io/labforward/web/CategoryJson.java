package io.labforward.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.labforward.categories.entity.Type;

import java.util.Map;

public class CategoryJson {

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

    @JsonIgnore
    public Map<String, Type> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Map<String, Type> attributes) {
        this.attributes = attributes;
    }
}
