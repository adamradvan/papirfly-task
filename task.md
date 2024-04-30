## TASK

- Your task is to implement a RESTful back-end API for registering and retrieving shop articles and categories.
- You can choose between completing the task in Kotlin or Java.
- Data should be persisted in a database of choice between runs and the database schema should be handed in as part of
  the assignment.
    - Preferable as a migration script that creates the database automatically during start of the program.
- Make sure that the code is covered with unit tests and that the project is well-structured.

Implement the following HTTP end-points according to the corresponding contracts:

### The article object

| Property    | Required? | Format                                                                                                                                  |
|-------------|-----------|-----------------------------------------------------------------------------------------------------------------------------------------|
| article_id  | optional  | Number > 0. Must not exist in POST requests, but required in all responses.                                                             |
| name        | required  | String <= 64 characters                                                                                                                 |
| description | required  | String <= 2048 characters                                                                                                               |
| categories  | optional  | Array of Number (List of category id’s). An article can belong to none or more categories, and a category can be used in many articles. |
| price       | required  | Number >= 0                                                                                                                             |
| currency    | optional  | Currency code according to ISO 4217 (NOK, CZK, EUR, etc.), can be blank if price == 0 otherwise required                                |

### The category object

| Property    | Required? | Format                                                                      |
|-------------|-----------|-----------------------------------------------------------------------------|
| category_id | required  | Number > 0. Must not exist in POST requests, but required in all responses. |
| name        | required  | String <= 64 characters                                                     |
| description | optional  | String <= 2048 characters                                                   |

### Endpoints

| Method | Endpoint                      | Description                                                            |
|--------|-------------------------------|------------------------------------------------------------------------|
| POST   | /api/articles                 | Accepts a new article object and stores it                             |
| GET    | /api/articles/{article-id}    | Returns the article object with the specified ID, or 404 if not found  |
| GET    | /api/articles                 | Returns an array of articles based on specified criteria in the URL    |
| POST   | /api/categories               | Accepts a new category object and stores it                            |
| GET    | /api/categories/{category-id} | Returns the category object with the specified ID, or 404 if not found |
| GET    | /api/categories               | Returns an array of categories based on specified criteria in the URL  |
