package io.labforward.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.labforward.categories.entity.Type;
import io.labforward.web.validation.FieldNames;
import io.labforward.web.validation.TableName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class CategoryJson {

    private Long id;

    @NotBlank(message = "Name is required")
    @TableName(message = "Name can only contains alphabetical characters and underscore")
    private String name;

    @NotNull(message = "Attributes are required")
    @FieldNames(message = "Attribute's name can only contains alphabetical characters and underscore")
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
