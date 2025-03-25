# Expenses Tracker

The **Expenses Tracker** is a web-based application built using **Spring Boot 3.5.0** and **Java 23**. It helps users manage and track their expenses efficiently by providing CRUD operations on various entities such as Users, Roles, Categories, and Expenses.

## Features

### User Management
- Create User: Add a new user to the system.
- Get User by ID: Retrieve user details using a unique ID.
- Get All Users: List all registered users.
- Delete User: Remove a user from the system.
- Update User: Modify details of an existing user.
- Get User Roles: Retrieve user roles details using a unique ID.
- Get User Expenses: Retrieve user expenses details using a unique ID.
- Get User Expenses PDF: Retrieve user expenses details in a pdf format using a unique ID
  
### Role Management
- Create Role: Add new roles to the system.
- Get Role by ID: Retrieve role details using a unique ID.
- Get All Roles: List all available roles.
- Delete Role: Remove a role from the system.

### Category Management
- Create Category: Define categories for expenses.
- Get Category by ID: Retrieve category details using a unique ID.
- Get All Categories: List all categories.
- Delete Category: Remove a category from the system.

### Expense Management
- Create Expense: Add an expense entry.
- Get Expense by ID: Retrieve details of a specific expense.
- Get All Expenses: List all expenses.
- Get Users Expenses By Category: List all users expenses categorised by spending type.
- Get User Expenses By Category: List a user's expenses categorised by spending type.
- Get Expenses By Category Chart: Show doughnut chart with all users expenses categorised by spending type.
- Delete Expense: Remove an expense entry.

## Technologies Used
- **Java 23**: Core programming language.
- **Spring Boot**: Framework for application development.
- **Spring Security**: User authentication and authorization.
- **JPARepository**: Interface for simplifying data access layers.
- **Hibernate**: ORM for database interaction.
- **MySQL**: Relational database for storing data.
- **Maven**: Build automation and dependency management.
- **Postman**: API testing.
- **Swagger**: API documentation.

## Third Party Integerations
### QuickChart API:
- Used to generate doughnut charts for expense tracking, providing admins with a visual representation of users spending types and their amount.

### iTextPDF:

- Used to export user expenses in a structured PDF format.


## Entity Relationships

### User
- A user can have one or more expenses.
- A user can have one or more roles.

### Role
- A role can be assigned to multiple users.

### Category
- A category can contain multiple expenses.

### Expense
- An expense belongs to a user and a category.

## Database schema
![Expenses Tracker DB scheme](https://github.com/user-attachments/assets/743e66d6-41d6-408e-b251-972f1906301a)

## Database Script

```sql
CREATE TABLE users (
    userid INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE category (
    categoryid INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE expenses (
    expenseid INT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10,2) NOT NULL,
    date DATE NOT NULL,
    description TEXT,
    categoryid INT NOT NULL,
    userid INT NOT NULL,
    FOREIGN KEY (categoryid) REFERENCES category(categoryid) ON DELETE CASCADE,
    FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE
);

CREATE TABLE roles (
    roleid INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    userid INT NOT NULL,
    roleid INT NOT NULL,
    PRIMARY KEY (userid, roleid),
    FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE,
    FOREIGN KEY (roleid) REFERENCES roles(roleid) ON DELETE CASCADE
);

```

## Deployment

### Steps to Deploy
1. Clone the repository:
   ```bash
   git clone https://github.com/mariammahmoud18/Expenses-Tracker.git
   cd expenses-tracker
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the API at `http://localhost:8080`


## API Endpoints

### Users Endpoints
- `GET /tracker/users` - Retrieve all users (Admin only)
- `GET /tracker/users/{id}` - Retrieve a specific user
- `GET /tracker/users/{id}/roles` - Retrieve a specific user roles
- `GET /tracker/users/{id}/expenses` - Retrieve a specific user expenses
- `GET /tracker/users/{id}/expenses/pdf-download` - download a pdf format of a specific user expenses
- `POST /tracker/users` - Add a new user (Admin only)
- `PUT /tracker/users` - Update user details (Admin only)
- `DELETE /tracker/users/{id}` - Delete a user (Admin only)

### Roles Endpoints
- `GET /tracker/roles` - Retrieve all roles (Admin only)
- `GET /tracker/roles/{id}` - Retrieve a specific role (Admin only)
- `POST /tracker/roles` - Add a new role (Admin only)
- `DELETE /tracker/roles/{id}` - Delete a role (Admin only)

### Category Endpoints
- `GET /tracker/category` - Retrieve all categories
-  `GET /tracker/category` - Retrieve a specific category
- `POST /tracker/category` - Add a new category
- `DELETE /tracker/category/{id}` - Delete a category (Admin only)

### Expenses Endpoints
- `GET /tracker/expenses` - Retrieve all expenses (Admin only)
- `GET /tracker/expenses/{id}` - Retrieve a specific expense
- `GET /tracker/expenses/{userId}/users` - Get a user's expenses spending types
- `GET /tracker/expenses/users` - Get all users' expenses spending types (Admin only)
- `GET /tracker/expenses/chart` - Get all users' expenses spending types in a doughnut chart (Admin only)
- `POST /tracker/expenses` - Add a new expense
- `DELETE /tracker/expenses/{id}` - Delete an expense (Admin only)



## Contact
For inquiries, contact [mariamswelam20@gmail.com](mariamswelam20@gmail.com).

