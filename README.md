# labforward-challenge
## Overview
This project is a REST API application using Java which provides the following functionality:

* Creating categories with attribute definitions
* Creating items in those categories
* Updating items
* Getting items of a category

## Build and Run
This project uses Maven as its build tool. To build the project, run this maven command:

```
mvn clean package
```

After that, You can start the application with:

```
java -jar /target/labforward-challenge-0.0.1-SNAPSHOT.jar
```

## Examples
Create a Category:
```
POST /categories
{
    "name":"ingredient",
    "attributes": [
        {
            "name": "weight"
        },
        {
            "name": "name"
        }
    ]
}
```

Create an Item:
```
POST /categories/{id}/items
{
  "values": [
    {
      "attribute": {
        "id": 2
      },
      "value": "5.2"
    },
    {
      "attribute": {
        "id": 3
      },
      "value": "cyanide"
    }
  ]
}
```
Update and Item:
```
PUT /categories/{id}/items/{id}
{
  "values": [
    {
      "attribute": {
        "id": 2
      },
      "value": "5.7"
    },
    {
      "attribute": {
        "id": 3
      },
      "value": "lithium"
    }
  ]
}
```
Get all items of a Category:
```
GET /categories/{id}/items
```

## Future Improvements
1. Add constraints for attributes (Required, Unique, ...)

1. More flat Data Access Objects(DTO)

1. Validate if attribute belongs to the category when creating an Item

1. More specific type for values
