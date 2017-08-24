OVERVIEW

ABOUT THIS PROJECT:
Spring Boot based REST service for Commerce Scenario.
It exposes products and categories endpoints. And, uses Mongo DB to store the document representation of product and category entities.

HOW TO RUN LOCALLY:
Service can be run locally using - mvn spring-boot:run 
For this project to work locally- you would need Mongo DB running with default configuration. 
Please visit following Swagger Console -�
http://localhost:8080/swagger-ui.html

TECHNOLOGIES USED:
-Spring boot
-Spring data for Mongo DB
-Swagger
-Mongo DB
-Spock test framework

AREAS OF IMPROVEMENT
-Validations for missing fields
-Non-blocking IO can be incorporated
-Better Test coverage

EXAMPLES - 
Product json:
{
    "productId": "top01",
    "name": {
      "de": "croppen top",
      "en": "crop top"
    },
    "description": {
      "de": "croppen top",
      "en": "crop top"
    },
    "details": {
      "color": "yellow",
      "size": [
        "S",
        "M"
      ],
      "material": "cotton"
    },
    "categoryId": "32",
    "price": {
      "base": "USD",
      "amount": 234.78,
      "supportedCurrencies": [
        "INR",
        "EUR"
      ]
    }
  }
  
  
  Category json:  
 {
    "categoryId": "32",
    "name": {
      "en": "skirt",
      "de": "skirt"
    },
    "parent": "45",
    "ancestors": [
      {
        "categoryId": "46",
        "name": {
          "en": "western wear",
          "de": "westlich kleidung"
        }
      },
      {
        "categoryId": "48",
        "name": {
          "en": "women",
          "de": "damen"
        }
      }
    ]
  }
