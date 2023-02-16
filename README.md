
# Library Management

Library Management REST API built using 
Spring Boot + MongoDB 


## Deployment

To deploy this project

```bash
  Use any IDE like Intellij, STS which runs your webapplication locally on your machine 

  base url : http://localhost:8080/api/v1/library
```


## API Reference

### Admin Priviliges

- Admin can perform all the CRUD operation on books.
- Admin can view all the users .
- Admin has the Privilige to delete users .
- Admin authenticationkey is necessary to perform any CRUD operations.

### User Priviliges

Users only have access to view the books in the library


#### Register as ADMIN/USER

```http
  POST /api/v1/library/register
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `User details` | `USER` | **Required**. User fields should not be null or empty.|

#### Login

```http
  GET /api/v1/library/login
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Login Credentials`      | `USER DETAILS` | **Required**. Email and Password. A Unique id will be sent back to the client as response which is used as an authenticationkey to access the books from the library |



#### Logout

```http
  GET /api/v1/library/logout
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Logout Credentials`      | `USER DETAILS` | **Required**. Email and Password |


#### Access Books from the library

```http
  GET /api/v1/library/books
```

| Headers | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `authenticationKey`      | `String` | Returns all books from the library. Authentication key is **Required** in order to access books from the library |

#### Get Book by Id

```http
  GET /api/v1/library/book/{id}
```

| Pathvariable | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `String` | Book Id is **Required** to fetch the book from the library |

#### Get Book by Name

```http
  GET /api/v1/library/book
```

| Parameters | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `String` |Book Name is **Required** to fetch the book from the library |

```http
  GET /api/v1/library/updatebook
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `book`      | `Book` |updates the book that matches the primary key (ID) |

```http
  GET /api/v1/book/library/book/remove/{id}
```

| Pathvariable | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Book` |Book ID is Required to remove the book from the library |

```http
  GET /api/v1/library/book/user/delete
```

| Parameters | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `String` |Removes the user with the specified Id |




