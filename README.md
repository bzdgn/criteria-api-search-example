# Flexible Search With CriteriaAPI

## TOC

- [1 Introduction and goal of the project](#1-introduction-and-goal-of-the-project) <br/>
- [2 Running The Application](#2-running-the-application) <br/>
    - [2.1 Running via Docker](#21-running-via-docker) <br/>
    - [2.2 Running via IDE](#22-running-via-ide) <br/>
- [3 Testing the application](#3-testing-the-application) <br/>

## 1 Introduction and goal of the project

This project aims to make a demonstration of the Criteria API which is a part of Java Persistence API. It's an alternative to JPQL and native SQL queries.

In this project, I'm using the Criteria API in order to build up complex filter queries.


[Go back to TOC](#toc)


## 2 Running The Application

There are two ways to run the application.

- Run through docker with all services up
- Run the postgres via Docker and application via IDE 

The former is only to demonstrate the application easily. The latter, you can play around with the IDE and debug it.

## 2.1 Running via Docker

Simple as the title, just run the docker compose and play with Postman.

`docker compose up`


[Go back to TOC](#toc)


## 2.2 Running via IDE

We only need the postgres service inside the docker stack. Just run the postgres service;

`docker compose up -d postgres`

Afterwards, run the application through the IDE and it will connect to the postgres service.


[Go back to TOC](#toc)


## 3 Testing the application

We need to use Postman tool in order to test the service. The controller expects a POST message with a body including the filters.

Here is a filter that selects the category with 'software'. In Postman, it will appear as below;

The content of the filter is as below;

<p align="center">
    <img src="https://github.com/bzdgn/criteria-api-search-example/blob/main/misc/01_category_postman.png" alt = "01_category_postman.png" width="500" style="height: auto;" />
</p>

Input filter body;

```json
[
    {
        "field": "category",
        "operator": "is",
        "value": "electronics"
    }
]
```

The output will bring all the entries matching the filter, in this case, all entries that equals to "electronics";

```json
[
    {
        "id": 1,
        "name": "Laptop",
        "price": 1500.0,
        "category": "electronics"
    },
    {
        "id": 2,
        "name": "ACME Phone",
        "price": 900.0,
        "category": "electronics"
    },
    {
        "id": 3,
        "name": "Headphones",
        "price": 120.0,
        "category": "electronics"
    }
]
```

A more complex chaing of filters is as below;

```json
[
    {
        "field": "category",
        "operator": "contains",
        "value": "software"
    },
    {
        "field": "name",
        "operator": "contains",
        "value": "ACME"
    },
    {
        "field": "price",
        "operator": ">=",
        "value": 56
    },
    {
        "field": "price",
        "operator": "<",
        "value": 1500
    }
]
```

The output will match the chaining of the filters with AND operation;

```json
[
    {
        "id": 4,
        "name": "ACME OS",
        "price": 99.0,
        "category": "software"
    },
    {
        "id": 5,
        "name": "ACME Antivirus",
        "price": 56.0,
        "category": "software"
    },
    {
        "id": 6,
        "name": "ACME Draw",
        "price": 278.0,
        "category": "software"
    }
]
```

[Go back to TOC](#toc)