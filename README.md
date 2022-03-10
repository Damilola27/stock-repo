# Stock

This is a simple stock application that could be used to perform basic crud operations in an inventory application.

## Installation
### Prerequisites
> Java 8 and above

> Maven >3.8

> Spring Initializer

> MySQL
 
> H2 database for testing

> IDE (IntelliJ or Eclipse or VSCode)

### Steps
1. Clone the project from [here](https://github.com/Damilola27/stock-repo.git)
1. cd stock-repo
1. Set up MySQL database
>1. check the db folder in resources for setup
>1. copy the setUp.sql script
>1. go to MySQL workbench and create a new query tab.
>1. paste the copied script and execute.
4. ```shell
    mvn -B package 
   ```

5. ```shell
    java -jar target/stock-0.0.1-SNAPSHOT.jar
    ```


## Usage
1. ```POST /api/v1/stocks```
> This endpoint takes a stock request object
> 
> Request Body
> 
> ```json
> {
>   "name": "",
>   "price": 0.00,
>   "quantity": 0
> }
> ```
>
> On a successful request, this endpoint would return a 201 status code and a stock response
> 
> Response Body
> ```json
> {
>   "id": 0,
>   "name": "",
>   "price": 0.00,
>   "quantity": 0,
>   "dateCreated": "2022-03-10 10:51:33",
>   "dateUpdated": null
> }
> ```
> On a failed request e.g, if the name or price is not provided, this endpoint would return a 400 bad request status code
> 
> Error Response
> ```json
> {
>   "status": "BAD_REQUEST",
>   "message": "error message",
>   "statusCode": 400
> }
> ```
>
2. ```GET /api/v1/stocks/{id}```
> This endpoint takes in a stock id
>
> On a successful request, this endpoint would return a 200 status code and a stock response
>
>
> Response Body
> ```json
> {
>   "id": 0,
>   "name": "",
>   "price": 0.00,
>   "quantity": 0,
>   "dateCreated": "2022-03-10 10:51:33",
>   "dateUpdated": null
> }
> ```
> On a failed request eg, invalid id, this endpoint will return an error response
>
> Error Response
> ```json
> {
>   "status": "NOT_FOUND",
>   "message": "error message",
>   "statusCode": 404
> }
> ```
>

3. ```GET /api/v1/stocks```
> This endpoint returns all the stocks in the repository, and a 200 status code
>
>
> Response Body
> ```json
> [
>   {
>       "id": 0,
>       "name": "",
>       "price": 0.00,
>       "quantity": 0,
>       "dateCreated": "2022-03-10 10:51:33",
>       "dateUpdated": null
>   },
>   {
>       "id": 0,
>       "name": "",
>       "price": 0.00,
>       "quantity": 0,
>       "dateCreated": "2022-03-10 10:51:33",
>       "dateUpdated": null
>   }
> ]
> ```
>
4. ```PUT /api/v1/stocks/{id}```

> This endpoint updates a particular stock by taking in the id as a path variable, and an update request body
>
> Request Body
> 
> All fields in request body are optional.
> ```json
> {
>   "name": "",
>   "price": 0.0,
>   "quantity": 0
> }
> ```
> On a successful request, this endpoint returns a 200 status  
>
> Response Body
> ```json
>   {
>       "id": 0,
>       "name": "",
>       "price": 0.00,
>       "quantity": 0,
>       "dateCreated": "2022-03-10 10:51:33",
>       "dateUpdated": "2022-03-10 10:51:33"
>   }
> ```
> On a failed request eg, id doesn't exist, this endpoint will return an error response
>
> Error Response
> ```json
> {
>   "status": "NOT_FOUND",
>   "message": "error message",
>   "statusCode": 404
> }
> ```
> 
5. ```DELETE /api/v1/stocks/{id}```
>
> This endpoint deletes a stock by getting the stock id as a path variable
> and returns a 204 status code.

