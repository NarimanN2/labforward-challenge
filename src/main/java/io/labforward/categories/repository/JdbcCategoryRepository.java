package io.labforward.categories.repository;

import io.labforward.categories.entity.Category;
import io.labforward.categories.entity.Item;
import io.labforward.categories.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JdbcCategoryRepository implements CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Category createCategory(Category category) {
        Map<String, Type> attributes = category.getAttributes();
        List<String> columns = new ArrayList();
        columns.add("id BIGINT PRIMARY KEY AUTO_INCREMENT");
        columns.add("category_id BIGINT");

        for (Map.Entry entry : attributes.entrySet()) {
            String fieldName = (String) entry.getKey();
            Type fieldType = (Type) entry.getValue();
            columns.add(fieldName + " " + toSqlType(fieldType));
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    String query = "INSERT INTO category(name) VALUES(?)";
                    PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, category.getName());
                    return statement;
                },
                keyHolder
        );

        String query = String.format("CREATE TABLE %s (%s)", category.getName(), String.join(",", columns));
        jdbcTemplate.execute(query);
        category.setId((long) keyHolder.getKey());
        return category;
    }

    @Override
    public Category findCategory(String id) {
        String query = "SELECT * FROM category WHERE id = ?";
        return jdbcTemplate.queryForObject(
                query,
                (resultSet, rowNumber) -> {
                    Category category = new Category();
                    category.setId(resultSet.getLong("id"));
                    category.setName(resultSet.getString("name"));
                    return category;
                },
                id
        );
    }

    @Override
    public Item createItem(Item item) {
        Category category = item.getCategory();
        Map<String, Object> attributes = item.getAttributes();
        List<String> fields = new ArrayList<>(attributes.keySet());
        List<Object> values = new ArrayList(attributes.values());
        fields.add("category_id");
        values.add(category.getId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    String query = String.format(
                            "INSERT INTO %s (%s) VALUES(%s)",
                            category.getName(),
                            String.join(",", fields),
                            String.join(",", values.stream().map(v -> "?").collect(Collectors.toList()))
                    );

                    PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    for (int i = 0; i < values.size(); i++)
                        statement.setObject(i + 1, values.get(i));

                    return statement;
                },
                keyHolder
        );

        item.setId((long) keyHolder.getKey());
        return item;
    }

    @Override
    public Item updateItem(Item item) {
        jdbcTemplate.update(
                connection -> {
                    Category category = item.getCategory();
                    Map<String, Object> attributes = item.getAttributes();
                    String query = String.format(
                            "UPDATE %s SET %s WHERE id = %s",
                            category.getName(),
                            String.join(
                                    ",",
                                    attributes.entrySet()
                                            .stream()
                                            .map(e -> e.getKey() + " = ?")
                                            .collect(Collectors.toList())
                            ),
                            item.getId()
                    );

                    PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    List<Object> values = new ArrayList<>(attributes.values());
                    for (int i = 0; i < values.size(); i++)
                        statement.setObject(i + 1, values.get(i));

                    return statement;
                }
        );

        return item;
    }

    @Override
    public List<Item> findAllItems(Category category) {
        String query = String.format("SELECT * FROM %s WHERE category_id = ?", category.getName());
        return jdbcTemplate.query(
                query,
                (resultSet, rowNumber) -> {
                    Item item = new Item();
                    item.setId(resultSet.getLong("id"));
                    item.setCategory(category);

                    ResultSetMetaData metaData = resultSet.getMetaData();
                    Map<String, Object> attributes = new HashMap<>();

                    for (int i = 0; i < metaData.getColumnCount(); i++) {
                        String fieldName = metaData.getColumnName(i + 1).toLowerCase();
                        if (!fieldName.equals("category_id") && !fieldName.equals("id")) {
                            attributes.put(fieldName, resultSet.getObject(i + 1));
                        }
                    }

                    item.setAttributes(attributes);
                    return item;
                },
                category.getId()
        );
    }

    private String toSqlType(Type type) {
        switch (type) {
            case STRING:
                return "VARCHAR";
            case INTEGER:
                return "INTEGER";
            case LONG:
                return "BIGINT";
            case DOUBLE:
                return "DOUBLE";
            default:
                throw new IllegalArgumentException("Invalid type");
        }
    }
}
